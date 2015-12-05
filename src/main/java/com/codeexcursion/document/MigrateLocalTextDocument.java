/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion.document;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 *
 * @author lynchcs
 */
public class MigrateLocalTextDocument implements IMigrateDocument {

  private URL url;
  private Path destination;
  private String content;
  private BufferedImage image;

  public MigrateLocalTextDocument(
          String content,
          Path destination) {
    this.destination = destination;
    this.content=content;
  }

  public void transfer() {
    try {
      Files.write(destination, content.getBytes());
    } catch(IOException ioe) {
      System.out.println("Failed to write local test document.  " + destination + "  -- " + ioe.getMessage());
    }
  }

}
