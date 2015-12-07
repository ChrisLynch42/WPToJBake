/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringEscapeUtils;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 *
 * @author lynchcs
 */
public class Item {

  private Element item;
  private Namespace namespace;

  public Item(Element item, Namespace namespace) {
    this.item = item;
    this.namespace = namespace;
  }

  private String getText(String tagName) {
    String returnValue = "";
    if (item != null) {
      returnValue = item.getChildText(tagName, namespace);
    }
    return returnValue;
  }

  public String getAttachmentURL() {
    return getText(ItemElementChildTagNames.ATTACHMENT_URL);
  }
  
  public String getName() {
    return getText(ItemElementChildTagNames.NAME);
  }
  
  public String getPostType() {
    return getText(ItemElementChildTagNames.POST_TYPE);
  }

  public String getPostDate() {
    return getText(ItemElementChildTagNames.POST_DATE);
  }

  public String getTitle() {
    return getText(ItemElementChildTagNames.TITLE);
  }

  public String getStatus() {
    return getText(ItemElementChildTagNames.STATUS);
  }
  
  public String getContent() {
    return StringEscapeUtils.unescapeHtml(getText(ItemElementChildTagNames.CONTENT));
  }  

  public String getAttachedFile() {
    String returnValue = "";
    if (item != null) {
      List<Element> postMetas = item.getChildren(ItemElementChildTagNames.POST_META, namespace);
      if (postMetas != null) {
        returnValue = postMetas.
                stream().
                filter(postMeta -> PostMetaTypes.ATTACHED_FILE.equals(postMeta.getChildText(ItemElementChildTagNames.POST_META_KEY, namespace))).
                map(postMeta -> postMeta.getChildText(ItemElementChildTagNames.POST_META_VALUE, namespace)).
                findFirst().get();
      }
    }
    return returnValue;
  }

  public List<String> getCategories() {
    List<String> categoryNames = new ArrayList();
    if (item != null) {
      List<Element> categories = item.getChildren(ItemElementChildTagNames.CATEGORY);
      if (categories != null) {
        categoryNames = categories.
                stream().
                filter(category -> ItemElementChildTagNames.CATEGORY.equals(category.getAttributeValue(ItemElementChildTagNames.DOMAIN))).
                map(category -> category.getText()).
                collect(Collectors.toList());
      }
    }
    return categoryNames;
  }

  public List<String> getTags() {
    List<String> tagNames = new ArrayList();
    if (item != null) {
      List<Element> categories = item.getChildren(ItemElementChildTagNames.CATEGORY);
      if (categories != null) {
        tagNames = categories.
                stream().
                filter(category -> ItemElementChildTagNames.TAG.equals(category.getAttributeValue(ItemElementChildTagNames.DOMAIN))).
                map(category -> category.getText()).
                collect(Collectors.toList());
      }
    }
    return tagNames;
  }

}