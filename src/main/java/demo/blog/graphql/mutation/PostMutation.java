package demo.blog.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import demo.blog.component.Helper;
import demo.blog.graphql.frm.FrmPost;
import demo.blog.graphql.frm.Login;
import demo.blog.model.Post;
import demo.blog.model.User;
import demo.blog.service.PostService;
import demo.blog.service.UserService;
import graphql.GraphQLException;

@Component
public class PostMutation implements GraphQLMutationResolver {

	@Autowired
	UserService userService;
	@Autowired
	PostService postService;

	public Post createPost(Login login, FrmPost newPost) {
		User user = userService.login(login);
		if (user != null) {
			if (newPost.getContent().equals(""))
				throw new GraphQLException("Nội dung không được để trống");
			if (newPost.getId_category().equals(""))
				throw new GraphQLException("Chủ đề không được để trống");
			return postService.create(user.get_id(), newPost);
		}
		throw new GraphQLException("Bạn chưa đăng nhập");
	}

	boolean checkPermission(User user, String idPost) {
		boolean flag = false;
		if (user.getRoles().contains(Helper.getInstance().ROLE_ADMIN))
			flag = true;
		else {
			Post post = postService.get(idPost);
			if (post != null && post.getId_user().equals(user.get_id()))
				flag = true;
		}
		return flag;
	}

	public boolean updatePost(Login login, String idPost, FrmPost upPost) {
		User user = userService.login(login);
		if (user != null) {
			boolean flag = this.checkPermission(user, idPost);
			if (flag)
				return postService.update(idPost, upPost);
			throw new GraphQLException("Bạn không có quyền truy cập");
		}
		throw new GraphQLException("Bạn chưa đăng nhập");
	}

	public boolean deletePost(Login login, String idPost) {
		User user = userService.login(login);
		if (user != null) {
			boolean flag = this.checkPermission(user, idPost);
			if (flag)
				return postService.delete(idPost);
			throw new GraphQLException("Bạn không có quyền truy cập");
		}
		throw new GraphQLException("Bạn chưa đăng nhập");
	}
}
