package com.example.boot09.service;

import org.springframework.ui.Model;
import com.example.boot09.dto.UserDto;

public interface UserService {
    public void addUser(UserDto dto);

    public void getInfo(Model model);
}
