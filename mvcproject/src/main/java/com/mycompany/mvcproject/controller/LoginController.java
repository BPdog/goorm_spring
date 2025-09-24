package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    // 로그인 폼을 보여주는 GET 요청 처리
    @RequestMapping(method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // 로그인 인증을 처리하는 POST 요청 처리
    @RequestMapping(method = RequestMethod.POST)
    public String processLogin(@ModelAttribute("user") User user, Model model) {
        if (User.DEFAULT_EMAIL.equals(user.getEmail()) && User.DEFAULT_PASSWORD.equals(user.getPassword())) {
            // 인증 성공 시, 이메일에서 사용자 이름을 추출하여 모델에 추가
            String name = user.getEmail().split("@")[0];
            model.addAttribute("name", name);
            return "loginSuccess"; // 성공 페이지로 이동
        } else {
            return "redirect:/login"; // 실패 시 로그인 폼으로 리다이렉트
        }
    }
}
