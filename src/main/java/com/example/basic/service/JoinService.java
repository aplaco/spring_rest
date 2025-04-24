package com.example.basic.service;

import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.repository.JoinRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinRepo joinRepo;

    public String processJoin(JoinDTO dto) {
        JoinEntity user = new JoinEntity(null, dto.getUname(), dto.getEmail(), dto.getColors() );
        joinRepo.save(user);
        return "회원정보가 저장되었습니다";
    }

    public List<JoinEntity> getAllUsers(){
        return joinRepo.findAll();
    }

    public void delete(Long id){
        joinRepo.deleteById(id);
    }

    public JoinEntity getUserById(Long id){
        return joinRepo.findById(id).orElseThrow(()-> new RuntimeException("해당 아이디의 유저 없음"));
    }

    public  void updateUser(JoinEntity user){
        joinRepo.save(user);
    }

    public Page<JoinEntity> getUsersByPage(int page, int size){
        return joinRepo.findAll(PageRequest.of(page, size));
    }
}
