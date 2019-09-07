package demo.blog.repository.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import demo.blog.config.MongoConfig;
import demo.blog.model.Category;
import demo.blog.repository.custom.CategoryRepositoryCustom;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@Override
	public Category get(String _id) {
		Query query = new Query(Criteria.where("_id").is(_id));
		return mongoOperation.findOne(query, Category.class);
	}

	@Override
	public List<Category> categories() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "_id"));
		return mongoOperation.find(query, Category.class);
	}
}
