package com.wodnj5.board.controller;

import com.wodnj5.board.domain.User;
import com.wodnj5.board.form.JoinForm;
import com.wodnj5.board.form.LoginForm;
import com.wodnj5.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/join")
    public String join() {
        return "join";
    }

    @PostMapping("/user/join")
    public String join(JoinForm form) {
        userService.join(form.getEmail(), form.getPassword(), form.getUsername());
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @PostMapping("/user/login")
    public String login(LoginForm form, HttpSession session) {
        User user = userService.login(form.getEmail(), form.getPassword());
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30 * 60);
        return "redirect:/";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
