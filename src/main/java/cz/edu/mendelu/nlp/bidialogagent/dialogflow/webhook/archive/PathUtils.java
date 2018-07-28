package cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook.archive;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {

	public static String getProjectDir(){
		Path currentRelativePath = Paths.get("");
		String curreDir = currentRelativePath.toAbsolutePath().toString();
		return curreDir;
	}
	
	public static String getTestArchiveDir(){
		return getProjectDir() + "/src/test/resources/archive/";
	}
}
