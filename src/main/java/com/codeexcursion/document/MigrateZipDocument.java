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
import javax.imageio.ImageIO;

/**
 *
 * @author lynchcs
 */
public class MigrateZipDocument implements IMigrateDocument {

  private URL url;
  private File destination;
  private String fileType;
  private BufferedImage image;

  public MigrateZipDocument(
          URL url,
          File destination,
          String fileType) {
    this.url = url;
    this.destination = destination;
    this.fileType = fileType;
  }

  public void transfer() {
    ReadableByteChannel rbc = null;
    try {
      rbc = Channels.newChannel(url.openStream());
    } catch (IOException ioe) {
      System.out.println("Failure opening url stream.");
    }
    FileOutputStream fos = null;
    if (rbc != null && rbc.isOpen()) {
      try {
        fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
      } catch (IOException ioe) {
        System.out.println("Failure reading zip document.");
      }
    }
  }

}
