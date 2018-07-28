package cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.cloud.dialogflow.v2.EntityType;
import com.google.cloud.dialogflow.v2.EntityTypesClient;
import com.google.cloud.dialogflow.v2.ProjectAgentName;

@Component("entityTypeCache")
public class EntityTypeCache extends AbstractCache<EntityType> {
	
	private final Logger log = LoggerFactory.getLogger(EntityTypeCache.class);


	public Map<String, EntityType> load()  {
		try {
		try (EntityTypesClient entityTypesClient = EntityTypesClient.create()) {
			// Set the project agent name using the projectID (my-project-id)
			ProjectAgentName parent = ProjectAgentName.of(System.getenv("GOOGLE_CLOUD_PROJECT"));
			
			// Performs the list entity types request
			return this.convertIteratorToStream(entityTypesClient.listEntityTypes(parent)
				.iterateAll()
				.iterator())
				.collect(Collectors.toMap(EntityType::getDisplayName, Function.identity()));
			
		}
		}
		catch (Exception e){
			log.error("Error loading Entity type Cache");
			throw new RuntimeException(e);
		}
	}

}
