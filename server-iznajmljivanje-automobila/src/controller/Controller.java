package controller;

import domain.AbstractDomainObject;
import domain.Automobil;
import domain.Klijent;
import domain.Korisnik;
import domain.TerminVoznje;
import domain.TipAutomobila;
import domain.Uverenje;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractSO;
import operation.automobil.KreirajAutomobil;
import operation.automobil.NadjiAutomobil;
import operation.automobil.NadjiAutomobile;
import operation.automobil.ObrisiAutomobil;
import operation.automobil.UcitajListuAutomobila;
import operation.automobil.ZapamtiAutomobil;
import operation.klijent.KreirajKlijenta;
import operation.klijent.NadjiKlijenta;
import operation.klijent.NadjiKlijente;
import operation.klijent.ObrisiKlijenta;
import operation.klijent.UcitajListuKlijenata;
import operation.klijent.ZapamtiKlijenta;
import operation.korisnik.LoginKorisnik;
import operation.terminvoznje.KreirajTerminVoznje;
import operation.terminvoznje.NadjiTermineVoznje;
import operation.terminvoznje.ObrisiTerminVoznje;
import operation.terminvoznje.ZapamtiTerminVoznje;
import operation.tipautomobila.UcitajListuTipovaAutomobila;
import operation.uverenje.KreirajUverenje;
import operation.uverenje.NadjiUverenja;
import operation.uverenje.NadjiUverenje;
import operation.uverenje.ZapamtiUverenje;
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
    
    public void kreirajAutomobil(Automobil automobil) throws Exception {
        AbstractSO operation = new KreirajAutomobil();
        operation.execute(automobil);
    }
    
    public List<Automobil> nadjiAutomobile(Automobil automobilNadji) throws Exception {
        AbstractSO operation = new NadjiAutomobile();
        operation.execute(automobilNadji);
        List<Automobil> autmobili = ((NadjiAutomobile) operation).getAutomobili();
        return autmobili;
    }
    
    public Automobil ucitajAutomobil(Automobil automobilUcitaj) throws Exception {
        AbstractSO operation = new NadjiAutomobil();
        operation.execute(automobilUcitaj);
        Automobil k = ((NadjiAutomobil) operation).getAutomobil();
        return k;
    }
    
    public void obrisiAutomobil(Automobil automobil) throws Exception {
        AbstractSO operation = new ObrisiAutomobil();
        operation.execute(automobil);
    }
    
    public void izmeniAutomobil(Automobil automobil) throws Exception {
        AbstractSO operation = new ZapamtiAutomobil();
        operation.execute(automobil);
    }
    
    public List<TipAutomobila> ucitajListuTipovaAutomobila() throws Exception {
        AbstractSO operation = new UcitajListuTipovaAutomobila();
        operation.execute(new TipAutomobila());
        List<TipAutomobila> tipoviAutomobila = ((UcitajListuTipovaAutomobila) operation).getTipoviAutomobila();
        return tipoviAutomobila;
    }
    
    public List<Klijent> ucitajListuKlijenata() throws Exception {
        AbstractSO operation = new UcitajListuKlijenata();
        operation.execute(new Klijent());
        List<Klijent> klijenti = ((UcitajListuKlijenata) operation).getKlijenti();
        return klijenti;
    }
        
    public List<Automobil> ucitajListuAutomobila() throws Exception {
        AbstractSO operation = new UcitajListuAutomobila();
        operation.execute(new Automobil());
        List<Automobil> automobili = ((UcitajListuAutomobila) operation).getAutomobili();
        return automobili;
    }
    
    public Uverenje kreirajUverenje(Uverenje uverenje) throws Exception {
        AbstractSO operation = new KreirajUverenje();
        operation.execute(uverenje);
        return ((KreirajUverenje) operation).getUverenje();
    }
    
    public List<Uverenje> nadjiUverenja(Uverenje uverenjeNadji) throws Exception {
        AbstractSO operation = new NadjiUverenja();
        operation.execute(uverenjeNadji);
        List<Uverenje> uverenja = ((NadjiUverenja) operation).getUverenja();
        return uverenja;
    }
    
    public Uverenje ucitajUverenje(Uverenje uverenjeUcitaj) throws Exception {
        AbstractSO operation = new NadjiUverenje();
        operation.execute(uverenjeUcitaj);
        Uverenje uverenje = ((NadjiUverenje) operation).getUverenje();
        return uverenje;
    }
    
    public void izmeniUverenje(Uverenje uverenje) throws Exception {
        AbstractSO operation = new ZapamtiUverenje();
        operation.execute(uverenje);
    }
    
    public void kreirajTerminVoznje(TerminVoznje tv) throws Exception {
        AbstractSO operation = new KreirajTerminVoznje();
        operation.execute(tv);
    }
    
    public List<TerminVoznje> nadjiTerminVoznje(TerminVoznje terminNadji) throws Exception {
        AbstractSO operation = new NadjiTermineVoznje();
        operation.execute(terminNadji);
        List<TerminVoznje> termini = ((NadjiTermineVoznje) operation).getTermineVoznje();
        return termini;
    }
    
    public void obrisiTerminVoznje(TerminVoznje terminObrisi) throws Exception {
        AbstractSO operation = new ObrisiTerminVoznje();
        operation.execute(terminObrisi);
    }

    public void izmeniTerminVoznje(TerminVoznje terminIzmeni) throws Exception {
        AbstractSO operation = new ZapamtiTerminVoznje();
        operation.execute(terminIzmeni);
    }
}
