/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;
import com.ui.ChatRoomPanel;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JList;
import com.ui.ChatFrame;
import java.awt.Color;

/**
 *
 * @author Hihew
 */
public class ChatRoomRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
    private ChatRoomPanel panel;
    private ChatFrame ui;
    
    public ChatRoomRenderer(){
        panel = new ChatRoomPanel();
    }
    @Override
        public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
        Message msg = (Message) e;
        panel.setImgIconUser(msg.icon);
        panel.setSendUsername(msg.sender);
        panel.setMessageSend(msg.content);
        setEnabled(true);
        setFont(jlist.getFont());
        return panel;
        }
}
