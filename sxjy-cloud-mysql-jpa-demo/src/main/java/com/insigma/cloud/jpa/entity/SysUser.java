package com.insigma.cloud.jpa.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * jpa sys_user
 */
@Entity
@Table(name="sys_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="userid",nullable = false)
    @GeneratedValue(generator = "jpa-uuid")
    private String userid;

    @Column
    private String username;

    @Column(name="cnname")
    private String name;

    @Column
    private String password;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
