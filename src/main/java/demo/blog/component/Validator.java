package demo.blog.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private static Validator instance;

	private Validator() {
	}

	public static Validator getInstance() {
		if (instance == null) {
			synchronized (Validator.class) {
				if (instance == null)
					instance = new Validator();
			}
		}
		return instance;
	}

	public boolean isEmail(String email) {
		if (email == null || email.equals(""))
			return false;
		String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
