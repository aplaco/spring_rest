package com.example.basic.service;

import com.example.basic.entity.JoinEntity;
import com.example.basic.repository.JoinRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JoinRepo joinRepo;

    public JoinEntity checkUser(String uname, String email) {
        return joinRepo.findByUnameAndEmail(uname, email);
    }
}
