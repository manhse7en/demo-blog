package demo.blog.repository.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import demo.blog.config.MongoConfig;
import demo.blog.graphql.frm.FrmPost;
import demo.blog.model.Post;
import demo.blog.repository.custom.PostRepositoryCustom;

public class PostRepositoryImpl implements PostRepositoryCustom {
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@Override
	public Post get(String _id) {
		Query query = new Query(Criteria.where("_id").is(_id));
		return mongoOperation.findOne(query, Post.class);
	}

	@Override
	public boolean update(String idPost, FrmPost upPost) {
		Query query = new Query(Criteria.where("_id").is(idPost));
		Update update = new Update();
		if (!upPost.getContent().equals(""))
			update.set("content", upPost.getContent());
		if (!upPost.getId_category().equals(""))
			update.set("id_category", upPost.getId_category());
		if (update.getUpdateObject().size() > 0)
			return mongoOperation.updateFirst(query, update, Post.class).isModifiedCountAvailable();
		return false;
	}

	@Override
	public boolean remove(String idPost) {
		Query query = new Query(Criteria.where("_id").is(idPost));
		return mongoOperation.remove(query, Post.class).getDeletedCount() > 0 ? true : false;
	}
}
