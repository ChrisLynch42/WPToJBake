/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import com.codeexcursion.document.DocumentTypes;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;
import org.jdom2.Element;

/**
 *
 * @author lynchcs
 */
public class PathBuilder {
  public static final String EXTENSION_SEPERATOR = ".";
  
  private Item item;

  public PathBuilder(Item item) {
    this.item = item;
  }
    
  public boolean makeDirectories() {
    boolean returnValue = true;
    File path = new File(getDirectories());
    if(!path.exists()) {
      returnValue = path.mkdirs();
    }
    return returnValue;
  }
  
  public File getFile() {
    return new File(getDirectories() + File.separator + getFileName());
  }
  
  public Path getPath() {
    return Paths.get(getDirectories() + File.separator + getFileName());
  }  
  
  public String getDirectories() {
    StringJoiner joiner = new StringJoiner(File.separator);
    joiner.add(".");

    String type = item.getPostType();
    if (type != null) {
      joiner.add(type);
    }

    String directoryPath = item.getAttachedFile();
    if (directoryPath != null) {
      String directoryPathParts[] = directoryPath.split("/");

      if (directoryPathParts != null && directoryPathParts.length > 0) {
        for(int i = 0; i < directoryPathParts.length - 1; i++) {
          joiner.add(directoryPathParts[i]);
        }
      }
    }
    return joiner.toString();
  }
  
  public String getFileName() {
    String returnValue = "";
    if(DocumentTypes.ATTACHMENT.equals(item.getPostType())) {
      returnValue = getAttachmentFileName();
    } else {
      returnValue = getOtherFileName();
    }

    return returnValue;
  }
  
  public String getAttachmentFileName() {
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
  
  public String getOtherFileName() {
    String returnValue="";
    returnValue = item.getName() + ".html";
    return returnValue;
  } 

  public String getFileExtension() {
    String returnValue = "";

    String fileName = getFileName();
    if (fileName != null && fileName.contains(EXTENSION_SEPERATOR)) {
      String fileNameParts[] = fileName.split(EXTENSION_SEPERATOR);
      if (fileNameParts != null && fileNameParts.length > 0) {
        returnValue = fileNameParts[fileNameParts.length - 1];
      }
    }
    return returnValue;
  }  

}
