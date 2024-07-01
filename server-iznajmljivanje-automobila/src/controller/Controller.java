/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.AbstractDomainObject;
import domain.Klijent;
import domain.Korisnik;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractSO;
import operation.klijent.KreirajKlijenta;
import operation.klijent.NadjiKlijenta;
import operation.klijent.NadjiKlijente;
import operation.klijent.ObrisiKlijenta;
import operation.klijent.ZapamtiKlijenta;
import operation.korisnik.LoginKorisnik;
import server.Server;

/**
 *
 * @author Filip
 */
public class Controller {
    
    private static Controller instance;
    private Server server;
    public List<AbstractDomainObject> prijavljeniKorisnici;

    private Controller() {
        prijavljeniKorisnici = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public void pokreniServer() {
        if (server == null || !server.isAlive()) {
            server = new Server();
            server.start();
            System.out.println("Server je pokrenut");
        }
    }

    public void zaustaviServer() {
        server.zaustaviServer();
        System.out.println("Server je zaustavljen");
    }
    
    public List<AbstractDomainObject> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public void setPrijavljeniKorisnici(List<AbstractDomainObject> prijavljeniKorisnici) {
        this.prijavljeniKorisnici = prijavljeniKorisnici;
    }
    
    public AbstractDomainObject login(String korisnickoIme, String sifra) throws Exception {

        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(korisnickoIme);
        korisnik.setSifra(sifra);

        AbstractSO operation = new LoginKorisnik();
        operation.execute(korisnik);
        return ((LoginKorisnik) operation).getAbstractDomainObject();
    }
    
    public void kreirajKlijenta(Klijent klijent) throws Exception {
        AbstractSO operation = new KreirajKlijenta();
        operation.execute(klijent);
    }
    
    public List<Klijent> nadjiKlijente(Klijent klijentNadji) throws Exception {
        AbstractSO operation = new NadjiKlijente();
        operation.execute(klijentNadji);
        List<Klijent> klijenti = ((NadjiKlijente) operation).getKandidati();
        return klijenti;
    }
    
    public Klijent ucitajKlijenta(Klijent klijentUcitaj) throws Exception {
        AbstractSO operation = new NadjiKlijenta();
        operation.execute(klijentUcitaj);
        Klijent k = ((NadjiKlijenta) operation).getKlijent();
        return k;
    }
    
    public void obrisiKlijenta(Klijent klijent) throws Exception {
        AbstractSO operation = new ObrisiKlijenta();
        operation.execute(klijent);
    }
    
    public void izmeniKlijenta(Klijent klijent) throws Exception {
        AbstractSO operation = new ZapamtiKlijenta();
        operation.execute(klijent);
    }
}
