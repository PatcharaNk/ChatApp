package com.socket;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.util.ArrayList;

public class HistoryDB {
    
    public String filePath;
    public Database db;
    
    public HistoryDB(String filePath){
        this.filePath = filePath;
    }
    
    public void addMessage(Message msg, String time){
        
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);
 
            Node data = doc.getFirstChild();
            
            //Element room = doc.createElement("room");
            Element message = doc.createElement("message");
            Element _sender = doc.createElement("sender"); _sender.setTextContent(msg.sender);
            Element _content = doc.createElement("content"); _content.setTextContent(msg.content);
            Element _recipient = doc.createElement("recipient"); _recipient.setTextContent(msg.recipient);
            Element _time = doc.createElement("time"); _time.setTextContent(time);
            
            message.appendChild(_sender); message.appendChild(_content); message.appendChild(_recipient); message.appendChild(_time);
            data.appendChild(message);//data.appendChild(room);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
 
	   } 
           catch(Exception ex){
		System.out.println("Exceptionmodify xml");
	   }
	}
    
    public ArrayList<Message> getMessage(String send_name,String reci_name){
       
        try{
            ArrayList<Message> msg = new ArrayList<Message>();
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("message");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                msg.add(new Message("message", getTagValue("sender", eElement), getTagValue("content", eElement), getTagValue("recipient", eElement),"0"));
            }
//            
//            for(int j=0;j<msg.size();j++){
//                System.out.println("Before : "+msg.get(j).toString());
//            }
            for(int j = 0;j<msg.size();j++){
//                System.out.print(j+"  ");
//                System.out.println("Chk "+send_name+" "+reci_name);
//                System.out.println("sender : "+msg.get(j).sender+" recipient : "+msg.get(j).recipient+" context : "+msg.get(j).content);
                if(reci_name.equals("All")){
                    if(!msg.get(j).recipient.equals("All")){
                        msg.remove(j);
                        j--;
                    }
                }
                else{
                    if(((msg.get(j).sender).equals(send_name)&&(msg.get(j).recipient).equals(reci_name))||((msg.get(j).sender).equals(reci_name)&&(msg.get(j).recipient).equals(send_name))){

                    }else{
                        msg.remove(j);
                        j--;
                    }
                }
            }
//            
//            for(int j=0;j<msg.size();j++){
//                System.out.println("After : "+msg.get(j).toString());
//            }
            return msg;
        }
        catch(Exception ex){
            System.out.println("Database exception : userExists()");
            return null;
        }
    }
    
    public static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
	return nValue.getNodeValue();
  }
}
