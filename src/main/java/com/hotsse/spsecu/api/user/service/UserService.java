package com.hotsse.spsecu.api.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotsse.spsecu.api.user.dao.UserDao;
import com.hotsse.spsecu.api.user.vo.SecurityUser;
import com.hotsse.spsecu.api.user.vo.UserVO;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	public String getRawPassword(String username) {

		if ("dkdlrja".equals(username)) {
			return "$2a$10$gNX5XgiFucX1Mx/JKREOVuue0cqNr5NYcCsQldCTvHu62WzNNZZqS";
		}

		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO user = this.userDao.getUser(username);
		if(user == null) {
			throw new UsernameNotFoundException(username + " is not avaliable username");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		return new SecurityUser(user.getUsername(), user.getPassword(), user.getNickname(), authorities);
	}
}
