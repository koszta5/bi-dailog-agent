package cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook.archive;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.EntityTypeCache;
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;
@Component
public class SimpleArchiver implements RequestArchiver {

	private ObjectMapper marshaller = new ObjectMapper();
	
	private final Logger log = LoggerFactory.getLogger(EntityTypeCache.class);

	private Map<String, WebHookRequestJson> archiveToBeFlushed = new HashMap<>();
	
	@Override
	public void archive(WebHookRequestJson request) {
		String fileName = "request_archive_"+ new Date().getTime() +".json";
		archiveToBeFlushed.put(fileName, request);
	}

	public void doArchive(String fileName, WebHookRequestJson archiveMe){
		
		String file = PathUtils.getTestArchiveDir() + fileName;
		try {
			FileUtils.writeStringToFile(new File(file), marshaller.writeValueAsString(archiveMe), Charset.defaultCharset());
		} catch (Exception e) {
			log.warn("Could not log correctly for file "+file);
		}
	}
	

	@Override
	public void run() {
		archiveToBeFlushed
			.forEach(this::doArchive);
		archiveToBeFlushed.clear();
		
	}

}
