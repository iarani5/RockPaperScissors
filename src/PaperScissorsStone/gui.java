package PaperScissorsStone;
/*
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class gui extends JFrame implements ActionListener{   
	
	private static final long serialVersionUID = 1L;

	 private JButton boton1,boton2,boton3;
	 
	    public gui() {
	        setLayout(null);
	        boton1=new JButton(new ImageIcon("src/img/papel.png"));
	        boton1.setBounds(10,100,160,220);
	        add(boton1);
	        boton1.addActionListener(this);
	        boton2=new JButton(new ImageIcon("src/img/piedra.png"));
	        boton2.setBounds(230,100,160,220);
	        add(boton2);
	        boton2.addActionListener(this);
	        boton3=new JButton(new ImageIcon("src/img/tijera.png"));
	        boton3.setBounds(450,100,160,220);
	        add(boton3);
	        boton3.addActionListener(this);        	
	    }
	    
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource()==boton1) {
	            setTitle("Seleccionaste papel");
	        }
	        else if (e.getSource()==boton2) {
	            setTitle("Seleccionaste piedra");

	        }
	        else if (e.getSource()==boton3) {
	            setTitle("Seleccionaste tijera");
	        }    
	    }
	    
	    /*public static void main(String[] ar){
	    	gui formulario1= new gui();
	        formulario1.setBounds(0,0,650,450);
	        formulario1.setVisible(true);
	        formulario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
}*/

