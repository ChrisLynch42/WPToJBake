/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import java.util.StringJoiner;
import org.jdom2.Element;

/**
 *
 * @author lynchcs
 */
public class PathBuilder {

  private Item item;

  public PathBuilder(Item item) {
    this.item = item;
  }
    
  public String getPath() {
    StringJoiner joiner = new StringJoiner("/");

    String type = item.getPostType();
    if (type != null) {
      joiner.add(type);
    }

    String pathFileName = item.getAttachedFile();
    if (pathFileName != null) {
      String pathFileNameParts[] = pathFileName.split("/");

      if (pathFileNameParts != null && pathFileNameParts.length > 0) {
        for(int i = 0; i < pathFileNameParts.length - 2; i++) {
          joiner.add(pathFileNameParts[i]);
        }
      }
    }
    return joiner.toString();
  }
  
  public String getFileName() {
    String returnValue = "";

    String pathFileName = item.getAttachedFile();
    if (pathFileName != null) {
      String pathFileNameParts[] = pathFileName.split("/");
      if (pathFileNameParts != null && pathFileNameParts.length > 0) {
        returnValue = pathFileNameParts[pathFileNameParts.length - 1];
      }
    }
    return returnValue;
  }  

}
