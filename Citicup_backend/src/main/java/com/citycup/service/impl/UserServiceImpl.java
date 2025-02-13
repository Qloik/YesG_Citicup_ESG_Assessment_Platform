package com.citycup.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.citycup.entity.User;
import com.citycup.mapper.UserMapper;
import com.citycup.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	UserMapper userMapper;
	
	@Override
	public Map<Boolean, String> register(String id, String password, String name) {
		Map<Boolean, String> map=new HashMap<>();
		//检测手机号是否被注册过
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", id);
		List<User> users = userMapper.selectList(queryWrapper);
		if (users.size() != 0) {
			map.put(false, "id repeat");
			return map;
		}
		User user=new User();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		int result=userMapper.insert(user);
		if(result>0)
			map.put(true, "register success");
		else {
			map.put(false, "database error");
		}
		return map;
	}

	@Override
	public Map<Boolean, String> login(String id, String password) {
		Map<Boolean, String> map=new HashMap<>();
		//检测手机号是否存在
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", id);
		List<User> users = userMapper.selectList(queryWrapper);
		if (users.size() == 0) {
			map.put(false, "id not exist");
			return map;
		}
		//检测密码
		User user=userMapper.getUserById(id);
		if(!user.getPassword().equals(password)) {
			map.put(false, "wrong password");
			return map;
		}
		LocalDateTime dateTime=LocalDateTime.now();
		boolean res=userMapper.updateLoginTime(dateTime, id);
		if(res) {
			map.put(true, user.getName());
		}
		else {
			map.put(false, "fail");
		}
		return map;
	}

	@Override
	public Map<Boolean, String> logout(String id) {
		LocalDateTime endTime=LocalDateTime.now();
		LocalDateTime startTime=userMapper.getTime(id);
		// 获得两个时间之间的相差值
        Duration dur= Duration.between(startTime, endTime);
        //两个时间差的分钟数
        Long minute = dur.toMinutes();
        boolean res=userMapper.updateUseTime(minute.intValue(), id);
        userMapper.updateLoginTime(null, id);
        Map<Boolean, String> map=new HashMap<>();
        if(res) {
			map.put(true, "success");
		}
		else {
			map.put(false, "fail");
		}
		return map;
	}

}
