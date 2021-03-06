package com.itbarxproject.common;

public class FileAttribute {
	  private String fullPath;
	  private char pathSeparator, extensionSeparator;

	  public FileAttribute(String str, char sep, char ext) {
	    fullPath = str;
	    pathSeparator = sep;
	    extensionSeparator = ext;
	  }

	  public String extension() {
	    int dot = fullPath.lastIndexOf(extensionSeparator);
	    return fullPath.substring(dot + 1);
	  }

	  public String filenameWithExtension() { // gets filename without extension
		    String fileName = filename()+"."+extension();
		    return fileName;
		  }
	  
	  public String filename() { // gets filename without extension
	    int dot = fullPath.lastIndexOf(extensionSeparator);
	    int sep = fullPath.lastIndexOf(pathSeparator);
	    return fullPath.substring(sep + 1, dot);
	  }

	  public String path() {
	    int sep = fullPath.lastIndexOf(pathSeparator);
	    return fullPath.substring(0, sep);
	  }
}
