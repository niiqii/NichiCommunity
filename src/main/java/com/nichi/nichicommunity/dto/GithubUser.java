package com.nichi.nichicommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubUser {
    
    private String name;
    private long id;
    private String bio;
    private String avatar_url;
    
}
