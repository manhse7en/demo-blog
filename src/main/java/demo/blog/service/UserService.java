package demo.blog.service;

import demo.blog.graphql.frm.FrmNewUser;
import demo.blog.graphql.frm.FrmUpUser;
import demo.blog.graphql.frm.Login;
import demo.blog.model.User;

public interface UserService {

	User createUser(FrmNewUser newUser);

	boolean updateUser(Login account, FrmUpUser upUser);

	User login(Login login);

	User get(String _id);

}
