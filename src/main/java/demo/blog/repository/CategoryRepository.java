package demo.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.blog.model.Category;
import demo.blog.repository.custom.CategoryRepositoryCustom;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>, CategoryRepositoryCustom {
	
}
