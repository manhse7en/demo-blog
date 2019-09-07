package demo.blog.repository.custom;

import java.util.List;

import demo.blog.model.Category;

public interface CategoryRepositoryCustom {

	List<Category> categories();

	Category get(String _id);

}
