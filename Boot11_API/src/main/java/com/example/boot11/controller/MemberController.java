package com.example.boot11.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.boot11.dto.MemberDto;
import com.example.boot11.repository.MemberDao;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberDao dao;

    @GetMapping
    public List<MemberDto> getList() {
        return dao.getList();
    }
    
    @GetMapping("/{num}")
    public MemberDto getData(@PathVariable("num") int num) {
        return dao.getData(num);
    }

    @PostMapping
    public Map<String, Object>inset(@RequestBody MemberDto dto) {
        dao.insert(dto);
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);

        return map;
    }

    @PatchMapping
    public Map<String, Object> patchMember() {

        return null;
    }

    @PutMapping("/{num}")
    public Map<String, Object> update(@RequestBody MemberDto dto) {
        dao.update(dto);
        
        return Map.of("isSuccess", true);
    }

    @DeleteMapping("/{num}")
    public Map<String, Object> delete(@PathVariable("num") int num) {
        dao.delete(num);
        
        return Map.of("isSuccess", true);
    }

}
