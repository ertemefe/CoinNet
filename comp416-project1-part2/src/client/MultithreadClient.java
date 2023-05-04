package client;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 * @author AbdulrazakZakieh
 */
public class MultithreadClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConnectionToServer connectionToServer = new ConnectionToServer(ConnectionToServer.DEFAULT_SERVER_ADDRESS, ConnectionToServer.DEFAULT_SERVER_PORT);
        connectionToServer.Connect();
        String message="";
        System.out.println("Enter a message");
        while (!message.equals("QUIT")) {
            Scanner scanner = new Scanner(System.in);
            message = scanner.nextLine();
            System.out.println("Response from server: " + connectionToServer.SendForAnswer(message));
        }
        connectionToServer.Disconnect();
    }

}
