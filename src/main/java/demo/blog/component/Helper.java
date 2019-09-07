package demo.blog.component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import graphql.GraphQLException;

public class Helper {
	private static Helper instance;

	public int ROLE_ADMIN = 1;
	public int ROLE_MANAGER = 2;
	public int ROLE_MEMBER = 3;

	private Helper() {
	}

	public static Helper getInstance() {
		if (instance == null) {
			synchronized (Helper.class) {
				if (instance == null)
					instance = new Helper();
			}
		}
		return instance;
	}

	public String md5(String str) {
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1, digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new GraphQLException(e.getMessage());
		}
		return result;
	}
}
