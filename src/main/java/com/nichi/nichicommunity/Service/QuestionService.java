package com.nichi.nichicommunity.Service;

import com.nichi.nichicommunity.dto.PaginationDTO;
import com.nichi.nichicommunity.dto.QuestionDTO;
import com.nichi.nichicommunity.mapper.QuestionMapper;
import com.nichi.nichicommunity.mapper.UserMapper;
import com.nichi.nichicommunity.model.Question;
import com.nichi.nichicommunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        //真正的Page当前页
        Integer offset = page < 1 ? 0 : size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //快速赋值属性
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setDescription("");
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        
        return paginationDTO;
    }
}
