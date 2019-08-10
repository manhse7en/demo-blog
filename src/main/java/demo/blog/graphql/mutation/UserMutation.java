package demo.blog.graphql.mutation;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import demo.blog.graphql.frm.FrmNewUser;
import demo.blog.graphql.frm.FrmUpUser;
import demo.blog.graphql.frm.Login;
import demo.blog.model.User;

@Component
public class UserMutation implements GraphQLMutationResolver {
	public User createUser(FrmNewUser newUser) {
		return null;
	}

	public boolean updateUser(Login login, FrmUpUser upUser) {
		return false;
	}
}
