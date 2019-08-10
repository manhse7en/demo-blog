package demo.blog.model;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import demo.blog.component.Helper;
import demo.blog.component.Validator;
import demo.blog.graphql.frm.FrmNewUser;
import graphql.GraphQLException;

@Document(collection = "user")
public class User {
	@Id
	String _id;
	@Field("username")
	String username;
	@Field("name")
	String name;
	@Field("password")
	String password;
	@Field("email")
	String email;

	public User(FrmNewUser user) {
		if (user.getUsername().length() < 6)
			throw new GraphQLException("Tên đăng nhập phải có độ dài lớn hơn 6 ký tự");
		user.setUsername(Jsoup.clean(user.getUsername(), Whitelist.none()));
		this.username = user.getUsername().toLowerCase();
		if (!user.getName().equals(""))
			throw new GraphQLException("Tên hiển thị không được để trống");
		user.setName(Jsoup.clean(user.getName(), Whitelist.none()));
		this.name = user.getName();
		if (user.getPassword().length() < 6)
			throw new GraphQLException("Mật khẩu phải độ dài lớn hơn 6 ký tự");
		user.setPassword(Helper.getInstance().md5(user.getPassword()));
		this.password = user.getPassword();
		if (!Validator.getInstance().isEmail(user.getEmail()))
			throw new GraphQLException("Email không hợp lệ");
		this.email = user.getEmail();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
