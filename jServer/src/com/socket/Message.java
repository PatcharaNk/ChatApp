package com.socket;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public String type, sender, content, recipient,pic;
    public ArrayList<Message> content_list;
    
    public Message(String type, String sender, String content, String recipient){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient;
    }
    
    public Message(String type, String sender, String content, String recipient,String pic){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient; this.pic = pic;
    }
    
    public Message(String type, String sender, ArrayList<Message> content_list, String recipient){
        this.type = type; this.sender = sender; this.content_list = content_list; this.recipient = recipient;
    }
    
    
    @Override
    public String toString(){
        return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
    }
}
