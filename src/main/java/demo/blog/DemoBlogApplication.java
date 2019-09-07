package demo.blog;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoBlogApplication {
	
	public static void main(String[] args) {
		String test = "19212345";
		int a = Integer.valueOf(test.substring(0, test.length() - 5));
		System.out.println(a);
	
		SpringApplication.run(DemoBlogApplication.class, args);
	}

}
