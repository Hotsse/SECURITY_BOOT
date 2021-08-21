package com.hotsse.spsecu.api.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotsse.spsecu.api.user.dao.UserDao;
import com.hotsse.spsecu.api.user.vo.SecurityUser;
import com.hotsse.spsecu.api.user.vo.UserVO;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 사용자 정보 조회
		UserVO user = this.userDao.getUser(username);
		if(user == null) {
			throw new UsernameNotFoundException(username + " is not avaliable username");
		}
		
		// 사용자 권한 정보 조회
		List<GrantedAuthority> authorities = this.userDao.getUserRoles(username).stream()
				.map(role -> {
					return new SimpleGrantedAuthority("ROLE_" + role); // 권한은 무조건 prefix "ROLE_"
				})
				.collect(Collectors.toList());
		
		return new SecurityUser(user.getUsername(), user.getPassword(), user.getNickname(), authorities);
	}
	
	public int insertUser(UserVO user) throws Exception {
		user.setPassword(
				passwordEncoder.encode(user.getPassword()));
		return this.userDao.insertUser(user);
	}
}
