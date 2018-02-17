package com.yangchun.tokenandfiletry.Entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author tianyi
 * @date 2018-02-14 11:48
 */
@Entity
public class File {

    @Id            //表示id列
    @GeneratedValue    //自增
    private Integer id;

    @NotBlank
    private Integer userId;

    @NotBlank
    private String fileName;

    @NotBlank
    private byte[] fileByte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }
}
