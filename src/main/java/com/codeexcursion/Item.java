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

/**
 *
 * @author lynchcs
 */
public class Item {

  private Element item;

  public Item(Element item) {
    this.item = item;
    if (item == null) {
      System.out.println("Item was null!");
    }
  }

  public String getText(String tagName) {
    String returnValue = "";
    if (item != null) {
      returnValue = item.getChildText(tagName);
    }
    return returnValue;
  }

  public String getPostType() {
    return getText(ItemElementTypes.POST_TYPE);
  }

  public String getPostDate() {
    return getText(ItemElementTypes.POST_DATE);
  }

  public String getTitle() {
    return getText(ItemElementTypes.TITLE);
  }

  public String getStatus() {
    return getText(ItemElementTypes.STATUS);
  }
  
  public String getContent() {
    return StringEscapeUtils.unescapeHtml(getText(ItemElementTypes.CONTENT));
  }  

  public String getAttachedFile() {
    String returnValue = "";
    if (item != null) {
      List<Element> postMetas = item.getChildren(ItemElementTypes.POST_META);
      if (postMetas != null) {
        returnValue = postMetas.
                stream().
                filter(postMeta -> PostMetaTypes.ATTACHED_FILE.equals(postMeta.getChildText(ItemElementTypes.POST_META_KEY))).
                map(postMeta -> postMeta.getChildText(ItemElementTypes.POST_META_VALUE)).
                findFirst().toString();
      }
    }
    return returnValue;
  }

  public List<String> getCategories() {
    List<String> categoryNames = new ArrayList();
    if (item != null) {
      List<Element> categories = item.getChildren(ItemElementTypes.CATEGORY);
      if (categories != null) {
        categoryNames = categories.
                stream().
                filter(category -> ItemElementTypes.CATEGORY.equals(category.getAttributeValue(ItemElementTypes.DOMAIN))).
                map(category -> category.getText()).
                collect(Collectors.toList());
      }
    }
    return categoryNames;
  }

  public List<String> getTags() {
    List<String> tagNames = new ArrayList();
    if (item != null) {
      List<Element> categories = item.getChildren(ItemElementTypes.CATEGORY);
      if (categories != null) {
        tagNames = categories.
                stream().
                filter(category -> ItemElementTypes.TAG.equals(category.getAttributeValue(ItemElementTypes.DOMAIN))).
                map(category -> category.getText()).
                collect(Collectors.toList());
      }
    }
    return tagNames;
  }

}
