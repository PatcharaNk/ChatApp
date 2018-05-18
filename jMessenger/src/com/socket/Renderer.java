/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Hihew
 */
public class Renderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean isSelected, boolean CellHasFocus) {
        //To change body of generated methods, choose Tools | Templates.
        UserList user = (UserList) o;
        setText(user.getUsername());
        if(user.getImgNoti() != null){
            setIcon(user.getImgNoti());
        }else{
            setIcon(user.getImgUser());
        }

        //set bg fg 
        if (isSelected) {
            setBackground(jlist.getSelectionBackground());
            setForeground(jlist.getSelectionForeground());
        } else {
            setBackground(jlist.getBackground());
            setForeground(jlist.getForeground());
        }
        setEnabled(true);
        setFont(jlist.getFont());
        return this;
    }

}
