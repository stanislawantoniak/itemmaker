package pl.essay.languages;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

//import pl.modago.FileManipulator.FileManipulator;
//import pl.modago.PIP.HelperForMapStringStringArray;
//import pl.modago.PLogger.PLogger;

public class Translator {

	protected static final Logger logger = LoggerFactory.getLogger(Translator.class);

	private Map<String,String> translations = new HashMap<String,String>();

	private String translationFile;
	private String language;

	public void setLanguage(String l){
		this.language = l;
	}

	public void setTranslationFile(String f){
		this.translationFile = f;
		logger.info("setting translation file for laguage "+this.language+": "+this.translationFile );
		//reusing classes from modago project


		//String fileContent = FileManipulator.readFileAsString(this.translationFile, new PLogger(Translator.class) );
		final DefaultResourceLoader loader = new DefaultResourceLoader();  
		Resource resource = loader.getResource("classpath:"+this.translationFile);           
		try {
			File file = resource.getFile();
			logger.info("path to treansaltions file: "+file.getPath());
			//Map<String,String[]> tempMap = HelperForMapStringStringArray.getMapFromCsv(fileContent, 0);
			//for (Map.Entry<String,String[]> line: tempMap.entrySet())
			//	this.translations.put(line.getValue()[0], line.getValue()[1]);
			logger.info("translation file for laguage "+this.language+" processed, "+this.translations.size()+" translation records read");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("problem reading translations file "+this.translationFile, e);
		}		

	}
	public Map<String,String> getTranslations(){
		return this.translations;
	}
}
