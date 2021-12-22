package com.nichi.nichicommunity.controller;

import com.nichi.nichicommunity.dto.AccessTokenDTO;
import com.nichi.nichicommunity.dto.GitHubUser;
import com.nichi.nichicommunity.mapper.UserMapper;
import com.nichi.nichicommunity.model.User;
import com.nichi.nichicommunity.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;
    
    @Value("${github.client.secret}")
    private String clientSecret;
    
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           HttpServletRequest request) {
        //code
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if (gitHubUser != null) {
            
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            
            userMapper.insert(user);
            //登陆成功,将User对象放入Session里,制作"银行卡" 还未发送给前端,但是默认会给一张卡,有登陆态不能选号
            request.getSession().setAttribute("gitHubUser", gitHubUser);
            return "redirect:/";//地址重定向
            
        } else {
            //登陆失败
            return "redirect:/";//地址重定向
        }
    }
}
