package demo.blog.service;

import demo.blog.graphql.frm.FrmPost;
import demo.blog.model.Post;

public interface PostService {

	Post create(String iduser, FrmPost newPost);

	boolean update(String idPost, FrmPost upPost);

	Post get(String _id);

	boolean delete(String idPost);

}
