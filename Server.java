package project;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	public static void main(final String[] args) {
		try {
			int i = 0;
			ServerSocket Server = new ServerSocket(4563);
			while (true) {
				i++;
				Socket client = Server.accept();
				System.out.println("client number: " + i + " is connected to the server .");
				ClientConnection clientconnection = new ClientConnection(client);
				clientconnection.start();

			} // while true
		} // try 18

		catch (IOException e) {
			e.printStackTrace();
		}
	}// main 17
	
	static class ClientConnection extends Thread {
		Socket client;
		DataInputStream ClientRead ;
		DataOutputStream ClientWrite ;
		ClientConnection(Socket client) {
			this.client = client;
		}

		public void run() {
			try {
				// To receive messages from the other side
				 ClientRead = new DataInputStream(client.getInputStream());

				// To send messages:
				ClientWrite = new DataOutputStream(client.getOutputStream());

				ClientWrite.writeUTF("connected");
				// start connection with client
				while (true) {
//////////////////////////////////////////////////////////code get and host////////////////////////////////////////////////////////////////////////////////////////
					String version = "HTTP/1.1 ";

					// hence we received name file
					String filename = ClientRead.readUTF();
					File file = new File(
							"C:\\Users\\hp\\eclipse-workspace\\PROJECT_HTTP_53\\Facebook – log in or sign up_files\\");

					System.out.println("Request received  ");
					System.out.println(
							"GET/" + file.getName() + " " + filename + version);

					System.out.println("HOST " + file.getParent());

					/// end code get and host

//////////////////////////////////////////////////////////////print if page exist or no////////////////////////////////////////////////////////////////////////////// 
					// hence we assume we work with .com
					URL url = new URL("http://www." + filename + ".com");

					HttpURLConnection CONNECTION = (HttpURLConnection) url.openConnection();

					ClientWrite.writeUTF(version + CONNECTION.getResponseCode() + CONNECTION.getResponseMessage());

/////////////////////////////////////////////////////////////////end code exist page or no////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////code date///////////////////////////////////////////////////////////////////////////
					// to write day in word
					SimpleDateFormat sdf = new SimpleDateFormat("EEE");

					String str = sdf.format(new Date());

					Date date = new Date();
					String DATE = "Date :" + str + "," + date.toGMTString();
					ClientWrite.writeUTF(DATE);
////////////////////////////////////////////////////////////////end code date///////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////display file content/////////////////////////////////////////////////////////////////////////////////
					StringBuilder filecontent = new StringBuilder();
					FileReader filereader = new FileReader(
							"C:\\Users\\hp\\eclipse-workspace\\PROJECT_HTTP_53\\Facebook – log in or sign up_files\\"+filename);

					BufferedReader BUFFER = new BufferedReader(filereader);
					String ptr;
					while ((ptr = BUFFER.readLine()) != null) {

						filecontent.append(ptr);
					}
					BUFFER.close();
					String result = filecontent.toString();
					ClientWrite.writeUTF(result);

////////////////////////////////////////////////////////////////end code display file content///////////////////////////////////////////////////////////////////////////
					client.close();	
					ClientRead.close();
					ClientWrite.close();
					
				} // while
			} // try
			catch (IOException e) {
			}

		}
	}

}
