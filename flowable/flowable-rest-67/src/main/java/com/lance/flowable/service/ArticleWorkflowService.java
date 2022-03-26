package com.lance.flowable.service;

import com.lance.flowable.domain.Approval;
import com.lance.flowable.domain.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ArticleWorkflowService
 *
 * @author lance
 * @date 2022/3/26 09:44
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleWorkflowService {
  private final RuntimeService runtimeService;
  private final TaskService taskService;

  public void startProcess(Article article) {
    Map<String, Object> variables = new HashMap<>(4);
    variables.put("author", article.getAuthor());
    variables.put("url", article.getUrl());
    runtimeService.startProcessInstanceByKey("articleReview", variables);
  }

  public List<Article> getTasks(String assignee) {
    List<Task> tasks = taskService.createTaskQuery()
        .taskCandidateGroup(assignee)
        .list();

    List<Article> articles = tasks.stream()
        .map(task -> {
          Map<String, Object> variables = taskService.getVariables(task.getId());
          return new Article(
              task.getId(), (String) variables.get("author"), (String) variables.get("url"));
        })
        .collect(Collectors.toList());
    return articles;
  }

  public void submitReview(Approval approval) {
    Map<String, Object> variables = new HashMap<>(4);
    variables.put("approved", approval.isStatus());
    taskService.complete(approval.getId(), variables);
  }
}
