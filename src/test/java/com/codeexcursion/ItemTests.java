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
public class ItemTests extends PathCommon {

  
  
  @Before
  public void prepare() {
    setup();    
  }

  
  @Test 
  public void testGetPostType() {
    String postType = item.getPostDate();
    Assert.assertEquals("Base path did not match attachment", "attachement", postType);    
  }
  
  @Test 
  public void testGetPostDate() {
    String postDate = item.getPostDate();
    Assert.assertEquals("Base path did not match attachment", "2012-02-05 21:47:26", postDate);    
  }
  
  @Test 
  public void testGetAttachedFile() {
    String attachedFile = item.getAttachedFile();
    Assert.assertEquals("Base path did not match attachment", "2012/02/network.jpg", attachedFile);    
  }
  
  
  

}
