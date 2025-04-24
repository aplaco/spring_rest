package com.example.basic.controller;

import com.example.basic.dto.LoginDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO,  HttpSession session) {
        JoinEntity user = loginService.checkUser(loginDTO.getUname(), loginDTO.getEmail());

        if (user != null) {
            session.setAttribute("loginUser", user);
            return "로그인 성공";
        } else {
           return "로그인 실패";
        }
    }

    @GetMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate();  // 세션 초기화 (로그아웃)
        Map<String, String> response = new HashMap<>();
        response.put("message","로그아웃 완료");
        return response;
    }
}



