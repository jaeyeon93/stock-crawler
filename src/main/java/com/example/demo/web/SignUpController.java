package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.domain.UserCreateRequestVO;
import com.example.demo.service.UserService;
import com.example.demo.social.FrontUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignUpController {
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String redirectRequestToRegistrationPage(WebRequest request, ModelMap modelMap) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        UserProfile userProfile = connection.fetchUserProfile();
        UserCreateRequestVO userCreateRequestVO = UserCreateRequestVO.fromSocialUserProfile(userProfile);
        modelMap.put("user", userCreateRequestVO);
        return "signup";
    }

    @PostMapping("/signup")
    public String registrationUser(@ModelAttribute UserCreateRequestVO userCreateRequestVO, WebRequest request) throws Exception {
        try {
            User user = userService.create(userCreateRequestVO);
            providerSignInUtils.doPostSignUp(user.getEmail(), request);

            FrontUserDetail frontUserDetail = new FrontUserDetail(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(frontUserDetail, null, frontUserDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/";
        } catch (Exception e) {
            return String.format("redirect:/error?message=%s", e.getMessage());
        }
    }
}
