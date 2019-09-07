package demo.blog.repository.custom;

import demo.blog.graphql.frm.FrmNewUser;
import demo.blog.graphql.frm.FrmUpUser;
import demo.blog.graphql.frm.Login;
import demo.blog.model.User;

public interface UserRepositoryCustom {

	User create(FrmNewUser newUser);

	User login(Login login);

	boolean update(String _id, FrmUpUser upUser);

	User get(String _id);

}
