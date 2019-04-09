
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;


public class Main extends JFrame  {
		int portNumber = 5000;
		String ipAdress = "192.168.137.90";
		boolean done = false;
		DataInputStream dis;
		DataOutputStream dos;
		Canvas canvas;
		int canvasX = 700;
		int canvasy = 700;
		
		 // constuctor 
		Main() 
	    { 
	        super("canvas"); 
	  
	        // create a empty canvas 
	         canvas = new Canvas() { 
	  
	            // paint the canvas 
	            public void paint(Graphics g) 
	            { 
	              
	  
	            } 
	        }; 
	        canvas.addKeyListener(new MKeyListener());
	  
	        // set background 
	        canvas.setBackground(Color.black); 
	  
	        add(canvas); 
	        setSize(canvasX, canvasy); 
	        show(); 
	    } 
		
	public static void main(String[] args) {
		 
		
		Main main = new Main();
		main.communicate();
	}
	
 public void communicate () 
 {
	 
	 
		Socket s;
		try {
			s = new Socket(ipAdress , portNumber);
		
	
		System.out.println("Setup socket");
		
		 dis = new DataInputStream(s.getInputStream());
		 dos = new DataOutputStream(s.getOutputStream());
		 int previusX = 0;
		 int previusY = 0;
		 while(!done)
			{
			 try
			 {
				 
			 String readMessage = dis.readUTF();
			 String[] cordinate = readMessage.split(" ");
			 int newX= (int) Float.parseFloat(cordinate[0]);
			 int newY= (int) Float.parseFloat(cordinate[1]);
			 System.out.println(newX);
			 System.out.println(newY);
			 Graphics g = canvas.getGraphics();
			 g.setColor(Color.RED);
		     g.drawLine(canvasX/2-(previusX/5), canvasy/2-(previusY/5), canvasX/2-(newX/5), canvasy/2-(newY/5));
			 canvas.paint(g);
			 previusX = newX;
			 previusY = newY;
			 }
			 catch (Exception ex)
			 {
				 System.out.println(ex);
			 }
			}
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
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
   /* @Override
    public void keyReleased(KeyEvent event) {
    	 int keyCode = event.getKeyCode();
  		switch( keyCode ) { 
  	    case KeyEvent.VK_UP:
  	    	sendMessage("pause");
  	        // handle up 
  	        break;
  	    case KeyEvent.VK_DOWN:
  	    	sendMessage("pause");
  	        // handle down 
  	        break;
  	    case KeyEvent.VK_LEFT:
  	    	sendMessage("pause");
  	        // handle left
  	        break;
  	    case KeyEvent.VK_RIGHT :
  	    	sendMessage("pause");
  	        // handle right
  	        break;
  	    case KeyEvent.VK_SPACE :
  	    	sendMessage("pause");
  	        // handle right
  	    	done = true;
  	        break;
  	 }
    	
}*/
}
}
