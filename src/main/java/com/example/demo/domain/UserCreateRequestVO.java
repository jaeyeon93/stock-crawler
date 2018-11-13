package com.example.demo.domain;

import lombok.Data;
import org.springframework.social.connect.UserProfile;
import org.springframework.util.StringUtils;

@Data
public class UserCreateRequestVO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public static UserCreateRequestVO fromSocialUserProfile(UserProfile userProfile) {
        UserCreateRequestVO userCreateRequestVO = new UserCreateRequestVO();
        userCreateRequestVO.setEmail(StringUtils.isEmpty(userProfile.getEmail()) ? "" : userProfile.getEmail());
        userCreateRequestVO.setLastName(StringUtils.isEmpty(userProfile.getLastName()) ? "" : userProfile.getLastName());
        userCreateRequestVO.setFirstName(StringUtils.isEmpty(userProfile.getFirstName()) ? "" : userProfile.getFirstName());
        return userCreateRequestVO;
    }
}
