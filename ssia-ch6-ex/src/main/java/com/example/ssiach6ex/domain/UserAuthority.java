package com.example.ssiach6ex.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "user_authority")
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "authority_id")
    private Authority authority;

    protected UserAuthority(){}

    private UserAuthority(User user, Authority authority){
        validate(user, authority);
        setUser(user);
        this.authority = authority;
    }

    private void validate(User user, Authority authority){
        if (user == null){
            throw new IllegalArgumentException("사용자를 입력하세요.");
        }

        if (authority == null){
            throw new IllegalArgumentException("권한을 입력하세요.");
        }
    }

    public static UserAuthority of(User user, Authority authority){
        return new UserAuthority(user, authority);
    }

    public boolean equalsUser(User user) {
        return this.user.equals(user);
    }

    public void removeUser() {
        if(this.user == null){
            return;
        }

        this.user.removeAuthority(this);
        this.user = null;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public String getAuthorityName() {
        return this.authority.getName();
    }

    public boolean equalsAuthority(Authority authority) {
        return this.authority.equals(authority);
    }

    protected void setUser(User user){
        if(this.user != null){
            this.user.removeAuthority(this);
        }
        this.user = user;
        if(!user.containAuthority(this)){
            user.addAuthority(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAuthority that = (UserAuthority) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
