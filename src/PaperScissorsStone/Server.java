
package PaperScissorsStone;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

	private static Integer port = 1337;

	private static Double versionNumber = 1.0;

	private static String welcomeMsg = "--- Server iniciado. Juego: piedra, papel o tijera" + versionNumber + " --- \n";

	private static int getPort() {

		Integer input = 0;
		Scanner sc = new Scanner(System.in);
		sc.close();
		return input == 0 ? Server.port : input;
	}

	public static void main(String args[]) throws Exception {

		String resClient_1 = "";
		String resClient_2 = "";
		String inputClient_1;
		String inputClient_2;
		
		//contador para cantidad de partidas ganadas
		int puntaje_cliente_1 = 0;
		int puntaje_cliente_2 = 0;

		//cantidad total de partidas jugadas
		int round = 0;
		
		//mensjae de conexion al server
		System.out.println(Server.welcomeMsg);

		Server.port = Server.getPort();

		// utilizacion de clase server socket para conexion de 2 clientes
		ServerSocket welcomeSocket = new ServerSocket(Server.port);
		System.out.println("\nOk, we're up and running on port " + welcomeSocket.getLocalPort() + " ...");

		 // Jugador #1
				Socket client_1 = welcomeSocket.accept();
				if (client_1.isConnected()) {
					System.out.println("\nEl Jugador #1 (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
							+ client_1.getLocalPort() + ") se unió ... esperando al Jugador #2 ...");
					DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
					outClient_1.writeBytes("Jugador #1\n");
				}
			
	
		// Jugador #2
				Socket client_2 = welcomeSocket.accept();
				if (client_2.isConnected()) {
					System.out.println("El Jugador #2 (" + (client_2.getLocalAddress().toString()).substring(1) + ":"
							+ client_1.getLocalPort() + ") se unió ... comenzando el juego ...");
					DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
					outClient_2.writeBytes("Jugador #2\n");
				}
				
				while(true){

				DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
				BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

				DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
				BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

				round++;

				//server lee los datos enviados por cada cliente
				inputClient_1 = inClient_1.readLine();
				inputClient_2 = inClient_2.readLine();
				System.out.println("\nRound #"+round);

				// Logica del juego piedra, papel o tijera
				
				/* Valores
				 * P: papel
				 * R: piedra
				 * S: tijeras
				 */
				
				if (inputClient_1.equals(inputClient_2)) {
					resClient_1 = "Es un empate";
					resClient_2 = "Es un empate";
					System.out.println("Es un empate");
				}
				else if (inputClient_1.equals("R") && inputClient_2.equals("S")) {
					resClient_1 = "Ganaste!";
					resClient_2 = "Perdiste :(";
					System.out.println("Gana el Jugador #1");
					puntaje_cliente_1++;
	
				}
				else if (inputClient_1.equals("S") && inputClient_2.equals("R")) {
					resClient_1 = "Perdiste :(";
					resClient_2 = "Ganaste!";
					System.out.println("Gana el jugador #2");
					puntaje_cliente_2++;
				}
				else if (inputClient_1.equals("R") && inputClient_2.equals("P")) {
					resClient_1 = "Perdiste :(";
					resClient_2 = "Ganaste!";
					System.out.println("Gana el jugador #2");
					puntaje_cliente_2++;
				}
				else if (inputClient_1.equals("P") && inputClient_2.equals("R")) {
					resClient_1 = "Ganaste!";
					resClient_2 = "Perdiste :(";
					System.out.println("Gana el Jugador #1");
					puntaje_cliente_1++;
				}
				else if (inputClient_1.equals("S") && inputClient_2.equals("P")) {
					resClient_1 = "Ganaste!";
					resClient_2 = "Perdiste :(";
					System.out.println("Gana el Jugador #1");
					puntaje_cliente_1++;
				}
				else if (inputClient_1.equals("P") && inputClient_2.equals("S")) {
					resClient_1 = "Perdiste :(";
					resClient_2 = "Ganaste!";
					System.out.println("Gana el jugador #2");
					puntaje_cliente_2++;
				}
		
				//al final de cada partida el server se fija los contadores de puntos 
				if(puntaje_cliente_2==3||puntaje_cliente_1==3) {
					if(puntaje_cliente_2 == 3) {
						System.out.println("\nPartida finalizada, Gana el jugador #2");
						outClient_1.writeBytes("Round #"+round+" Partida finalizada Ganaste!\n");
						outClient_2.writeBytes("Round #"+round+" Partida finalizada Perdiste!\n");
					}
					else{
						System.out.println("\nPartida finalizada, Gana el Jugador #1");
						outClient_1.writeBytes("Round #"+round+" Partida finalizada Perdiste!\n");
						outClient_2.writeBytes("Round #"+round+" Partida finalizada Ganaste!\n");
					}
					
					//fin del juego
					client_1.close();
					client_2.close();
					break;
				}
				else {
					outClient_1.writeBytes("Round #"+round+" - "+resClient_1.toUpperCase()+"\n");
					outClient_2.writeBytes("Round #"+round+" - "+resClient_2.toUpperCase()+"\n");
				}
		}
	}
}