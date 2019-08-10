package demo.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.blog.model.User;
import demo.blog.repository.custom.UserRepositoryCustom;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

}
