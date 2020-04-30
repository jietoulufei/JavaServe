package com.yiwan.login;
import javax.persistence.*;

/**
 * 1.实体类和表的映射
 * @Entity
 * @Table
 * 2.类中属性和表字段的映射
 * @Id 主键id
 * @GeneratedValue 主键生成策略(自增等。。。)
 * @Column
 */

@Entity
@Table(name = "user_list")
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略-自增
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
