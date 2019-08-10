package demo.blog.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comment")
public class Comment {
	@Id
	String _id;
	@Field("content")
	String content;
	@Field("id_post")
	String id_post;
	@Field("id_user")
	String id_user;
	int timed = (int) (new Date().getTime() / 1000);

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

	public String getId_post() {
		return id_post;
	}

	public void setId_post(String id_post) {
		this.id_post = id_post;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public int getTimed() {
		return timed;
	}

	public void setTimed(int timed) {
		this.timed = timed;
	}

}
