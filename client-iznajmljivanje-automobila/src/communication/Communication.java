/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Automobil;
import domain.Klijent;
import domain.Korisnik;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import ui.coordinator.ClientCoordinator;
import ui.forms.util.Constants;

/**
 *
 * @author Filip
 */
public class Communication {
    
    private static Communication instance;
    Socket socket;
    Sender sender;
    Receiver receiver;

    private Communication() throws IOException {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws IOException {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Korisnik login(String username, String password) throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(username);
        korisnik.setSifra(password);
        Request request = new Request(Operation.LOGIN, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Korisnik) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void logout() throws Exception {
        Request request = new Request(Operation.LOGOUT, ClientCoordinator.getInstance().getParam(Constants.KORISNIK));
        sender.send(request);
    }
    
    public void kreirajKlijenta(Klijent klijent) throws Exception {
        Request request = new Request(Operation.KREIRAJ_KLIJENTA, klijent);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
    public List<Klijent> nadjiKlijente(Klijent klijentNadji) throws Exception {
        Request request = new Request(Operation.NADJI_KLIJENTE, klijentNadji);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Klijent>) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public Klijent nadjiKlijenta(Klijent klijentUcitaj) throws Exception {
        Request request = new Request(Operation.UCITAJ_KLIJENTA, klijentUcitaj);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Klijent) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public void izmeniKlijenta(Klijent klijent) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_KLIJENTA, klijent);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
    public void obrisiKlijenta(Klijent klijent) throws Exception {
        Request request = new Request(Operation.OBRISI_KLIJENTA, klijent);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
    public void kreirajAutomobil(Automobil automobil) throws Exception {
        Request request = new Request(Operation.KREIRAJ_AUTOMOBIL, automobil);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException() != null) {
            throw response.getException();
        }
    }
    
    public List<Automobil> nadjiAutomobile(Automobil automobil) throws Exception {
        Request request = new Request(Operation.NADJI_AUTOMOBILE, automobil);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException() == null) {
            return (List<Automobil>) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public Automobil nadjiAutomobil(Automobil automobil) throws Exception {
        Request request = new Request(Operation.UCITAJ_AUTOMOBIL, automobil);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Automobil) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public void izmeniAutomobil(Automobil automobil) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_AUTOMOBIL, automobil);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
    public void obrisiAutomobil(Automobil automobil) throws Exception {
        Request request = new Request(Operation.OBRISI_AUTOMOBIL, automobil);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
    public List<Automobil> ucitajListuAutomobila() throws Exception {
        Request request = new Request(Operation.UCITAJ_LISTU_AUTOMOBILA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Automobil>) response.getResult();
        } else {
            throw response.getException();
        }
    }
}
