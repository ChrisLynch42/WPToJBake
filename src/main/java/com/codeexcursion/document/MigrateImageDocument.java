/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion.document;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author lynchcs
 */
public class MigrateImageDocument implements IMigrateDocument {
  private URL url;
  private File destination; 
  private String fileType;
  private BufferedImage image;
  
  public MigrateImageDocument(
          URL url, 
          File destination, 
          String fileType) {
    this.url=url;
    this.destination=destination;
    this.fileType=fileType;
  }
  
  
  public void transfer() {
    try {
      image = ImageIO.read(url);
    } catch(IOException ioe) {
      System.out.println("Unable to read image:  " + url.toString());
    }
    
    
    try {
      ImageIO.write(image, fileType, destination);
    } catch(IOException ioe) {
      System.out.println("Unable to write image:  " + destination);      
    }    
  }
  
}
