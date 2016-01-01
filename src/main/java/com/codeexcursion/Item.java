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
  private Namespace wordpressNamespace;
  private Namespace contentNamespace;

  public Item(Element item, Namespace wordpressNamespace, Namespace contentNamespace) {
    this.item = item;
    this.wordpressNamespace = wordpressNamespace;
    this.contentNamespace = contentNamespace;
  }

  private String getText(String tagName, Namespace namespace) {
    String returnValue = "";
    if (item != null) {
      returnValue = item.getChildText(tagName, namespace);
    }
    return returnValue;
  }
  
  private String getText(String tagName) {
    return getText(tagName, wordpressNamespace);
  }  

  public String getAttachmentURL() {
    return getText(ItemElementChildTagNames.ATTACHMENT_URL);
  }
  
  public String getContent() {
    return getText(ItemElementChildTagNames.ENCODED,contentNamespace);
  }

  public String getLink() {
    return item.getChildText(ItemElementChildTagNames.LINK);
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
    return item.getChildText(ItemElementChildTagNames.TITLE);
  }

  public String getStatus() {
    return getText(ItemElementChildTagNames.STATUS).replaceAll("publish", "published");
  }
  
  public String getAttachedFile() {
    String returnValue = "";
    if (item != null) {
      List<Element> postMetas = item.getChildren(ItemElementChildTagNames.POST_META, wordpressNamespace);
      if (postMetas != null) {
        returnValue = postMetas.
                stream().
                filter(postMeta -> PostMetaTypes.ATTACHED_FILE.equals(postMeta.getChildText(ItemElementChildTagNames.POST_META_KEY, wordpressNamespace))).
                map(postMeta -> postMeta.getChildText(ItemElementChildTagNames.POST_META_VALUE, wordpressNamespace)).
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
