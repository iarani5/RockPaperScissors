/**
 * The server class waits for the connection of the two clients client_1
 * and client_2 (in either order) on port 1337. Receives one character
 * from client_1 and one character from client_2 (in either order) and
 * calculates the winner of the game based on a rule set. After sending a
 * correspondent massage to each client the server waits again for two
 * clients to connect.
 *
 * @author Mathias Schilling <https://github.com/pinkbigmacmedia>
 * @version 1.0
 * 
 */

package PaperScissorsStone;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

	private static Integer port = 1337;

	private static Double versionNumber = 1.0;

	private static String welcomeMsg = "--- Welcome to Paper Scissors Stone Server V. " + versionNumber + " --- \n";

	private static boolean validPort(Integer x) {
		return x >= 1 && x <= 65535 ? true : false;
	}

	private static int getPort() {

		Integer input = 0;

		Scanner sc = new Scanner(System.in);

		/*do {
			System.out.print("Please select a port by entering an integer value between 1 and 65535 or\n");
			System.out.print("insert \"0\" in order to continue with the default setting (" + Server.port + "): ");
			input = 0;
			//sc.nextInt();

		} while (input != 0 && !Server.validPort(input));*/
		//input = 0;
		sc.close();

		return input == 0 ? Server.port : input;
	}

	public static void main(String args[]) throws Exception {

		String resClient_1 = "";
		String resClient_2 = "";
		String inputClient_1;
		String inputClient_2;
		
		int puntaje_cliente_1 = 0;
		int puntaje_cliente_2 = 0;


		int round = 0;
		// Print welcome msg
		System.out.println(Server.welcomeMsg);

		// Set port
		Server.port = Server.getPort();

		// Create new server socket & dump out a status msg
		ServerSocket welcomeSocket = new ServerSocket(Server.port);
		System.out.println("\nOk, we're up and running on port " + welcomeSocket.getLocalPort() + " ...");

		
			
				
				// Jugador #1
				Socket client_1 = welcomeSocket.accept();
				if (client_1.isConnected()) {
					System.out.println("\nPlayer one (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
							+ client_1.getLocalPort() + ") has joined ... waiting for player two ...");
					DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
					outClient_1.writeBytes("Jugador #1\n");
				}
			
	
				// Jugador #2
				Socket client_2 = welcomeSocket.accept();
				if (client_2.isConnected()) {
					System.out.println("Player two (" + (client_2.getLocalAddress().toString()).substring(1) + ":"
							+ client_1.getLocalPort() + ") has joined ... lets start ...");
					DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
					outClient_2.writeBytes("Jugador #2\n");
				}
				
				while(true){

				DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
				BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

				DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
				BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

				round++;

				inputClient_1 = inClient_1.readLine();
				inputClient_2 = inClient_2.readLine();
				System.out.println("\nRound #"+round);

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