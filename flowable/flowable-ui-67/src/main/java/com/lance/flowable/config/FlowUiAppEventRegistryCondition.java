package com.lance.flowable.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * FlowUiAppEventRegistryCondition
 *
 * @author lance
 * @date 2021/10/15 19:24
 */
@Configuration
public class FlowUiAppEventRegistryCondition extends SpringBootCondition implements AutoConfigurationImportFilter, BeanFactoryAware, Condition, EnvironmentAware {
  protected BeanFactory beanFactory;
  protected Environment environment;

  public FlowUiAppEventRegistryCondition() {
  }

  @Override
  public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
    ConditionEvaluationReport report = ConditionEvaluationReport.find(this.beanFactory);
    Map<String, ConditionOutcome> conditions = this.getConditionOutcomes();
    ConditionOutcome[] outcomes = this.getOutcomes(autoConfigurationClasses, conditions);
    boolean[] match = new boolean[outcomes.length];

    for (int i = 0; i < outcomes.length; ++i) {
      match[i] = outcomes[i] == null || outcomes[i].isMatch();
      if (!match[i] && outcomes[i] != null) {
        this.logOutcome(autoConfigurationClasses[i], outcomes[i]);
        if (report != null) {
          report.recordConditionEvaluation(autoConfigurationClasses[i], this, outcomes[i]);
        }
      }
    }

    return match;
  }

  protected Map<String, ConditionOutcome> getConditionOutcomes() {
    boolean jmsEnabled = this.environment.getProperty("flowable.task.app.jms-enabled", Boolean.class, false);
    boolean kafkaEnabled = this.environment.getProperty("flowable.task.app.kafka-enabled", Boolean.class, false);
    boolean rabbitEnabled = this.environment.getProperty("flowable.task.app.rabbit-enabled", Boolean.class, false);
    Map<String, ConditionOutcome> conditions = new HashMap<>(8);
    if (!jmsEnabled) {
      conditions.put("org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration", ConditionOutcome.noMatch("Property flowable.task.app.jms-enabled was not set to true"));
    }

    if (!kafkaEnabled) {
      conditions.put("org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration", ConditionOutcome.noMatch("Property flowable.task.app.kafka-enabled was not set to true"));
    }

    if (!rabbitEnabled) {
      conditions.put("org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration", ConditionOutcome.noMatch("Property flowable.task.app.rabbit-enabled was not set to true"));
    }

    return conditions;
  }

  @Override
  public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return ConditionOutcome.noMatch(ConditionMessage.empty());
  }

  protected ConditionOutcome[] getOutcomes(String[] autoConfigurationClasses, Map<String, ConditionOutcome> conditionOutcomes) {
    ConditionOutcome[] outcomes = new ConditionOutcome[autoConfigurationClasses.length];

    for (int i = 0; i < autoConfigurationClasses.length; ++i) {
      outcomes[i] = conditionOutcomes.get(autoConfigurationClasses[i]);
    }

    return outcomes;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
