package com.codeexcursion;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

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
    String postType = attachmentItem.getPostType();
    Assert.assertEquals("Post type did not match attachment", "attachment", postType);    
  }
  
  @Test 
  public void testGetPostDate() {
    String postDate = attachmentItem.getPostDate();
    Assert.assertEquals("Post date did not match attachment", "2012-02-05 21:47:26", postDate);    
  }
  
  @Test 
  public void testGetAttachedFile() {
    String attachedFile = attachmentItem.getAttachedFile();
    Assert.assertEquals("Attached file did not match attachment", "2012/02/network.jpg", attachedFile);    
  }

  
  @Test 
  public void testGetCategories() {
    List<String> categories = postItem.getCategories();
    Assert.assertNotNull("Categories were null.", categories);    
    Assert.assertNotNull("Categories first category was null.", categories.get(0));    
    Assert.assertEquals("Categories first category did not match.", "Tech Article", categories.get(0));    
  }
  
  @Test 
  public void testGetTags() {
    List<String> tags = postItem.getTags();
    Assert.assertNotNull("Categories were null.", tags);    
    Assert.assertNotNull("Categories first category was null.", tags.get(0));    
    Assert.assertEquals("Categories first category did not match.", "authentication", tags.get(0));    
  }
  
  @Test 
  public void testContent() {
    String content = postItem.getContent();
    Assert.assertNotNull("Content were null.", content);    
    
    String testValue = "The last part is execute the authorization check in a controller";
    Assert.assertTrue("Content did not contain:  " + testValue + "\n\n" + content, content.contains(testValue));    
  }  
  
  

}
