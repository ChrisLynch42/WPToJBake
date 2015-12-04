/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion.document;

import com.codeexcursion.Item;

/**
 *
 * @author lynchcs
 */
public class HTMLBuilder {

  private static final String JBAKE_HEADER_DELIMITER = "~~~~~~";

  private Item item;
  private String content;

  public HTMLBuilder(Item item) {
    this.item = item;
    this.content = buildHTMLContent();
  }

  private String buildHTMLContent() {
    StringBuilder content = new StringBuilder();
    content.append(buildJBakeHeader());

    content.append(item.getContent());
    return content.toString();
  }

  private StringBuilder buildJBakeHeader() {
    StringBuilder header = new StringBuilder();
    header.append("title=");
    header.append(item.getTitle());
    header.append("\n");

    header.append("type=");
    header.append(item.getPostType());
    header.append("\n");

    header.append("date=");
    header.append(item.getPostDate());
    header.append("\n");

    header.append("status=");
    header.append(item.getStatus());
    header.append("\n");

    header.append("tags=");
    header.append(String.join(",", item.getTags()));
    header.append("\n");

    header.append("categories=");
    header.append(String.join(",", item.getTags()));
    header.append("\n");

    header.append(JBAKE_HEADER_DELIMITER);
    header.append("\n");

    return header;
  }

  public String getContent() {
    return content;
  }

}
