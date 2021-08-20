package com.hotsse.spsecu.api.user.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotsse.spsecu.api.user.vo.UserVO;
import com.hotsse.spsecu.core.base.BaseDao;

@Repository
public class UserDao extends BaseDao {

	public UserVO getUser(String username) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		
		return this.sqlSession.selectOne("user.getUser", param);
	}
	
	public int insertUser(UserVO user) {
		return this.sqlSession.insert("user.insertUser", user);
	}
}
