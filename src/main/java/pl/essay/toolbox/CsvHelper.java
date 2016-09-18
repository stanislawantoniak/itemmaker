package pl.essay.toolbox;

import java.io.*;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;

import pl.essay.languages.Translator;

public class CsvHelper {
	
	protected static final Logger logger = LoggerFactory.getLogger(CsvHelper.class);
	
	public static String getStringFromInputStream(InputStream csv) throws IOException {
		logger.info("break helper: 1" + csv);
		String content = IOUtils.toString(csv,"UTF-8");
		return content;
	}
	
	public static Map<String, String[]> getMapFromCsv(String fileContent, int indexForKey ) throws IOException{

		List<String[]> csvSplitted = new ArrayList<String[]>();

		CSVReader reader = new CSVReader(new StringReader(fileContent),',' ) ;
		csvSplitted = reader.readAll();
		reader.close();
		
		Map<String, String[]> csv = new HashMap<String, String[]>();
		Iterator<String[]> csvIterator = csvSplitted.iterator();
		while (csvIterator.hasNext()) {
			String[] line = csvIterator.next();
			csv.put(line[indexForKey], line);
		}
		return csv;
	}
	
	public static Map<String, String[]> getMapStringStringArrayFromCsvFile(String file) {
		InputStream csv = 
				(InputStream) Translator.class.getResourceAsStream(file);
		
		logger.info("break: 2" );
		
		String content = "";
		try {
			content = CsvHelper.getStringFromInputStream(csv);
		} catch (IOException e1) {
			logger.error("", e1);
		}
		
		Map<String, String[]> tempMap = new HashMap<String,String[]>();
		try {
			tempMap = CsvHelper.getMapFromCsv(content, 0);
		} catch (IOException e) {
			logger.error("", e);
		}
		if (csv != null)
			try {
				csv.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		return tempMap;
	}
}
