package demo.blog.repository.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import demo.blog.config.MongoConfig;
import demo.blog.graphql.frm.FrmNewUser;
import demo.blog.graphql.frm.FrmUpUser;
import demo.blog.graphql.frm.Login;
import demo.blog.model.User;
import demo.blog.repository.custom.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {
	// Ioc Container
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@Override
	public User get(String _id) {
		Query query = new Query(Criteria.where("_id").is(_id));
		return mongoOperation.findOne(query, User.class);
	}

	@Override
	public User create(FrmNewUser newUser) {
		return mongoOperation.save(new User(newUser));
	}

	@Override
	public User login(Login login) {
		// ORM
		new Criteria();
		Criteria criteria = Criteria.where("username").is(login.getUsername()).and("password").is(login.getPassword());
		Query query = new Query(criteria);
		return mongoOperation.findOne(query, User.class);
	}

	@Override
	public boolean update(String _id, FrmUpUser upUser) {
		Query query = new Query(Criteria.where("_id").is(_id));
		Update update = new Update();
		if (!upUser.getName().equals(""))
			update.set("name", upUser.getName());
		if (!upUser.getEmail().equals(""))
			update.set("email", upUser.getEmail());
		if (!upUser.getPassword().equals(""))
			update.set("password", upUser.getPassword());
		if (update.getUpdateObject().size() > 0)
			return mongoOperation.updateFirst(query, update, User.class).isModifiedCountAvailable();
		return false;
	}
}
