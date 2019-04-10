package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_USER")
public class RUser {
    @Id
    @Column(name = "idUser")
    private int idUser;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "enabled")
    private String enabled;

}
