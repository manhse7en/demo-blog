package demo.blog.graphql.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import demo.blog.model.Post;
import demo.blog.service.PostService;

@Component
public class PostQuery implements GraphQLQueryResolver {
	
	@Autowired
	PostService postService;
	
	public Post getPost(String idPost) {
		return postService.get(idPost);
	}
}
