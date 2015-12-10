/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeexcursion.path;

import com.codeexcursion.document.DocumentTypes;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author lynchcs
 */
public interface IPathBuilder {
  
  public boolean makeDirectories();
  
  public File getFile();
  
  public Path getPath();
  
  public String getDirectories();
  
  public String getFileName();
  
}
