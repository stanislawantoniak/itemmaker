package pl.essay.languages;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.essay.toolbox.CsvHelper;


public class Translator {

	protected static final Logger logger = LoggerFactory.getLogger(Translator.class);

	private Map<String,String> translations = new HashMap<String,String>();

	private String translationFile;
	private String language;

	public void setLanguage(String l){
		this.language = l;
	}
	public void setTranslationFile(String l){
		logger.info("setting translation file for language "+this.language+": "+ l );
		this.translationFile = l;
	}

	public void init() {
		logger.info("getting translation file for language "+this.language+": "+this.translationFile );
		
		Map<String, String[]> tempMap = CsvHelper.getMapStringStringArrayFromCsvFile(this.translationFile);

		for (Map.Entry<String,String[]> lineCsv: tempMap.entrySet())
			this.translations.put(lineCsv.getValue()[0], lineCsv.getValue()[1]);
		logger.info("translation file for laguage "+this.language+" processed, "+this.translations.size()+" translation records read");
	}

	public Map<String,String> getTranslations(){
		return this.translations;
	}
}
