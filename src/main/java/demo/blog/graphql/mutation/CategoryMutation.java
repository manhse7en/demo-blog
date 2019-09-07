package demo.blog.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import demo.blog.component.Helper;
import demo.blog.graphql.frm.Login;
import demo.blog.model.Category;
import demo.blog.model.User;
import demo.blog.service.CategoryService;
import demo.blog.service.UserService;
import graphql.GraphQLException;

@Component
public class CategoryMutation implements GraphQLMutationResolver {
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	
	public Category createCategory(Login login, String title) {
		User user = userService.login(login);
		if (user != null) {
			if (user.getRoles().contains(Helper.getInstance().ROLE_ADMIN)) {
				if (!title.equals(""))
					return categoryService.create(title);
				throw new GraphQLException("Tiêu đề không được để trống");
			}
			throw new GraphQLException("Bạn không có quyền truy cập");
		}
		throw new GraphQLException("Tên đăng nhập hoặc mật khẩu không đúng");
	}
}
