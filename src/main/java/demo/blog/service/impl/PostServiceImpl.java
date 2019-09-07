package demo.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.blog.graphql.frm.FrmPost;
import demo.blog.model.Post;
import demo.blog.repository.PostRepository;
import demo.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepository;
	
	@Override
	public Post get(String _id) {
		return postRepository.get(_id);
	}
	
	@Override
	public Post create(String iduser, FrmPost newPost) {
		Post result = new Post(newPost);
		result.setId_user(iduser);
		return postRepository.save(result);
	}
	
	@Override
	public boolean update(String idPost, FrmPost upPost) {
		return postRepository.update(idPost, upPost);
	}

	@Override
	public boolean delete(String idPost) {
		return postRepository.remove(idPost);
	}
}
