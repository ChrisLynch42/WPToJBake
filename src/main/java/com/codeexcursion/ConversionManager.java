/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import com.codeexcursion.path.AttachmentPathBuilder;
import com.codeexcursion.document.DocumentTypes;
import com.codeexcursion.document.IMigrateDocument;
import com.codeexcursion.document.MigrateAttachmentDocument;
import com.codeexcursion.document.MigrateLocalTextDocument;
import com.codeexcursion.path.HTMLPathBuilder;
import com.codeexcursion.path.IPathBuilder;
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
  public final static String CONTENT_NAMESPACE_PREFIX = "content";

  private Element rss;

  public ConversionManager(Element rss) {
    this.rss = rss;
  }

  public void convert() {
    List<Element> itemNodes = getItems();
    if (itemNodes == null) {
      System.out.println("Failed to retrieve documents from WordPress XML Export File.");
      return;
    }

    for (Element itemNode : itemNodes) {
      if (itemNode != null) {
        Item item = new Item(
                itemNode,
                rss.getNamespace(WORDPRESS_NAMESPACE_PREFIX),
                rss.getNamespace(CONTENT_NAMESPACE_PREFIX)
        );
        if (item != null) {
          transfer(item);
        }
      }
    }
  }

  public void transfer(Item item) {
    System.out.println("Conversion manager transfer start");
    IPathBuilder pathBuilder = null;

    if (DocumentTypes.ATTACHMENT.equals(item.getPostType())) {
      pathBuilder = new AttachmentPathBuilder(item);
    } else {
      pathBuilder = new HTMLPathBuilder(item);

    }

    if (pathBuilder.makeDirectories()) {
      System.out.println("Conversion manager transfer directories were made.");
      IMigrateDocument migrateDocument = null;

      if (DocumentTypes.ATTACHMENT.equals(item.getPostType())) {
        System.out.println("Conversion manager item is attachment");
        URL url = stringToURL(item.getAttachmentURL());
        if (url != null) {
          System.out.println("Conversion manager transfer URL is not null");
          migrateDocument
                  = new MigrateAttachmentDocument(
                          url,
                          pathBuilder.getFile());
        } else {
          System.out.println("Document url was not valid:  " + item.getAttachmentURL());
        }

      } else {
        System.out.println("Conversion manager item is post/page");
        migrateDocument = new MigrateLocalTextDocument(item.getContent(), pathBuilder.getPath());
      }
      if (migrateDocument != null) {
        migrateDocument.transfer();
      }
    }
  }

  private URL stringToURL(String urlInput) {
    URL url = null;
    try {
      url = new URL(urlInput);
    } catch (MalformedURLException mue) {
      System.out.println("Document URL malformed.  \n" + mue.getMessage());
    }
    return url;
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
