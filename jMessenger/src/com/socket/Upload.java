package com.socket;

import com.ui.ChatFrame;
import java.io.*;
import java.net.*;
import javax.swing.ImageIcon;

public class Upload implements Runnable{
    public SocketClient sc;
    public String addr;
    public int port;
    public Socket socket;
    public FileInputStream In;
    public OutputStream Out;
    public File file;
    public ChatFrame ui;
    public ImageIcon systemIcon = new ImageIcon(getClass().getResource("/com/ui/img/system.png"));
    
    public Upload(String addr, int port, File filepath, ChatFrame frame){
        super();
        try {
            file = filepath; ui = frame;
            socket = new Socket(InetAddress.getByName(addr), port);
            Out = socket.getOutputStream();
            In = new FileInputStream(filepath);
        } 
        catch (Exception ex) {
            System.out.println("Exception [Upload : Upload(...)]");
        }
    }
    
    @Override
    public void run() {
        try {       
            byte[] buffer = new byte[1024];
            int count;
            
            while((count = In.read(buffer)) >= 0){
                Out.write(buffer, 0, count);
            }
            Out.flush();
            
            //ui.jChatTextArea.append("[Applcation > Me] : File upload complete\n");
            System.out.println("File upload .......");
            ui.chatModel.addElement(new Message("App", "Application", "File upload complete", "Me", systemIcon));
            System.out.println("complete");
            
            ui.jSelectFileButton.setEnabled(true); ui.jSendFileButton.setEnabled(true);
            ui.jLocaltionFileTextField.setVisible(true);
            
            if(In != null){ In.close(); }
            if(Out != null){ Out.close(); }
            if(socket != null){ socket.close(); }
        }
        catch (Exception ex) {
            System.out.println("Exception [Upload : run()]");
            ex.printStackTrace();
        }
    }

}