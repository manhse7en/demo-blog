package demo.blog.service;

import java.util.List;

import demo.blog.model.Category;

public interface CategoryService {

	Category get(String _id);
	
	Category create(String title);

	List<Category> categories();

}