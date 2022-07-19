package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class Client {
	public static void main(String[] args) {
            
			try {
				Scanner sc= new Scanner(System.in); 

				InetAddress ip = InetAddress.getLocalHost();
				Socket client = new Socket (ip,4563);
				
				System.out.println("Connecting to the Server.....  ");

				// To receive messages from the other side
				DataInputStream clientRead=new DataInputStream(client.getInputStream());
				//To send messages: 
				DataOutputStream clientWrite=new DataOutputStream(client.getOutputStream());

				//ClientRead.readUTF():server send message connected 
				System.out.println("Server:"+clientRead.readUTF());

				
				System.out.println("Please enter file name  ");
				String filename=sc.nextLine(); 				
				
				//filename is :referer_frame.html
				File file=new File("C:\\Users\\hp\\eclipse-workspace\\PROJECT_HTTP_53\\Facebook – log in or sign up_files\\"+filename);
				if (file.exists())
				{
				System.out.println("Reply received ");
				clientWrite.writeUTF(filename);	
				}
				else 
				{
					System.out.println("FILE not exist ");

				}
				///received if page exist or no and version http
				System.out.println(clientRead.readUTF());
				
				///received date 
				System.out.println(clientRead.readUTF());

				///received file content
				System.out.println(clientRead.readUTF());
								
				clientRead.close();
				clientWrite.close();
	    		client.close();
                                
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}