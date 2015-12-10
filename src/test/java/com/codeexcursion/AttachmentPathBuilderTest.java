package com.codeexcursion;

import com.codeexcursion.path.AttachmentPathBuilder;
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
public class AttachmentPathBuilderTest extends PathCommon {
  protected AttachmentPathBuilder pathBuilder;
  
  @Before
  public void prepare() {
    setup();
    pathBuilder = new AttachmentPathBuilder(attachmentItem);
  }

  @Test 
  public void testGetDirectories() {
    String path = pathBuilder.getDirectories();
    Assert.assertEquals("Directories did not match.", "./attachment/2012/02", path);    
  }
  
  @Test 
  public void testGetFileName() {
    String fileName = pathBuilder.getFileName();
    Assert.assertEquals("File name did not match.", "network.jpg", fileName);    
  }  
  
}
