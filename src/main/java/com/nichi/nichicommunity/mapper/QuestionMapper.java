package com.nichi.nichicommunity.mapper;

import com.nichi.nichicommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface QuestionMapper {
    
    @Insert("insert into QUESTION (TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,TAG) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from QUESTION limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);
    
    @Select("select count(1) from QUESTION")
    Integer count(); 
}
