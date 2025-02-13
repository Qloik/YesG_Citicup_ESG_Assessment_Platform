package com.citycup.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.citycup.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dql
 * @since 2023-03-22
 */
public interface UserService extends IService<User> {
	public Map<Boolean, String> register(String id,String password,String name);
	public Map<Boolean, String> login(String id,String password);
	public Map<Boolean, String> logout(String id);
}
