package com.codeexcursion;

import com.codeexcursion.path.AttachmentPathBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

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
    Assert.assertEquals("Directories did not match.", "./assets/2012/02", path);    
  }
  
  @Test 
  public void testGetFileName() {
    String fileName = pathBuilder.getFileName();
    Assert.assertEquals("File name did not match.", "network.jpg", fileName);    
  }  
  
}
