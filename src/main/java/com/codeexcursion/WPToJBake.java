package com.codeexcursion;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Hello world!
 *
 */
public class WPToJBake {

  public static void main(String[] args) {

    String filePath = "";
    File inputFile = null;

    if (args[0] == null) {
      System.out.println("Command line is java com.codeexcursion.WPToJBake <path to file>.");
      return;
    } else {
      filePath = args[0];
      inputFile = new File(filePath);
      if (inputFile == null && !inputFile.isFile()) {
        return;
      }
    }

    WPToJBake wpToJBake = new WPToJBake();

    wpToJBake.convertFile(inputFile);

  }

  public void convertFile(File inputFile) {
    String errors = null;
    Document document = getDocument(inputFile);

    if (document == null) {
      return;
    }

    
    List<Element> items = getItems();
    printFiles(channel);

  }

  public List<Element> getItems(Document document) {
    List<Element> items = null;

    Element rss = document.getRootElement();

    if (rss == null) {
      System.out.println("Failed to retrieve rss node.");
    } else {
      Element channel = rss.getChild("channel");
      if (channel == null) {
        System.out.println("Failed to retrieve channel node.");
      }
    }
    return items;
  }

  public Document getDocument(File inputFile) {
    SAXBuilder jdomBuilder = new SAXBuilder();
    Document jdomDocument = null;
    try {
      jdomDocument = jdomBuilder.build(inputFile);
    } catch (JDOMException jde) {
      System.out.println(jde.toString());
      jdomDocument = null;
    } catch (IOException ioe) {
      System.out.println(ioe.toString());
      jdomDocument = null;
    }
    return jdomDocument;
  }

  public void printFiles(Element rootNode) {
    List<Element> items = rootNode.getChildren("item");
    for (Element item : items) {
      System.out.println(item.getChild("title").getText());
    }
  }

}
