package demo.blog.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import demo.blog.graphql.frm.FrmPost;

@Document(collection = "post")
public class Post {

	@Id
	String _id;
	@Field("content")
	String content;
	@Field("id_category")
	String id_category;
	@Field("id_user")
	String id_user;
	@Field("created")
	int created = (int) (new Date().getTime() / 1000);

	public Post() {
	}

	public Post(FrmPost newPost) {
		this.content = newPost.getContent();
		this.id_category = newPost.getId_category();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId_category() {
		return id_category;
	}

	public void setId_category(String id_category) {
		this.id_category = id_category;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

}
