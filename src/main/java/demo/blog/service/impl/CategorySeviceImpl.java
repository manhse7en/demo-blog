package demo.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.blog.model.Category;
import demo.blog.repository.CategoryRepository;
import demo.blog.service.CategoryService;

@Service
public class CategorySeviceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category get(String _id) {
		return categoryRepository.get(_id);
	}
	
	@Override
	public Category create(String title) {
		return categoryRepository.save(new Category(title));
	}
	
	@Override
	public List<Category> categories() {
		return categoryRepository.categories();
	}
}
