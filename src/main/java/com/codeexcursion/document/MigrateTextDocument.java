/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion.document;

import com.codeexcursion.Item;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;
import javax.imageio.ImageIO;

/**
 *
 * @author lynchcs
 */
public class MigrateTextDocument implements IMigrateDocument {

  private URL url;
  private Path destination;
  private Item document;  

  public MigrateTextDocument(
          Item document,
          Path destination) {
    this.destination = destination;
    this.document=document;
  }

  public void transfer() {
    StringBuilder content = new StringBuilder();
    
    String tags = String.join(", ", document.getTags());
    String categories = String.join(", ", document.getCategories());
    
    appendKeyValue(content, JBakeConstants.HEADER_TYPE, document.getPostType());
    appendKeyValue(content, JBakeConstants.HEADER_TITLE, document.getTitle());
    appendKeyValue(content, JBakeConstants.HEADER_DATE, document.getPostDate());
    appendKeyValue(content, JBakeConstants.HEADER_TAGS, tags);
    appendKeyValue(content, JBakeConstants.HEADER_STATUS, document.getStatus());
    appendKeyValue(content, JBakeConstants.HEADER_CATEGORY, categories);
    appendLine(content,JBakeConstants.HEADER_DELIMITER);
    appendLine(content,document.getContent());

    try {
      Files.write(destination, content.toString().getBytes());
    } catch(IOException ioe) {
      System.out.println("Failed to write local test document.  " + destination + "  -- " + ioe.getMessage());
    }
  }
  
  private void appendKeyValue(StringBuilder content, String key, String value) {
    content.append(key);
    content.append(JBakeConstants.HEADER_VALUE_SEPERATOR);
    content.append(value);
    content.append(System.lineSeparator());
  }
  
  private void appendLine(StringBuilder content, String value) {
    content.append(value);
    content.append(System.lineSeparator());
  }  

}
