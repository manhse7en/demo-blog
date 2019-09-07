package demo.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.blog.graphql.frm.FrmNewUser;
import demo.blog.graphql.frm.FrmUpUser;
import demo.blog.graphql.frm.Login;
import demo.blog.model.User;
import demo.blog.repository.UserRepository;
import demo.blog.service.UserService;
import graphql.GraphQLException;

@Service
public class UserServiceImpl implements UserService {

	// Dependency injection
	@Autowired
	UserRepository userRepository;

	@Override
	public User get(String _id) {
		return userRepository.get(_id);
	}
	
	@Override
	public User createUser(FrmNewUser newUser) {
		return userRepository.create(newUser);
	}

	@Override
	public boolean updateUser(Login account, FrmUpUser upUser) {
		User user = userRepository.login(account);
		if (user != null) {
			return userRepository.update(user.get_id(), upUser);
		}
		throw new GraphQLException("Bạn chưa đăng nhập");
	}

	@Override
	public User login(Login login) {
		return userRepository.login(login);
	}
}
