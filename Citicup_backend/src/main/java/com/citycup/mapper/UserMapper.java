package com.citycup.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-22
 */
public interface UserMapper extends BaseMapper<User> {
	@Update("update user set loginTime=#{time} where id=#{id}")
	public boolean updateLoginTime(LocalDateTime time,String id);
	
	@Select("select loginTime from user where id=#{id}")
	public LocalDateTime getTime(String id);
	
	@Update("update user set useTime=useTime+#{time} where id=#{id}")
	public boolean updateUseTime(int time,String id);
	
	@Select("select * from user where id=#{id}")
	public User getUserById(String id);
}
