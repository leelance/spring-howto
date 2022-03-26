package com.lance.flowable.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Article
 *
 * @author lance
 * @date 2022/3/26 09:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
  private String id;
  private String author;
  private String url;
}
