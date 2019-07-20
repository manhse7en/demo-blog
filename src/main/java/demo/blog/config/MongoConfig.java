package demo.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {

	private String host = "127.0.0.1";
	private int port = 27017;
	private String database = "blog";

	public @Bean MongoClient mongoClient() {
		// final MongoCredential credential =
		// MongoCredential.createCredential(this.username, this.database,
		// this.password.toCharArray());
		// ServerAddress serverAddress = new ServerAddress(this.host, this.port);
		// @SuppressWarnings("serial")
		// MongoClient mongoClient = new MongoClient(serverAddress,new
		// ArrayList<MongoCredential>(){{add(credential);}});
		// return mongoClient;
		return new MongoClient(host, port);
	}

	public @Bean MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), database);
	}

}
