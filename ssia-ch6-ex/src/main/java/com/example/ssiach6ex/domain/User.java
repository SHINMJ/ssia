package com.example.ssiach6ex.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.util.StringUtils;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    protected User(){}

    private User(String username, String password, List<Authority> authorities) {
        validate(username, password, authorities);
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static User of(String username, String password, List<Authority> authorities){
        return new User(username, password, authorities);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    private void validate(String username, String password, List<Authority> authorities){
        if (!StringUtils.hasLength(username)){
            throw new IllegalArgumentException("이름은 필수 입니다.");
        }

        if (!StringUtils.hasLength(password)) {
            throw new IllegalArgumentException("비밀번호는 필수 입니다.");
        }

        if (authorities.size() <= 0){
            throw new IllegalArgumentException("하나 이상의 권한을 가져야 합니다.");
        }
    }
}
