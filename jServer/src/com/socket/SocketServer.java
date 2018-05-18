package com.socket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import com.socket.Database;

class ServerThread extends Thread { 
	
    public SocketServer server = null;
    public Socket socket = null;
    public int ID = -1;
    public String username = "";
    public ObjectInputStream streamIn  =  null;
    public ObjectOutputStream streamOut = null;
    public ServerFrame ui;

    public ServerThread(SocketServer _server, Socket _socket){  
    	super();
        server = _server;
        socket = _socket;
        ID     = socket.getPort();
        ui = _server.ui;
    }
    
    public void send(Message msg){
        try {
            streamOut.writeObject(msg);
            streamOut.flush();
        } 
        catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)]");
        }
    }
    
    public void send_hist(ArrayList msg){
        try {
            streamOut.writeObject(msg);
            streamOut.flush();
        } 
        catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)]");
        }
    }
    
    public int getID(){  
	    return ID;
    }
   
    @SuppressWarnings("deprecation")
	public void run(){  
    	ui.jTextArea1.append("\nServer Thread " + ID + " running.");
        while (true){  
    	    try{  
                Message msg = (Message) streamIn.readObject();
    	    	server.handle(ID, msg);
            }
            catch(Exception ioe){  
            	System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.remove(ID);
                stop();
            }
        }
    }
    
    public void open() throws IOException {  
        streamOut = new ObjectOutputStream(socket.getOutputStream());
        streamOut.flush();
        streamIn = new ObjectInputStream(socket.getInputStream());
    }
    
    public void close() throws IOException {  
    	if (socket != null)    socket.close();
        if (streamIn != null)  streamIn.close();
        if (streamOut != null) streamOut.close();
    }
}





public class SocketServer implements Runnable {
    
    public ServerThread clients[];
    public ServerSocket server = null;
    public Thread       thread = null;
    public int clientCount = 0, port = 13000;
    public ServerFrame ui;
    public HistoryDB hist = new HistoryDB(System.getProperty("user.home")+"/Desktop/History.xml");
    public Database db;

    public SocketServer(ServerFrame frame){
       
        clients = new ServerThread[50];
        ui = frame;
        db = new Database(ui.filePath);
        
	try{  
	    server = new ServerSocket(port);
            port = server.getLocalPort();
	    ui.jTextArea1.append("Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            ui.jTextArea1.append("Can not bind to port : " + port + "\nRetrying"); 
            ui.RetryStart(0);
	}
    }
    
    public SocketServer(ServerFrame frame, int Port){
       
        clients = new ServerThread[50];
        ui = frame;
        port = Port;
        db = new Database(ui.filePath);
        
	try{  
	    server = new ServerSocket(port);
            port = server.getLocalPort();
	    ui.jTextArea1.append("Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            ui.jTextArea1.append("\nCan not bind to port " + port + ": " + ioe.getMessage()); 
	}
    }
	
    public void run(){  
	while (thread != null){  
            try{  
		ui.jTextArea1.append("\nWaiting for a client ..."); 
	        addThread(server.accept()); 
	    }
	    catch(Exception ioe){ 
                ui.jTextArea1.append("\nServer accept error: \n");
                ui.RetryStart(0);
	    }
        }
    }
	
    public void start(){  
    	if (thread == null){  
            thread = new Thread(this); 
	    thread.start();
	}
    }
    
    @SuppressWarnings("deprecation")
    public void stop(){  
        if (thread != null){  
            thread.stop(); 
	    thread = null;
	}
    }
    
    private int findClient(int ID){  
    	for (int i = 0; i < clientCount; i++){
        	if (clients[i].getID() == ID){
                    return i;
                }
	}
	return -1;
    }
	
    public synchronized void handle(int ID, Message msg){  
	if (msg.content.equals(".bye")){
            Announce("signout", "SERVER", msg.sender,db.getNumPic(msg.sender));
            remove(ID); 
	}
	else{
            if(msg.type.equals("login")){
                if(findUserThread(msg.sender) == null){
                    if(db.checkLogin(msg.sender, msg.content)){
                        clients[findClient(ID)].username = msg.sender;
                        clients[findClient(ID)].send(new Message("login", "SERVER", "TRUE", msg.sender,db.getNumPic(msg.sender)));
                        Announce("newuser", "SERVER", msg.sender,db.getNumPic(msg.sender));
                        SendUserList(msg.sender);
                    }
                    else{
                        clients[findClient(ID)].send(new Message("login", "SERVER", "FALSE", msg.sender,db.getNumPic(msg.sender)));
                    } 
                }
                else{
                    clients[findClient(ID)].send(new Message("login", "SERVER", "FALSE", msg.sender,db.getNumPic(msg.sender)));
                }
            }
            else if(msg.type.equals("message")){
                hist.addMessage(new Message("history", msg.sender, msg.content, msg.recipient), new Date().toString());
                if(msg.recipient.equals("All")){
                    Announce("message", msg.sender, msg.content,db.getNumPic(msg.sender));
                }
                else{
                    findUserThread(msg.recipient).send(new Message(msg.type, msg.sender, msg.content, msg.recipient,db.getNumPic(msg.sender)));
                    clients[findClient(ID)].send(new Message(msg.type, msg.sender, msg.content, msg.recipient,db.getNumPic(msg.sender)));
                }
            }
            else if(msg.type.equals("test")){
                //clients[findClient(ID)].send(new Message("test", "SERVER", "OK", msg.sender));
            }
            else if(msg.type.equals("signup")){
                if(findUserThread(msg.sender) == null){
                    if(!db.userExists(msg.sender)){
                        db.addUser(msg.sender, msg.content);
                        clients[findClient(ID)].username = msg.sender;
                        clients[findClient(ID)].send(new Message("signup", "SERVER", "TRUE", msg.sender,db.getNumPic(msg.sender)));
                        clients[findClient(ID)].send(new Message("login", "SERVER", "TRUE", msg.sender,db.getNumPic(msg.sender)));
                        Announce("newuser", "SERVER", msg.sender,db.getNumPic(msg.sender));
                        SendUserList(msg.sender);
                    }
                    else{
                        clients[findClient(ID)].send(new Message("signup", "SERVER", "FALSE", msg.sender,db.getNumPic(msg.sender)));
                    }
                }
                else{
                    clients[findClient(ID)].send(new Message("signup", "SERVER", "FALSE", msg.sender,db.getNumPic(msg.sender)));
                }
            }
            else if(msg.type.equals("upload_req")){
                if(msg.recipient.equals("All")){
                    clients[findClient(ID)].send(new Message("message", "SERVER", "Uploading to 'All' forbidden", msg.sender,db.getNumPic(msg.sender)));
                }
                else{
                    findUserThread(msg.recipient).send(new Message("upload_req", msg.sender, msg.content, msg.recipient,db.getNumPic(msg.sender)));
                }
            }
            else if(msg.type.equals("upload_res")){
                if(!msg.content.equals("NO")){
                    String IP = findUserThread(msg.sender).socket.getInetAddress().getHostAddress();
                    findUserThread(msg.recipient).send(new Message("upload_res", IP, msg.content, msg.recipient));
                }
                else{
                    findUserThread(msg.recipient).send(new Message("upload_res", msg.sender, msg.content, msg.recipient));
                }
            }
            else if(msg.type.equals("jUserlist")){
                ArrayList<Message> msg_list= hist.getMessage(msg.sender,msg.recipient);
                if (msg_list != null) {
                    for (int i = 0; i < msg_list.size(); i++) {
                        msg_list.get(i).pic = db.getNumPic(msg_list.get(i).sender);
                        //System.out.println(msg_list.get(i).sender);
                    }
                }
                
                Message history = new Message("hist", msg.sender,msg_list , msg.recipient);
//                System.out.println("history"+history.toString());
                clients[findClient(ID)].send(history );
            }
	}
    }
    
    public void Announce(String type, String sender, String content,String numIcon){
        Message msg = new Message(type, sender, content, "All",numIcon);
        for(int i = 0; i < clientCount; i++){
            clients[i].send(msg);
        }
    }
    
    public void SendUserList(String toWhom){
        for(int i = 0; i < clientCount; i++){
            findUserThread(toWhom).send(new Message("newuser", "SERVER", clients[i].username, toWhom,db.getNumPic(clients[i].username)));
        }
    }
    
    public ServerThread findUserThread(String usr){
        for(int i = 0; i < clientCount; i++){
            if(clients[i].username.equals(usr)){
                return clients[i];
            }
        }
        return null;
    }
	
    @SuppressWarnings("deprecation")
    public synchronized void remove(int ID){  
    int pos = findClient(ID);
        if (pos >= 0){  
            ServerThread toTerminate = clients[pos];
            ui.jTextArea1.append("\nRemoving client thread " + ID + " at " + pos);
	    if (pos < clientCount-1){
                for (int i = pos+1; i < clientCount; i++){
                    clients[i-1] = clients[i];
	        }
	    }
	    clientCount--;
	    try{  
	      	toTerminate.close(); 
	    }
	    catch(IOException ioe){  
	      	ui.jTextArea1.append("\nError closing thread: " + ioe); 
	    }
	    toTerminate.stop(); 
	}
    }
    
    private void addThread(Socket socket){  
	if (clientCount < clients.length){  
            ui.jTextArea1.append("\nClient accepted: " + socket);
	    clients[clientCount] = new ServerThread(this, socket);
	    try{  
	      	clients[clientCount].open(); 
	        clients[clientCount].start();  
	        clientCount++; 
	    }
	    catch(IOException ioe){  
	      	ui.jTextArea1.append("\nError opening thread: " + ioe); 
	    } 
	}
	else{
            ui.jTextArea1.append("\nClient refused: maximum " + clients.length + " reached.");
	}
    }
}
