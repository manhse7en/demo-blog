package demo.blog.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import demo.blog.graphql.frm.FrmNewUser;
import demo.blog.graphql.frm.FrmUpUser;
import demo.blog.graphql.frm.Login;
import demo.blog.model.User;
import demo.blog.service.UserService;

@Component
public class UserMutation implements GraphQLMutationResolver {

	@Autowired
	UserService userService;

	public User createUser(FrmNewUser newUser) {
		return userService.createUser(newUser);
	}

	public boolean updateUser(Login account, FrmUpUser upUser) {
		return userService.updateUser(account, upUser);
	}
}
