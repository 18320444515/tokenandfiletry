package com.yangchun.tokenandfiletry.Entity;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author tianyi
 * @date 2018-02-12 23:24
 */
@Entity
public class User {

    @Id            //表示id列
    @GeneratedValue    //自增
    private Integer id;

    @NotBlank(message = "这个字段必传")
    private String userName;

    @NotBlank(message = "这个字段必传")
    private String passWord;

    private String fileId;  //默认显示的文件

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord=" + passWord +
                '}';
    }
}
