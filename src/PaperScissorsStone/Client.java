/**
 * The client class creates a connection to the server at default port 1337.
 * Waits for the user to input a single character with the keyboard. The
 * character has to be ’R’ (rock), ’P’ (paper) or ’S’ (scissors). Sends the
 * character to the server via the TCP protocol. Waits for a reply from the
 * Server. Prints the message received from the Server. Closes the connection.
 *
 * @author Mathias Schilling <https://github.com/pinkbigmacmedia>
 * @version 1.0
 * 
 */

package PaperScissorsStone;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;


class Client extends JFrame implements ActionListener   {

	// A T R I B U T O S
	private JButton boton1,boton2,boton3;
	public JFrame marco;
	
	public static final Color MY_Yellow = new Color(255,216,59);
	
	private static BufferedReader inFromUser;
	private	static Socket clientSocket;
	private	static DataOutputStream outToServer;
	private	static BufferedReader inFromServer;
		
	private static final long serialVersionUID = 1L;

    private static String host = "localhost";

    private static Integer port = 1337;

    private static Double versionNumber = 1.0;

   // private static String msgWelcome = "--- Welcome to Paper Scissors Stone V. "
	//    + versionNumber + " --- \n";

  //  private static String msgRules = "\nRule set:\n - (R)ock beats (S)cissors\n - (S)cissors beats (P)aper\n - (P)aper beats (R)ock\n";
    
    //constructor
    public Client() {
    	 //Border emptyBorder = BorderFactory.createEmptyBorder();
    	 
		 setLayout(null);
	     boton1=new JButton(new ImageIcon("src/img/papel.png"));
	     boton1.setBounds(230,100,160,220);
	     add(boton1);
	     boton1.addActionListener(this);
	     
	     boton2=new JButton(new ImageIcon("src/img/piedra.png"));
	     boton2.setBounds(10,100,160,220);
	     add(boton2);
	     boton2.addActionListener(this);
	     
	     boton3=new JButton(new ImageIcon("src/img/tijera.png"));
	     boton3.setBounds(450,100,160,220);
	     add(boton3);
	     boton3.addActionListener(this);        	
     }
    
    public static void main(String args[]) throws Exception {
	
	//System.out.println(Client.msgWelcome);
    	Client.inFromUser = new BufferedReader(new InputStreamReader(
    			System.in));
    	Client.clientSocket = new Socket(Client.host, Client.port);
    	Client.outToServer = new DataOutputStream(
    			clientSocket.getOutputStream());
    	Client.inFromServer = new BufferedReader(new InputStreamReader(
    			clientSocket.getInputStream()));



	JLabel label1=new JLabel("Sistema de Facturaci�n.");
    label1.setBounds(10,20,300,30);
     
	 	try {
	 		Client.inFromServer = new BufferedReader(new InputStreamReader(
	 				clientSocket.getInputStream()));
	 		String response = inFromServer.readLine();
	 		label1 = new JLabel(response);
	 	    System.out.println(response);
	 	    System.out.println(label1);
	 	 
	 	} catch (IOException e2) {
	 		// TODO Auto-generated catch block
	 		e2.printStackTrace();
	 	}
	 
	 	Client formulario1 = new Client();
	    formulario1.setBounds(0,0,650,450);
	    formulario1.setVisible(true);
	    formulario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    formulario1.getContentPane().setBackground(MY_Yellow);
	    formulario1.getContentPane().add(label1);

	 	// add(titulo);
	    
	//while (true){
        
	/*	do {
	
		    if (input.equals("-rules")) {
		    	System.out.println(Client.msgRules);
		    }
	
		    // Prompt user for select rock, paper or scissors ...
		    System.out
			    .println("Start the game by selecting (R)ock (P)aper, (S)cissors");
		    System.out.print("or type \"-rules\" in order to see the rules: ");
		    input = inFromUser.readLine();
		    
		} while (!input.equals("R") && !input.equals("P") && !input.equals("S"));
	*/
		// Transmit input to the server and provide some feedback for the user
		
       

		//outToServer.writeBytes(input + "\n");
		/*System.out
			.println("\nYour input ("
				+ input
				+ ") was successfully transmitted to the server. Now just be patient and wait for the result ...");
		 */
		
		// Catch respones
		//response = inFromServer.readLine();
	
		// Display respones
		/*System.out.println(response);
		if(response.contains("partida finalizada")) {
			clientSocket.close();
			break;
		}*/
	
		// Close socket
		//clientSocket.close();
	
		//}

    }

	public void actionPerformed(ActionEvent e) {
		
		try {
			Client.inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		 if (e.getSource()==boton1) {
             setTitle("Seleccionaste papel");
         	try {
    			Client.outToServer.writeBytes("P\n");
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         	
         }
         else if (e.getSource()==boton2) {
             setTitle("Seleccionaste piedra");
         	try {
    			Client.outToServer.writeBytes("R\n");
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         }
         else if (e.getSource()==boton3) {
             setTitle("Seleccionaste tijera");
         	try {
    			Client.outToServer.writeBytes("S\n");
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         }    
		 
	    String response = "";
		
	    try {
			response = inFromServer.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    System.out.println(response);
		    
	}

}
