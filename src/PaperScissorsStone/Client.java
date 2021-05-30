
package PaperScissorsStone;
import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;


class Client extends JFrame implements ActionListener   {

	// A T R I B U T O S
	private JButton boton1,boton2,boton3, next;
	public static JFrame frame;
	private static JPanel panel;
	public static JTextField menssage  = new javax.swing.JTextField();
    JTextField selected = new javax.swing.JTextField();

	public static final Color MY_Yellow = new Color(255,216,59);
	
	private static BufferedReader inFromUser;
	private	static Socket clientSocket;
	private	static DataOutputStream outToServer;
	private	static BufferedReader inFromServer;
		
	private static final long serialVersionUID = 1L;

    private static String host = "localhost";

    private static Integer port = 1337;

    private static Double versionNumber = 1.0;

    // titulo de la ventana
    public void setTitle(String title){
        frame.setTitle(title); 
    }
    
    //constructor
    public Client() {
    	
    	 frame = new JFrame();  
         panel = new JPanel();  
         panel.setLayout(new FlowLayout());  
         panel.setBounds(0,0,750,450);
         
        // selected  = new javax.swing.JTextField();
		 selected.setEditable(false);
		 selected.setHorizontalAlignment(JTextField.CENTER);
		 selected.setBackground(MY_Yellow);
		 selected.setBounds(200,290,400,20);
		 selected.setFont(new Font("Verdana", Font.PLAIN, 13));

		 setLayout(null);
	     boton1=new JButton(new ImageIcon("src/img/papel.png"));
	     boton1.setBounds(230,100,160,220);
	     boton1.setBackground(MY_Yellow);
	     panel.add(boton1);
	     boton1.addActionListener(this);
	     
	     boton2=new JButton(new ImageIcon("src/img/piedra.png"));
	     boton2.setBounds(10,100,160,220);
	     boton2.setBackground(MY_Yellow);
	     panel.add(boton2);
	     boton2.addActionListener(this);
	     
	     boton3=new JButton(new ImageIcon("src/img/tijera.png"));
	     boton3.setBounds(450,100,160,220);
	     boton3.setBackground(MY_Yellow);
	     panel.add(boton3);
	     boton3.addActionListener(this);  
	     
	     panel.setBackground(MY_Yellow);
	     
	     frame.add(panel);  

	     frame.setBounds(0,0,850,450);
	     frame.setVisible(true);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
	     boton1.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ae) {
		    	 boton1.setBackground(Color.WHITE);
				 boton2.setBackground(MY_Yellow);
				 boton3.setBackground(MY_Yellow);
				 
				selected.setText("Seleccionaste papel");
				frame.add(selected);
		     }
		   }
		 );
	     
	     
	     boton2.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ae) {
		    	 boton1.setBackground(MY_Yellow);
				 boton2.setBackground(Color.WHITE);
				 boton3.setBackground(MY_Yellow);
				selected.setText("Seleccionaste piedra");
				frame.add(selected);
		     }
		   }
		 );
		   
	     boton3.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ae) {
		    	 boton1.setBackground(MY_Yellow);
				 boton2.setBackground(MY_Yellow);
				 boton3.setBackground(Color.WHITE);
				 
				selected.setText("Seleccionaste tijera");
				frame.add(selected);
		     }
		   }
		 );
	     
	    
     }
    
    public static void main(String args[]) throws Exception {
	
    	Client.inFromUser = new BufferedReader(new InputStreamReader(
    			System.in));
    	Client.clientSocket = new Socket(Client.host, Client.port);
    	Client.outToServer = new DataOutputStream(
    			clientSocket.getOutputStream());
    	Client.inFromServer = new BufferedReader(new InputStreamReader(
    			clientSocket.getInputStream()));
     
	 	Client formulario1 = new Client();

    	// TITLE PLAYER 
	 	try {
	 		Client.inFromServer = new BufferedReader(new InputStreamReader(
	 				clientSocket.getInputStream()));
	 		String response = inFromServer.readLine();	 		
	 		frame.setTitle(response);
	 	 
	 	} catch (IOException e2) {
	 		// TODO Auto-generated catch block
	 		e2.printStackTrace();
	 	}
	
    }

	public void actionPerformed(ActionEvent e) {
		
		try {
			Client.inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
		 
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		// respuesta del server
		   menssage.setBounds(200,320,400,20);
		   menssage.setEditable(false);
		   menssage.setHorizontalAlignment(JTextField.CENTER);
		   menssage.setBackground(MY_Yellow);
		     
		// TODO Auto-generated method stub
		 if (e.getSource()==boton1) {		  
		
         	try {
    			Client.outToServer.writeBytes("P\n");
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         	
         }
         else if (e.getSource()==boton2) {
        	
         	try {
    			Client.outToServer.writeBytes("R\n");
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         }
         else if (e.getSource()==boton3) {
	   	 	
         	try {
    			Client.outToServer.writeBytes("S\n");
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         }    
	     
		 
	    try {
		    String response = "";
			response = inFromServer.readLine();
			System.out.println(response);
			menssage.setFont(new Font("Verdana", Font.PLAIN, 17));
			menssage.setText(response);
			frame.add(menssage);
			
			if(response.contains("Partida finalizada")){
				 boton1.setVisible(false);
				 boton2.setVisible(false);
				 boton3.setVisible(false);
				 selected.setVisible(false);
				 clientSocket.close();
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
}
