package demo.blog.graphql.query;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import demo.blog.graphql.custom.Node;

@Component
public class UserQuery implements GraphQLQueryResolver {

	public Node getNode() {
		return null;
	}
}
