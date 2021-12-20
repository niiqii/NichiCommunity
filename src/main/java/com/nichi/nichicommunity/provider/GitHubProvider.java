package com.nichi.nichicommunity.provider;

import com.alibaba.fastjson.JSON;
import com.nichi.nichicommunity.dto.AccessTokenDTO;
import com.nichi.nichicommunity.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){

        //Java模拟Post请求
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //将String JSON对象自动转换成java类对象
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (Exception e) {
        }
        return null;
    }
}
