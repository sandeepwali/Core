package com.solumesl.aims.saas.adapter.service.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.solumesl.aims.saas.adapter.constants.CommonConstants;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Service
public class CSVFileHandlingService   {

	private static Logger logger = LoggerFactory.getLogger(CSVFileHandlingService.class);

	@Value("${solum.saas.server.temppath:}")
	private String tempPath;

	private CsvParserSettings parserSettings;  

	private CsvParser parser;

	@PostConstruct
	private void postConstruct() {
		CsvFormat parserFormat = new CsvFormat();

		parserFormat.setDelimiter(';');

		parserSettings = new CsvParserSettings();

		parserSettings.setFormat(parserFormat);

		parserSettings.getFormat().setLineSeparator("\n");

		parser = new CsvParser(parserSettings);

	}



	public List<String[]> processCSVFile(MultipartFile multipartFile) throws Exception {

		List<String[]> allRows = null;
		try {

			File tempFile = prepareTempFile();

			convertMultipartFile(multipartFile, tempFile);

			allRows = readDataFromCSV(tempFile);

		}catch(Exception e) {
			logger.error("Process file --> Error in Processing CSV File");
			throw e;
		}
		return allRows;
	}
	public List<String[]> processCSVFile(byte[] bytes) throws Exception {

		List<String[]> allRows = null;
		try {

			File tempFile = prepareTempFile();
			 try {
		            OutputStream os = new FileOutputStream(tempFile);
		 
		            os.write(bytes);
		           
		            os.close();
		            
		        }catch (Exception e) {
		        	logger.error("Process file --> Error in Processing CSV File");
		        	throw e;
				}


			allRows = readDataFromCSV(tempFile);

		}catch(Exception e) {
			logger.error("Process file --> Error in Processing CSV File");
			throw e;
		}
		return allRows;
	}

	private List<String[]> readDataFromCSV(File tempFile) throws FileNotFoundException {

		List<String[]> allRows = parser.parseAll(new FileReader(tempFile));

		return allRows;
	}


	private void convertMultipartFile(MultipartFile multipartFile, File tempFile) throws  Exception {

		Objects.requireNonNull(multipartFile); 

		Objects.requireNonNull(tempFile);

		multipartFile.transferTo(tempFile);

	}



	private File prepareTempFile() {
		
		String tempName = String.valueOf(System.currentTimeMillis()) ;
		
		File tempFile = new File(tempPath.concat(tempName).concat(CommonConstants.CSV_FILE_EXTENSION));
		
		return tempFile;
	}







}
