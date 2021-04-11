package com.minimal.mybatis.mapper;

import com.minimal.common.api.dto.UserDto;
import com.minimal.entity.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author linzhiqiang
 */
public interface UserMapper extends Mapper<User> {

    /**
     * 通过openId查询用户信息
     * @param openId
     * @return
     */
    User selectUserByOpenId(@Param(value="openId") String openId);


    /**
     * 查询用户的推荐记录
     * @param userId
     * @return
     */
    List<UserDto> selectRelationshipUserByActUserId(@Param(value="userId") String userId);
}