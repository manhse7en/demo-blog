package demo.blog.repository.custom;

import demo.blog.graphql.frm.FrmPost;
import demo.blog.model.Post;

public interface PostRepositoryCustom {

	boolean update(String idPost, FrmPost upPost);

	Post get(String _id);

	boolean remove(String idPost);

}
