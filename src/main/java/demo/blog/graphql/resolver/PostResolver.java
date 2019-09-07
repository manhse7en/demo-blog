package demo.blog.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import demo.blog.model.Category;
import demo.blog.model.Post;
import demo.blog.model.User;
import demo.blog.service.CategoryService;
import demo.blog.service.UserService;

@Component
public class PostResolver implements GraphQLResolver<Post>{
	
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	
	public User getUser(Post post) {
		return userService.get(post.getId_user());
	}
	
	public Category getCategory(Post post) {
		return categoryService.get(post.getId_category());
	}
}
