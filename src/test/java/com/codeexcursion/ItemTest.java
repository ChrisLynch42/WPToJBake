package com.codeexcursion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class ItemTest extends PathCommon {

  
  
  @Before
  public void prepare() {
    setup();    
  }

  
  @Test 
  public void testGetPostType() {
    String postType = attachmentItem.getPostDate();
    Assert.assertEquals("Base path did not match attachment", "attachement", postType);    
  }
  
  @Test 
  public void testGetPostDate() {
    String postDate = attachmentItem.getPostDate();
    Assert.assertEquals("Base path did not match attachment", "2012-02-05 21:47:26", postDate);    
  }
  
  @Test 
  public void testGetAttachedFile() {
    String attachedFile = attachmentItem.getAttachedFile();
    Assert.assertEquals("Base path did not match attachment", "2012/02/network.jpg", attachedFile);    
  }

  
  @Test 
  public void testGetCategories() {
    List<String> categories = postItem.getCategories();
    Assert.assertNotNull("Categories were null.", categories);    
    Assert.assertNotNull("Categories first category was null.", categories.get(0));    
    Assert.assertEquals("Categories first category did not match.", "technical-article", categories.get(0));    
  }
  
  @Test 
  public void testGetTags() {
    List<String> tags = postItem.getCategories();
    Assert.assertNotNull("Categories were null.", tags);    
    Assert.assertNotNull("Categories first category was null.", tags.get(0));    
    Assert.assertEquals("Categories first category did not match.", "authentication", tags.get(0));    
  }
  
  

}
