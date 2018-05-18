/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Hihew
 */
public class UserList {

    private String username;
    private ImageIcon imgUser;
    private ImageIcon imgNoti;

    public UserList(String username, ImageIcon imgUser, ImageIcon imgNoti) {
        this.username = username;
        this.imgUser = imgUser;
        this.imgNoti = imgNoti;
    }

    public UserList(String username, ImageIcon imgUser) {
        this.username = username;
        this.imgUser = imgUser;
    }

    public ImageIcon getImgNoti() {
        return imgNoti;
    }

    public void setImgNoti(ImageIcon imgUserDefault) {
        this.imgNoti = imgUserDefault;
    }

    public String getUsername() {
        return username;
    }

    public ImageIcon getImgUser() {
        return imgUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImgUser(ImageIcon imgUser) {
        this.imgUser = imgUser;
    }

}
