package com.example.ssiach6ex.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.springframework.util.StringUtils;

@Entity(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserAuthority> authorities = new ArrayList<>();

    protected User(){}

    private User(String username, String password) {
        validate(username, password);
        this.username = username;
        this.password = password;
    }

    private void validate(String username, String password){
        if (!StringUtils.hasLength(username)){
            throw new IllegalArgumentException("이름은 필수 입니다.");
        }

        if (!StringUtils.hasLength(password)) {
            throw new IllegalArgumentException("비밀번호는 필수 입니다.");
        }

    }

    public static User of(String username, String password){
        return new User(username, password);
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

    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(UserAuthority authority) {
        this.authorities.add(authority);
        if (!authority.equalsUser(this)){
            authority.setUser(this);
        }
    }

    public void removeAuthority(UserAuthority authority) {
        this.authorities.remove(authority);
        if (authority.equalsUser(this)){
            authority.removeUser();
        }
    }

    public boolean containAuthority(UserAuthority authority){
        return this.authorities.contains(authority);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
