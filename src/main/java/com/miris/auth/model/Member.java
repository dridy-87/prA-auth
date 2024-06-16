package com.miris.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @FileName : Member.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 16

 * @프로그램 설명 :

 * @변경이력 :
 */
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String role;
}
