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
public class ConversionManagerTest extends PathCommon {
  protected ConversionManager conversionManager;
  
  @Before
  public void prepare() {
    setup();
    conversionManager = new ConversionManager(rss);
  }

  @Test 
  public void testTransferAttachment() {
    System.out.println("*****************");
    System.out.println("*****************");
    System.out.println("*****************");
    conversionManager.transfer(attachmentItem);
  }
  
  @Test 
  public void testTransferHTML() {
    System.out.println("*****************");
    System.out.println("*****************");
    System.out.println("*****************");
    conversionManager.transfer(postItem);
  }  
  
}
