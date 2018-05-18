package com.socket;

import com.ui.ChatFrame;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SocketClient implements Runnable {

    public int port;
    public String serverAddr;
    public Socket socket;
    public ChatFrame ui;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public History hist;
    public ArrayList<Message> hist_list;
    public boolean chkLog = false;
    public boolean chkRea = false;
    public ImageIcon systemIcon = new ImageIcon(getClass().getResource("/com/ui/img/system.png"));
    public ImageIcon man1Icon = new ImageIcon(getClass().getResource("/com/ui/img/matureman1.png"));
    public ImageIcon man2Icon = new ImageIcon(getClass().getResource("/com/ui/img/matureman2.png"));
    public ImageIcon ninja3Icon = new ImageIcon(getClass().getResource("/com/ui/img/ninja3.png"));
    public ImageIcon man4Icon = new ImageIcon(getClass().getResource("/com/ui/img/teacher4.png"));
    public ImageIcon man5Icon = new ImageIcon(getClass().getResource("/com/ui/img/man5.png"));
    public ImageIcon man6Icon = new ImageIcon(getClass().getResource("/com/ui/img/man6.png"));
    public ImageIcon man7Icon = new ImageIcon(getClass().getResource("/com/ui/img/boy7.png"));
    public ImageIcon man8Icon = new ImageIcon(getClass().getResource("/com/ui/img/man8.png"));
    public ImageIcon unkownIcon = new ImageIcon(getClass().getResource("/com/ui/img/unknown0.png"));

    public SocketClient(ChatFrame frame) throws IOException {
        ui = frame;
        this.serverAddr = ui.serverAddr;
        this.port = ui.port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);

        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());

    }

    @Override
    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : " + msg.toString());
                UserList u = (UserList) ui.jUserOnlineList.getSelectedValue();
                if (msg.type.equals("message")) {
                    if (msg.sender.equals(ui.username)) {
                        //ui.jChatTextArea.append("[" + msg.sender + " > " + msg.recipient + "] : " + msg.content + "\n");
                        ui.chatModel.addElement(toMessageIcon(msg, checkIcon(ui.numAvatar)));

                    } else if (u.getUsername().equals("All")) {
                        if (msg.recipient.equals(ui.username)) {
                            //ui.jChatTextArea.append("[" + msg.sender + " > Me] : " + msg.content + "\n");
                            int index = -1;
                            for (int i = 0; i < ui.model.getSize(); i++) {
                                //System.out.println("Yeah = > " + ((UserList) ui.model.get(i)).getUsername());
                                if (((UserList) ui.model.get(i)).getUsername().equals(msg.sender)) {
                                    index = i;
                                }
                            }
                            System.out.println(msg.pic + "" + index);
                            ui.model.set(index, new UserList(msg.sender, checkIcon(msg.pic), ui.notiIcon));
                        } else {
                            //ui.jChatTextArea.append("[" + msg.sender + " > " + msg.recipient + "] : " + msg.content + "\n");
                            ui.chatModel.addElement(toMessageIcon(msg, checkIcon(msg.pic)));
                        }
                    } else if (!msg.recipient.equals("All") && msg.recipient.equals(ui.username)) {
                        if (!msg.sender.equals(u.getUsername())) {
                            //ui.jChatTextArea.append("[" + msg.sender + " > Me] : " + msg.content + "\n");
                            
                            int index = -1;
                            for (int i = 0; i < ui.model.getSize(); i++) {
                                //System.out.println("Yeah = > " + ((UserList) ui.model.get(i)).getUsername());
                                if (((UserList) ui.model.get(i)).getUsername().equals(msg.sender)) {
                                    index = i;
                                }
                            }
                            System.out.println("" + index);
                            if (!u.getUsername().equals(msg.sender)) {
                            ui.model.set(index, new UserList(msg.sender, checkIcon(msg.pic), ui.notiIcon));
                            }
                        }else{
                            ui.chatModel.addElement(toMessageIcon(msg, checkIcon(msg.pic)));
                        }
                    }

                    if (!msg.content.equals(".bye") && !msg.sender.equals(ui.username)) {
                        String msgTime = (new Date()).toString();

                        try {
                            hist.addMessage(msg, msgTime);
                            DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
                            table.addRow(new Object[]{msg.sender, msg.content, "Me", msgTime});
                        } catch (Exception ex) {
                        }
                    }
                } else if (msg.type.equals("login")) {
                    if (msg.content.equals("TRUE")) {
                        //ui.jChatTextArea.append("[SERVER > Me] : Login Successful\n");

                        ui.numAvatar = msg.pic;
                        ui.jMyNameLabel.setIcon(checkIcon(ui.numAvatar));
                        ui.chatModel.addElement(new Message(msg.type, msg.sender, "Login Successful", "Me", systemIcon));
                        chkLog = true;
                        //chkRea=true;
                    } else {
                        //ui.jTextArea1.append("[SERVER > Me] : Login Failed\n");
                        chkLog = false;
                        //chkRea=true;
                    }
                } else if (msg.type.equals("newuser")) {
                    if (!msg.content.equals(ui.username)) {
                        boolean exists = false;
                        for (int i = 0; i < ui.model.getSize(); i++) {
                            if (ui.model.getElementAt(i).equals(msg.content)) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            if (!msg.content.equals("")) {
                                ui.model.addElement(new UserList(msg.content, checkIcon(msg.pic)));
                                //ui.jChatTextArea.append("[" + msg.sender + " > All] : -------- " + msg.content + "  Online --------\n");
                                ui.chatModel.addElement(new Message(msg.type, msg.sender, "<<<<  "+msg.content + "  Online  >>>>", "All", systemIcon));
                            }
                        }
                    }
                } else if (msg.type.equals("signup")) {
                    if (msg.content.equals("TRUE")) {
//                        ui.jLoginButton.setEnabled(false);
                        //                       ui.jSignupButton.setEnabled(false);
//                        ui.jButton4.setEnabled(true); 
                        //                       ui.jSelectFileButton.setEnabled(true);
                        //ui.jChatTextArea.append("[SERVER > Me] : Singup Successful\n");
                        ui.chatModel.addElement(new Message(msg.type, msg.sender, "Singup Successful", "Me", systemIcon));

                        chkLog = true;
                    } else {
                        //ui.jTextArea1.append("[SERVER > Me] : Signup Failed\n");
                        chkLog = false;
                    }
                } else if (msg.type.equals("signout")) {
                    if (msg.content.equals(ui.username)) {
                        //ui.jChatTextArea.append("[" + msg.sender + " > Me] : Bye\n");
                        ui.chatModel.addElement(new Message(msg.type, msg.sender, "Bye", "Me", checkIcon(msg.pic)));

                        for (int i = 0; i < ui.chatModel.size(); i++) {
                            ui.chatModel.removeElementAt(i);
                        }
                        for (int i = 1; i < ui.model.size(); i++) {
                            ui.model.removeElementAt(i);
                        }

                        ui.clientThread.stop();
                    } else {
                        for (int i = 1; i < ui.model.size(); i++) {
                            UserList uso = (UserList) ui.model.getElementAt(i);
                            if (msg.content.equals(uso.getUsername())) {
                                ui.model.removeElementAt(i);
                                //ui.jChatTextArea.append("[" + msg.sender + " > All] : " + msg.content + " has signed out\n");
                                ui.chatModel.addElement(new Message(msg.type, msg.sender, msg.content + " has signed out", "All", systemIcon));

                            }
                        }
                    }
                } else if (msg.type.equals("upload_req")) {

                    if (JOptionPane.showConfirmDialog(ui, ("Accept '" + msg.content + "' from " + msg.sender + " ?")) == 0) {

                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(msg.content));
                        int returnVal = jf.showSaveDialog(ui);

                        String saveTo = jf.getSelectedFile().getPath();
                        if (saveTo != null && returnVal == JFileChooser.APPROVE_OPTION) {
                            Download dwn = new Download(saveTo, ui);
                            Thread t = new Thread(dwn);
                            t.start();
                            //send(new Message("upload_res", (""+InetAddress.getLocalHost().getHostAddress()), (""+dwn.port), msg.sender));
                            send(new Message("upload_res", ui.username, ("" + dwn.port), msg.sender));
                        } else {
                            send(new Message("upload_res", ui.username, "NO", msg.sender));
                        }
                    } else {
                        send(new Message("upload_res", ui.username, "NO", msg.sender));
                    }
                } else if (msg.type.equals("upload_res")) {
                    if (!msg.content.equals("NO")) {
                        int port = Integer.parseInt(msg.content);
                        String addr = msg.sender;

                        ui.jSelectFileButton.setEnabled(false);
                        ui.jSendFileButton.setEnabled(false);
                        Upload upl = new Upload(addr, port, ui.file, ui);
                        Thread t = new Thread(upl);
                        t.start();
                    } else {
                        //ui.jChatTextArea.append("[SERVER > Me] : " + msg.sender + " rejected file request\n");
                        ui.chatModel.addElement(new Message(msg.type, "SERVER", msg.sender + " rejected file request", "Me", systemIcon));
                    }
                } else if (msg.type.equals("hist")) {
                    hist_list = msg.content_list;
                    //ui.jChatTextArea.setText("");
                    ui.chatModel.removeAllElements();
                    if (hist_list != null) {
                        for (int i = 0; i < hist_list.size(); i++) {
                            //System.out.println(hist_list.get(i).toString());
                            //ui.jChatTextArea.append("[" + (hist_list.get(i).sender) + " > " + (hist_list.get(i).recipient + "] : " + hist_list.get(i).content + "\n"));
                            ui.chatModel.addElement(new Message(hist_list.get(i).type, hist_list.get(i).sender, hist_list.get(i).content, hist_list.get(i).recipient, checkIcon(hist_list.get(i).pic)));
                        }
                    }
                } else {
                    System.out.println(msg.type + msg.content + msg.recipient);
                    //ui.jChatTextArea.append("[SERVER > Me] : Unknown message type\n");
                    ui.chatModel.addElement(new Message(msg.type, "SERVER", "Unknown message type", "Me", systemIcon));
                }
                chkRea = true;
                ui.jChatList = new JList(ui.chatModel);
            } catch (Exception ex) {
                keepRunning = false;
                //ui.jChatTextArea.append("[Application > Me] : Connection Failure\n");
                ui.chatModel.addElement(new Message("Ex", "Application", "Connection Failure", "Me", systemIcon));
                ui.jSelectFileButton.setEnabled(false);
                ui.jSelectFileButton.setEnabled(false);

                for (int i = 1; i < ui.model.size(); i++) {
                    ui.model.removeElementAt(i);
                }

                ui.clientThread.stop();

                System.out.println("Exception SocketClient run()");
                ex.printStackTrace();
            }
        }
    }

    public void send(Message msg) {
        try {
            Out.writeObject(msg);
            Out.flush();
            chkRea = false;
            System.out.println("Outgoing : " + msg.toString());
            if (msg.type.equals("message") && !msg.content.equals(".bye")) {
                String msgTime = (new Date()).toString();
                try {

                    DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
                    table.addRow(new Object[]{"Me", msg.content, msg.recipient, msgTime});
                } catch (Exception ex) {
                }
            }
        } catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }

    public void closeThread(Thread t) {
        t = null;
    }

    public boolean checkLogin() {
        return chkLog;
    }

    public boolean checkReady() {
        return chkRea;
    }

    public Message toMessageIcon(Message msg, ImageIcon icon) {
        Message msgIcon = new Message(msg.type, msg.sender, msg.content, msg.recipient, icon);
        return msgIcon;
    }

    public ImageIcon checkIcon(String pic) {
      try{
        switch (Integer.parseInt(pic)) {
            case 0:
                return unkownIcon;
            case 1:
                return man1Icon;
            case 2:
                return man2Icon;
            case 3:
                return ninja3Icon;
            case 4:
                return man4Icon;
            case 5:
                return man5Icon;
            case 6:
                return man6Icon;
            case 7:
                return man7Icon;
            case 8:
                return man8Icon;
            default:
                return unkownIcon;
        }
      }catch(NumberFormatException ex){
          System.out.println("checkIcon: numPic error >> +"+pic+"+");
          return unkownIcon;
      }
    }
}
