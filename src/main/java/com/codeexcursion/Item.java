/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author lynchcs
 */
public class Item {

  private Element item;

  public Item(Element item) {
    this.item = item;
    if(item == null) {
      System.out.println("Item was null!");
    }
  }

  public String getPostType() {
    String returnValue = "";
    if (item != null) {
      returnValue = item.getChildText(ItemElements.POST_TYPE);
    }
    return returnValue;
  }

  public String getPostDate() {
    String returnValue = "";
    if (item != null) {
      returnValue = item.getChildText(ItemElements.POST_DATE);
    }
    return returnValue;
  }

  public String getAttachedFile() {
    String returnValue = "";
    if (item != null) {
      List<Element> postMetas = item.getChildren(ItemElements.POST_META);
      for (Element postMeta : postMetas) {
        if (postMeta != null && postMeta.getChildText(ItemElements.POST_META_KEY) == PostMetaTypes.ATTACHED_FILE) {
          returnValue = postMeta.getChildText(ItemElements.POST_META_VALUE);
          break;
        }
      }
    }
    return returnValue;
  }

}
