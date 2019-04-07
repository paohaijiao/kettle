package com.core.user.bean;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class UserDto {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "email")
    private String email;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "auditFlag")
    private String auditFlag;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
