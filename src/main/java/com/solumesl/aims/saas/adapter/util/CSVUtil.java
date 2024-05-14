package com.solumesl.aims.saas.adapter.util;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class CSVUtil {
	 
			 public static final String[] TYPES = new String[] {"text/csv","application/vnd.ms-excel"};
	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (!Arrays.asList(TYPES).contains(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
}
