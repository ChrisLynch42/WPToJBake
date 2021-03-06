/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
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
  protected Item attachmentItem;
  protected Item postItem;
  protected Element rss;
  
  
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

    
    rss = document.getRootElement();

    Namespace wordpressNamespace = rss.getNamespace("wp");
    Assert.assertNotNull("Wordpress namespace is null.", wordpressNamespace);
    Assert.assertEquals("Wordpress namespace is null.", "wp" , wordpressNamespace.getPrefix());

    Namespace contentNamespace = rss.getNamespace("content");
    Assert.assertNotNull("Content namespace is null.", contentNamespace);
    Assert.assertEquals("Content namespace is null.", "content" , contentNamespace.getPrefix());
   
    List<Element> channels = rss.getChildren(ConversionManager.CHANNEL);
    Assert.assertNotNull("List of channels was null", channels);
    
    Element channel = channels.get(0);
    Assert.assertNotNull("Channel was null", channel);
    
    List<Element> listOfItems = channel.getChildren(ConversionManager.ITEM);
    
    
    
    Optional<Element> firstItem = listOfItems.stream().findFirst();
    Assert.assertTrue("Item element is null!", firstItem.isPresent());    
    attachmentItem = new Item(firstItem.get(), wordpressNamespace, contentNamespace);
/*
    System.out.println("xxxxxxxxxxxxxxxxx");
//    System.out.println(firstItem.get().getChildText(ItemElementChildTagNames.POST_TYPE, Namespace.getNamespace(ItemElementChildTagNames.WORDPRESS_NAMESPACE)));
    System.out.println(firstItem.get().getName());
    System.out.println(firstItem.get().getChildText("post_id", wordpressNamespace));
//    firstItem.get().getChildren().stream().forEach(child -> System.out.println(child.getName()));
    //System.out.println(firstItem.get().getChild("post_id", wordpressNamespace));
    System.out.println("xxxxxxxxxxxxxxxxx");
  */  
    Optional<Element> postElementItem = listOfItems.
            stream().
            filter(item -> "246".equals(item.getChildText(ItemElementChildTagNames.DOCUMENT_ID, wordpressNamespace))).
            findFirst();
    
    Assert.assertTrue("Post element item element is null!", postElementItem.isPresent());    
    postItem = new Item(postElementItem.get(), wordpressNamespace, contentNamespace);
  }  
}
