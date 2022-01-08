package com.nichi.nichicommunity.mapper;

import com.nichi.nichicommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into USER (ACCOUNT_ID, NAME, TOKEN, GMT_CREATE, GMT_MODIFIED, AVATAR_URL) VALUES (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from USER where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findByPrimaryKey(@Param("id") Integer id);
}
