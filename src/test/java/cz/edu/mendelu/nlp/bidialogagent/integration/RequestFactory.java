package cz.edu.mendelu.nlp.bidialogagent.integration;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.EntityTypeCache;
import cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook.archive.PathUtils;
import cz.edu.mendelu.nlp.bidialogagent.util.Utils;
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;

public class RequestFactory {
	private ObjectMapper mapper = new ObjectMapper();
	
	private final Logger log = LoggerFactory.getLogger(EntityTypeCache.class);

	
	public Stream<WebHookRequestJson> feedRequestsFromArchive(){
		try {
			return Utils.convertIteratorToStream(Files.newDirectoryStream(Paths.get(PathUtils.getTestArchiveDir()))
					.iterator())
					.map(file -> fromFile(file))
					.filter(Objects::nonNull)
					;
		} catch (IOException e) {
			log.error("Error getting requests from Archive");
			throw new RuntimeException(e);
		}
	}
	
	public WebHookRequestJson fromFile(Object file){
		Path path =(Path) file;
		try {
			String content = FileUtils.readFileToString(path.toAbsolutePath().toFile(), Charset.defaultCharset());
			return mapper.readValue(content, WebHookRequestJson.class);
		} catch (IOException e) {
			log.info("Couldnot create request from file "+path.toAbsolutePath().toString());
			return null;
		}
	}

}
