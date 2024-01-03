package com.example.boot05.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.boot05.dto.UserDto;

// 원래는 DB 에서 읽어와야 하지만 DB 에 저장된 sample 데이터가 아래와 같다고 가정하고 로그인후 테스트해야한다.
// - 계정 / 비밀번호 예시
// 1. 일반 사용자(USER) => udada / 1234
// 2. 직원(STAFF) => batman / 1234
// 3. 관리자(ADMIN) => superman / 1234

@Service // Bean으로 만들기 위해
public class CustomUserDetailsService implements UserDetailsService {

  // Spring Security가 로그인 처리 시 호출하는 메서드
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // username에는 로그인 폼에 입력한 userNamd이 전달
    // 실제 DB 에는 ROLE_XXX 형식으로 저장이 되어 있어야한다
    String role = "";
    if (username.equals("udada")) {
      role = "ROLE_USER";
    } else if (username.equals("batman")) {
      role = "ROLE_STAFF";
    } else if (username.equals("superman")) {
      role = "ROLE_ADMIN";
    }

    // 비밀번호를 암호화하기 위한 객체
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // DB에는 암호화되어 비밀번호가 저장되있으므로
    String encodedPwd = encoder.encode("1234");

    // 1. DB에서 username 정보를 얻어와서
    UserDto dto = new UserDto();
    dto.setUserName(username);
    dto.setPassword(encodedPwd);
    dto.setEmail("aaa@");
    dto.setRole(role);

    // 2. UserDetails 객체에 해당 정보를 담아 리턴
    // 권한은 1개지만 List에 담아
    List<GrantedAuthority> authList = new ArrayList<>();
    authList.add(new SimpleGrantedAuthority(dto.getRole()));

    UserDetails ud = new User(dto.getUserName(), dto.getPassword(), authList);

    return ud;
  }

}
