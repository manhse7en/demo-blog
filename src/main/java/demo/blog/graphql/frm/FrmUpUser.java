package demo.blog.graphql.frm;

import demo.blog.component.Helper;
import demo.blog.component.Validator;
import graphql.GraphQLException;

public class FrmUpUser {
	String name = "";
	String email = "";
	String password = "";

	public String getName() {
		if (!name.equals("")) {
			if (name.length() < 6)
				throw new GraphQLException("Tên hiển thị phải dài hơn 6 ký tự");
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		if (!email.equals("")) {
			if (!Validator.getInstance().isEmail(email))
				throw new GraphQLException("Email không hợp lệ");
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		if (!password.equals("")) {
			if (password.length() < 6)
				throw new GraphQLException("Mật khẩu phải dài hơn 6 ký tự");
			password = Helper.getInstance().md5(password);
		}
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
