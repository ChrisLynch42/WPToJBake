/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import com.codeexcursion.document.DocumentTypes;
import com.codeexcursion.document.IMigrateDocument;
import com.codeexcursion.document.MigrateCompressedDocument;
import com.codeexcursion.document.MigrateImageDocument;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author chris
 */
public class ConversionManager {

  public final static String CHANNEL = "channel";
  public final static String ITEM = "item";
  public final static String WORDPRESS_NAMESPACE_PREFIX = "wp";

  private Element rss;

  public ConversionManager(Element rss) {
    this.rss = rss;
  }

  private void convert() {
    List<Element> itemNodes = getItems();
    if (itemNodes == null) {
      System.out.println("Failed to retrieve documents from WordPress XML Export File.");
      return;
    }

    for (Element itemNode : itemNodes) {
      if (itemNode != null) {
        Item item = new Item(itemNode, rss.getNamespace(WORDPRESS_NAMESPACE_PREFIX));
      }
    }
  }

  private void transfer(Item item) {
    PathBuilder pathBuilder = new PathBuilder(item);

    if (pathBuilder.makeDirectories()) {
      IMigrateDocument migrateDocument = null;

      if (DocumentTypes.ATTACHMENT.equals(item.getPostType())) {
        URL url = stringToURL(item.getAttachmentURL());
        if (url != null) {
          if (isACompressedFile(item.getAttachedFile())) {
            migrateDocument
              = new MigrateCompressedDocument(
                url,
                pathBuilder.getFile());
          } else {
            migrateDocument
              = new MigrateImageDocument(
                url,
                pathBuilder.getFile(),
                pathBuilder.getFileExtension());
            
          }
        } else {

        }

      } else {

      }
      if(migrateDocument != null) {
        migrateDocument.transfer();
      }
    }
  }

  public URL stringToURL(String urlInput) {
    URL url = null;
    try {
      url = new URL(urlInput);
    } catch (MalformedURLException mue) {
      System.out.println("Document URL malformed.  \n" + mue.getMessage());
    }
    return url;
  }

  public boolean isACompressedFile(String inputToTest) {
    boolean returnValue = false;
    if (inputToTest != null) {
      returnValue = DocumentTypes.COMPRESSED_EXTENSIONS.stream().anyMatch(extension -> inputToTest.endsWith(extension));
    }
    return returnValue;
  }

  public List<Element> getItems() {
    List<Element> items = null;

    Element channel = rss.getChild(CHANNEL);
    if (channel == null) {
      System.out.println("Failed to retrieve channel node.");
    } else {
      items = channel.getChildren("item");
    }
    return items;
  }

}
