/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import domain.Korisnik;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.thread.ClientThread;

/**
 *
 * @author Filip
 */
public class Server extends Thread {
    
    private List<ClientThread> klijenti;
    private ServerSocket ss;
    private boolean kraj = false;

    public Server() {
        klijenti = new ArrayList<>();
        try {
            ss = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                System.out.println("Server ceka konekciju");
                Socket socket = ss.accept();
                ClientThread klijent = new ClientThread(socket, this);
                System.out.println("Klijent se povezao");
                klijent.start();
                klijenti.add(klijent);
            } catch (IOException ex) {
                System.out.println("Server je zaustavljen");
            }
        }
    }

    public List<ClientThread> getKlijenti() {
        return klijenti;
    }

    public void zaustaviServer() {
        try {
            for (ClientThread clientThread : klijenti) {
                clientThread.krajRada();
            }
            Controller.getInstance().setPrijavljeniKorisnici(new ArrayList<>());
            ss.close();
            kraj = true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
