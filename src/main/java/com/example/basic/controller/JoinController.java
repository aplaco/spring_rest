package com.example.basic.controller;
import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.JoinService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/join/create")
    public Map<String, Object> create(@RequestBody JoinDTO formDTO ){
        String result = joinService.processJoin(formDTO);

        Map<String, Object> response =  new HashMap<>();
        response.put("message", result);
        return response;
    }



    @GetMapping("/admin")
    public Object showAdminPage(@RequestParam(defaultValue="0") int page, HttpSession session){
        //세션에 유저 정보가 없을 시 에러 객체 반환
//        if(session.getAttribute("loginUer") == null){
//            // Map.of(key, value); 간단한 구조의 객체 생성 (수정 불가)
//            return Map.of("error", "로그인이 필요합니다.");
//        }

        int pageSize = 3;
        Page<JoinEntity> userPage = joinService.getUsersByPage(page, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("users", userPage.getContent());
        result.put("currentPage", page);
        result.put("totalPages", userPage.getTotalPages());
        return result;
    }

    @DeleteMapping("/admin/del/{id}")
    public Map<String, String> delUser(@PathVariable Long id){
        joinService.delete(id);
        Map<String, String> result = new HashMap<>();
        result.put("message", "삭제 성공");
        return result;
    }


    @GetMapping("/admin/edit/{id}")
    public JoinEntity editUser(@PathVariable Long id){
        return joinService.getUserById(id);
    }


    @PutMapping("/admin/update")
    public Map<String, String> updateUser(@RequestBody JoinEntity formUser){
        joinService.updateUser(formUser);

        Map<String, String> result = new HashMap<>();
        result.put("message", "수정 성공");
        return result;
    }
}

