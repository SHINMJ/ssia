package com.example.ssiach6ex.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.util.StringUtils;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected Authority(){}

    private Authority(String name){
        validate(name);
        this.name = name;
    }

    public static Authority from(String name){
        return new Authority(name);
    }

    private void validate(String name) {
        if (!StringUtils.hasLength(name)){
            throw new IllegalArgumentException("권한명을 입력하세요.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
