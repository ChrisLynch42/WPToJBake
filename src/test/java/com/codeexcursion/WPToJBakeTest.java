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
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class WPToJBakeTest {

  private String testFilePath = "./src/test/java/com/codeexcursion/wp.xml";
  private File testFile;
  private WPToJBake converter;
  
  @Before
  public void prepare() {
    testFile = new File(testFilePath);
    if(!testFile.isFile()) {
      fail("Unable to open file = " + testFilePath);
    }
    converter = new WPToJBake();
  }


  @Test 
  public void testGetDocument() {
    Document document = converter.getDocument(testFile);
    Assert.assertNotNull("Document was null!",document);
  }

  @Test 
  public void testWPXMLToFiles() {
    converter.wpXMLToFiles(testFile);    
  }
  
  
}
