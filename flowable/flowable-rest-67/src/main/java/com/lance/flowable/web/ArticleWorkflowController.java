package com.lance.flowable.web;

import com.lance.flowable.domain.Approval;
import com.lance.flowable.domain.Article;
import com.lance.flowable.service.ArticleWorkflowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArticleWorkflowController
 *
 * @author lance
 * @date 2022/3/26 09:47
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleWorkflowController {
  private final ArticleWorkflowService service;

  @PostMapping("/submit")
  public void submit(@RequestBody Article article) {
    service.startProcess(article);
  }

  @GetMapping("/tasks")
  public List<Article> getTasks(@RequestParam String assignee) {
    return service.getTasks(assignee);
  }

  @PostMapping("/review")
  public void review(@RequestBody Approval approval) {
    service.submitReview(approval);
  }
}
