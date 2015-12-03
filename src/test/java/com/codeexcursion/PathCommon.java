/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;

/**
 *
 * @author lynchcs
 */
public class PathCommon {
  protected String testFilePath = "./src/test/java/com/codeexcursion/wp.xml";
  protected File testFile;
  protected Document document;
  protected Item item;
  
  
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
  
  public void setup() {
    testFile = new File(testFilePath);
    if(!testFile.isFile()) {
      fail("Unable to open file = " + testFilePath);
    }
    document = getDocument(testFile);
    Element rss = document.getRootElement();
    List<Element> items = rss.getChildren(WPToJBake.LIST_OF_ITEMS);
    Element itemElement = items.get(0);
    Assert.assertNotNull("Item element is null!", itemElement);    
    item = new Item(itemElement);
  }  
}
