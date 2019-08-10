package demo.blog.graphql.frm;

import demo.blog.component.Helper;
import graphql.GraphQLException;

public class Login {
	String username;
	String password;

	public String getUsername() {
		if (username.length() < 6)
			throw new GraphQLException("Tên đăng nhập không hợp lệ");
		return username.toLowerCase();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		if (password.length() < 6)
			throw new GraphQLException("Mật khẩu không hợp lệ");
		return Helper.getInstance().md5(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
