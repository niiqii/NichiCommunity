package com.nichi.nichicommunity.controller;

import com.nichi.nichicommunity.Service.QuestionService;
import com.nichi.nichicommunity.dto.PaginationDTO;
import com.nichi.nichicommunity.mapper.UserMapper;
import com.nichi.nichicommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private QuestionService questionService;
    
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        
        //在此引入Service,在请求组装user与question的时候就需要中间层去做
        //加大层级，层级改为pagination
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
