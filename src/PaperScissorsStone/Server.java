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

		Integer input;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("Please select a port by entering an integer value between 1 and 65535 or\n");
			System.out.print("insert \"0\" in order to continue with the default setting (" + Server.port + "): ");
			input = sc.nextInt();

		} while (input != 0 && !Server.validPort(input));

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

		
			
			//while (i<=2) { //!welcomeSocket.isClosed()||
				
				// Player one
				Socket client_1 = welcomeSocket.accept();
				if (client_1.isConnected()) {
					System.out.println("\nPlayer one (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
							+ client_1.getLocalPort() + ") has joined ... waiting for player two ...");
					DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
					outClient_1.writeBytes("Jugador #1\n");
				}
			
	
				// Player two
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

				// Get client inputs
				inputClient_1 = inClient_1.readLine();
				inputClient_2 = inClient_2.readLine();
				System.out.println("\nRound #"+round);

				/**
				 * If the characters received from C1 and C2 are the same then the
				 * server sends back to both clients the string "DRAW".
				 */
				if (inputClient_1.equals(inputClient_2)) {
					resClient_1 = "Draw";
					resClient_2 = "Draw";
					System.out.println("It's a draw.");
				}
				/**
				 * If the server receives ’R’ from C1 and ’S’ from C2 it sends the
				 * string "YOU WIN" to C1 and the string "YOU LOSE" to C2.
				 */
				else if (inputClient_1.equals("R") && inputClient_2.equals("S")) {
					resClient_1 = "You win";
					resClient_2 = "You lose";
					System.out.println("Player one wins.");
					puntaje_cliente_1++;
	
				}
				/**
				 * If the server receives ’S’ from C1 and ’R’ from C2 it sends the
				 * string "YOU LOSE" to C1 and the string "YOU WIN" to C2.
				 */
				else if (inputClient_1.equals("S") && inputClient_2.equals("R")) {
					resClient_1 = "You lose";
					resClient_2 = "You win";
					System.out.println("Player two wins.");
					puntaje_cliente_2++;
				}
				/**
				 * If the server receives ’R’ from C1 and ’P’ from C2 it sends the
				 * string "YOU LOSE" to C1 and the string "YOU WIN" to C2.
				 */
				else if (inputClient_1.equals("R") && inputClient_2.equals("P")) {
					resClient_1 = "You lose";
					resClient_2 = "You win";
					System.out.println("Player two wins.");
					puntaje_cliente_2++;
				}
				/**
				 * If the server receives ’P’ from C1 and ’R’ from C2 it sends the
				 * string "YOU WIN" to C1 and the string "YOU LOSE" to C2.
				 */
				else if (inputClient_1.equals("P") && inputClient_2.equals("R")) {
					resClient_1 = "You win";
					resClient_2 = "You lose";
					System.out.println("Player one wins.");
					puntaje_cliente_1++;
				}
				/**
				 * If the server receives ’S’ from C1 and ’P’ from C2 it sends the
				 * string "YOU WIN" to C1 and the string "YOU LOSE" to C2.
				 */
				else if (inputClient_1.equals("S") && inputClient_2.equals("P")) {
					resClient_1 = "You win";
					resClient_2 = "You lose";
					System.out.println("Player one wins.");
					puntaje_cliente_1++;
				}
				/**
				 * If the server receives ’P’ from C1 and ’S’ from C2 it sends the
				 0* string "YOU LOSE" to C1 and the string "YOU WIN" to C2.
				 */
				else if (inputClient_1.equals("P") && inputClient_2.equals("S")) {
					resClient_1 = "You lose";
					resClient_2 = "You win";
					System.out.println("Player two wins.");
					puntaje_cliente_2++;
				}
	
				
				//client_1.close();
				//client_2.close();
	
				//System.out.println("\nWaiting for new players ...\n");
				if(puntaje_cliente_2==3||puntaje_cliente_1==3) {
					if(puntaje_cliente_2 == 3) {
						System.out.println("\nPartida finalizada, ganador Player two ");
						outClient_1.writeBytes("Round #"+round+" --> "+resClient_1.toUpperCase()+" --> Partida finalizada Ganaste!\n");
						outClient_2.writeBytes("Round #"+round+" --> "+resClient_2.toUpperCase()+" --> Partida finalizada Perdiste!\n");
					}
					else{
						System.out.println("\nPartida finalizada, ganador Player one ");
						outClient_1.writeBytes("Round #"+round+" --> "+resClient_1.toUpperCase()+" --> Partida finalizada Perdiste!\n");
						outClient_2.writeBytes("Round #"+round+" --> "+resClient_2.toUpperCase()+" --> Partida finalizada Ganaste!\n");
					}
					
					client_1.close();
					client_2.close();
					break;
				}
				else {
					// Send responses in uppercase and close sockets
					outClient_1.writeBytes("Round #"+round+" --> "+resClient_1.toUpperCase()+"\n");
					outClient_2.writeBytes("Round #"+round+" --> "+resClient_2.toUpperCase()+"\n");
				}
			//}
		}
	}
}