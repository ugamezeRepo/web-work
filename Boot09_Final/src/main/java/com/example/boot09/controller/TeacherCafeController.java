package com.example.boot09.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.boot09.dto.CafeCommentDto;
import com.example.boot09.dto.TeacherCafeDto;
import com.example.boot09.service.TeacherCafeService;

@Controller
public class TeacherCafeController {
    @Autowired private TeacherCafeService service;
    
    @GetMapping("/teacher/cafe/comment_list")
    public String commentList(Model model, CafeCommentDto dto) {
        service.getCommentList(model, dto);
        
        // templates/cafe/comment_list.html에서 댓글이 들어있는 여러 개의 li를 응답할 예정
        return "teacher/cafe/comment_list";
    }
    
    @ResponseBody
    @PostMapping("/teacher/cafe/comment_update")
    public Map<String, Object> commentUpdate(CafeCommentDto dto) {
        service.updateComment(dto);
        
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("num", dto.getNum());
        map.put("content", dto.getContent());
        
        return map;
    }
    
    /* 내가 한 거
    @PostMapping("/teacher/cafe/comment_update")
    public String commentUpdate(CafeCommentDto dto) {
        service.updateComment(dto);
        
        return "redirect:/teacher/cafe/detail?num=" + dto.getRef_group();
    }
    */
    
    @ResponseBody // Map 객체를 리턴하면 json 문자열이 응답되도록 @ResponseBody 어노테이션 추가
    @GetMapping("/teacher/cafe/comment_delete")    
    public Map<String, Object> commentDelete(int num) {
        // num은 GET방식 파라미터로 전달되는 삭제할 댓글의 번호
        service.deleteComment(num);

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        
        return map;
    }
    
    @PostMapping("/teacher/cafe/comment_insert")
    public String commentInsert(CafeCommentDto dto) {
        // 댓글 저장 처리를 하고
        service.saveComment(dto);
        // 해당 글 자세히 보기로 리다이렉트
        return "redirect:/teacher/cafe/detail?num=" + dto.getRef_group();
    }
    
    @GetMapping("/teacher/cafe/updateform")
    public String updateform(Model model, int num) {
        service.getData(model, num);
        
        return "teacher/cafe/updateform";
    }
    
    @PostMapping("/teacher/cafe/update")
    public String update(TeacherCafeDto dto) {
        service.updateContent(dto);
        
        return "redirect:/teacher/cafe/detail?num=" + dto.getNum();
    }
    
    @GetMapping("/teacher/cafe/delete")
    public String delete(int num) {
        service.deleteContent(num);
        
        return "redirect:/teacher/cafe/list";
    }
    
    @GetMapping("/teacher/cafe/detail")
    public String detail(Model model, TeacherCafeDto dto) {
        service.getDetail(model, dto);
        
        return "teacher/cafe/detail";
    }
    
    @PostMapping("/teacher/cafe/insert")
    public String insert(TeacherCafeDto dto) {
        //서비스를 이용해서 새글을 저장한다
        service.saveContent(dto);
        
        return "teacher/cafe/insert";
    }
    
    @GetMapping("/teacher/cafe/insertform")
    public String insertform() {
        
        return "teacher/cafe/insertform";
    }

    @GetMapping("/teacher/cafe/list")
    public String list(Model model, TeacherCafeDto dto) {
        //dto 에는 검색키워드가 있을수도 있고 없을수도 있다
        service.getList(model, dto);
        
        return "teacher/cafe/list";
    }
    
}
