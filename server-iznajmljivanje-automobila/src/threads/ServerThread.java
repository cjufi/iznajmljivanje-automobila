/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Filip
 */
public class ServerThread extends Thread {
    
    private ServerSocket serverSocket;

    public ServerThread() throws IOException {
        serverSocket = new ServerSocket(9000);
        System.out.println("Server pokrenut");
    }

    @Override
    public void run() {
        
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
