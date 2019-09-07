package demo.blog.graphql.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import demo.blog.model.Category;
import demo.blog.service.CategoryService;

@Component
public class CategoryQuery implements GraphQLQueryResolver {
	@Autowired
	CategoryService categoryService;
	
	public List<Category> categories() {
		return categoryService.categories();
	}
}
