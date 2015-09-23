package com.demo.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.demo.rest.model.Message;

public class MessageService {
	
	public static List<Message> messages = new ArrayList<Message>();
	
	
	
	public List<Message> getAllMessages(){
		return messages;
	}
	
	public String  deletedMessage(long id){
		return id+" is deleted";
	}
	
	public Message getMessage(long id){
		
		for(Message msg: messages){
			if(msg.getId()== id){
				return msg;
			}
		}
		return null;
	}
	
    public Message  addMessage(Message msg){		
	  msg.setId(messages.size()+1);
	  messages.add(msg);
	  return msg;
	}
    
    public Message  deleteMessage(long id){		
    	for(Message msg: messages){
			if(msg.getId()== id){				
				messages.remove(msg);
				return msg;
			}
		}
    	return null;
  	}
    
    public Message updateMessage(Message message){		
    	    	
    	for(int index=0;index< messages.size();index++){    		
    		if(messages.get(index).getId() == message.getId()){
    			System.out.println("Message found");
    			messages.set(index,message);
    			return message;
    		}
    	}	
    	
      return null;
  	}

    }
