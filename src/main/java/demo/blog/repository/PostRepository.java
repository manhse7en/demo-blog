package demo.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.blog.model.Post;
import demo.blog.repository.custom.PostRepositoryCustom;

@Repository
public interface PostRepository extends MongoRepository<Post, String>, PostRepositoryCustom {

}
