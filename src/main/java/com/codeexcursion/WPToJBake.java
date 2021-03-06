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

    wpToJBake.wpXMLToFiles(inputFile);

  }

  public void wpXMLToFiles(File inputFile) {
    String errors = null;
    Document document = getDocument(inputFile);

    if (document == null) {
      return;
    }
    
    Element rss = document.getRootElement();
    if(rss == null) {
      System.out.println("Root element missing!");
      return;
    }
    
    ConversionManager conversionManager = new ConversionManager(rss);
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

}
