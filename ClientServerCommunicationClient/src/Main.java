import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main  {
		int portNumber = 5000;
		String ipAdress = "192.168.137.124";
		boolean done = false;
		DataInputStream dis;
		DataOutputStream dos;
		JTextField textField;
		
	public static void main(String[] args) {
		 
		 
		Main main = new Main();
		main.communicate();
	}
 public void communicate () 
 {
	 textField = new JTextField();
	 
	  textField.addKeyListener(new MKeyListener());
	 
	  JFrame jframe = new JFrame();
	 
	  jframe.add(textField);
	 
	  jframe.setSize(400, 350);
	 
	  jframe.setVisible(true);
	 
	 System.out.println("hey");
	 
	 
		Socket s;
		try {
			s = new Socket(ipAdress , portNumber);
		
	
		System.out.println("Setup socket");
		
		 dis = new DataInputStream(s.getInputStream());
		 dos = new DataOutputStream(s.getOutputStream());
		 while(!done)
			{
			}
		/*
		Scanner sc = new Scanner(System.in);
		boolean running= true; 
		
		while(running)
		{
			System.out.println("Say something to the robot");
			if(sc.hasNext()) {
				   String token = sc.next();
				   
				}*/
			
			//message.intValue();
			//System.out.print(message.intValue());
			/*dos.writeUTF(message);
			dos.flush();*/
			
			/*String readMessage = dis.readUTF();
			if(readMessage.toLowerCase().equals("quit"))
			{
				running = false ;
			}
			else
			{
				System.out.println("Robot say: "+ readMessage);
			}*/
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 }
 public void sendMessage(String message) 
 {
	 
	 System.out.println(message);
	 try {
		dos.writeUTF(message);
		dos.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
 }

class MKeyListener extends KeyAdapter {
	 
    @Override
    public void keyPressed(KeyEvent event) {
 
    	 int keyCode = event.getKeyCode();
 		switch( keyCode ) { 
 	    case KeyEvent.VK_UP:
 	    	sendMessage("forward");
 	        // handle up 
 	        break;
 	    case KeyEvent.VK_DOWN:
 	    	sendMessage("backward");
 	        // handle down 
 	        break;
 	    case KeyEvent.VK_LEFT:
 	    	sendMessage("left");
 	        // handle left
 	        break;
 	    case KeyEvent.VK_RIGHT :
 	    	sendMessage("right");
 	        // handle right
 	        break;
 	    case KeyEvent.VK_SPACE :
 	    	sendMessage("stop");
 	        // handle right
 	    	done = true;
 	        break;
 	 }
    }
}
}
