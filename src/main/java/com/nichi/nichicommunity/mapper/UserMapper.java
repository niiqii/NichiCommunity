package com.nichi.nichicommunity.mapper;

import com.nichi.nichicommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into USER (ACCOUNT_ID, NAME, GMT_CREATE, GMT_MODIFIED, TOKEN) VALUES (#{accountId},#{name},#{gmtCreate},#{gmtModified},#{token})")
    public void insert(User user);
    
}
