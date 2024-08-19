package server.thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.AbstractDomainObject;
import domain.Automobil;
import domain.Klijent;
import domain.Korisnik;
import domain.Servis;
import domain.StavkaAutomobila;
import domain.TipAutomobila;
import domain.Uverenje;
import java.net.Socket;
import java.util.List;
import server.Server;

/**
 *
 * @author Filip
 */
public class ClientThread extends Thread {
    
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private Server server;
    private boolean kraj = false;

    public ClientThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    @Override
    public void run() {
        while (!kraj) {            
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            Korisnik korisnik = (Korisnik) request.getArgument();
                            AbstractDomainObject ado = Controller.getInstance().login(korisnik.getKorisnickoIme(), korisnik.getSifra());
                            if (ado == null) {
                                Exception e = new Exception("Korisnik nema pravo pristupa sistemu");
                                response.setException(e);
                                response.setUspesno(false);
                            } else {
                                if (!Controller.getInstance().getPrijavljeniKorisnici().contains(ado)) {
                                    Controller.getInstance().getPrijavljeniKorisnici().add(ado);
                                    response.setUspesno(true);
                                    response.setPoruka("Uspešno ste se prijavili na sistem!");
                                    response.setResult(ado);
                                } else {
                                    Exception e = new Exception("Korisnik je već prijavljen");
                                    response.setException(e);
                                    response.setUspesno(false);
                                }
                            }
                            break;
                        case LOGOUT:
                            Korisnik korisnikLogout = (Korisnik) request.getArgument();
                            Controller.getInstance().getPrijavljeniKorisnici().remove(korisnikLogout);
                            kraj = true;
                            break;
                        case KREIRAJ_KLIJENTA:
                            Klijent klijent = (Klijent) request.getArgument();
                            Controller.getInstance().kreirajKlijenta(klijent);
                            response.setUspesno(true);
                            break;
                        case NADJI_KLIJENTE:
                            Klijent klijentNadji = (Klijent) request.getArgument();
                            List<Klijent> klijenti = Controller.getInstance().nadjiKlijente(klijentNadji);
                            response.setResult(klijenti);
                            response.setUspesno(true);
                            break;
                        case UCITAJ_KLIJENTA:
                            Klijent klijentUcitaj = (Klijent) request.getArgument();
                            Klijent klijentNadjen = Controller.getInstance().ucitajKlijenta(klijentUcitaj);
                            response.setResult(klijentNadjen);
                            response.setUspesno(true);
                            break;
                        case OBRISI_KLIJENTA:
                            Klijent klijentObrisi = (Klijent) request.getArgument();
                            Controller.getInstance().obrisiKlijenta(klijentObrisi);
                            response.setUspesno(true);
                            break;
                        case ZAPAMTI_KLIJENTA:
                            Klijent klijentIzmeni = (Klijent) request.getArgument();
                            Controller.getInstance().izmeniKlijenta(klijentIzmeni);
                            response.setUspesno(true);
                            break;
                        case KREIRAJ_AUTOMOBIL:
                            Automobil automobil = (Automobil) request.getArgument();
                            Controller.getInstance().kreirajAutomobil(automobil);
                            response.setUspesno(true);
                            break;
                        case NADJI_AUTOMOBILE:
                            Automobil automobilNadji = (Automobil) request.getArgument();
                            List<Automobil> automobili = Controller.getInstance().nadjiAutomobile(automobilNadji);
                            response.setResult(automobili);
                            response.setUspesno(true);
                            break;
                        case UCITAJ_AUTOMOBIL:
                            Automobil automobilUcitaj = (Automobil) request.getArgument();
                            Automobil automobilNadjen = Controller.getInstance().ucitajAutomobil(automobilUcitaj);
                            response.setResult(automobilNadjen);
                            response.setUspesno(true);
                            break;
                        case OBRISI_AUTOMOBIL:
                            Automobil automobilObrisi = (Automobil) request.getArgument();
                            Controller.getInstance().obrisiAutomobil(automobilObrisi);
                            response.setUspesno(true);
                            break;
                        case ZAPAMTI_AUTOMOBIL:
                            Automobil automobilIzmeni = (Automobil) request.getArgument();
                            Controller.getInstance().izmeniAutomobil(automobilIzmeni);
                            response.setUspesno(true);
                            break;
                        case UCITAJ_LISTU_TIPOVA_AUTOMOBILA:
                            List<TipAutomobila> sviTipovi = Controller.getInstance().ucitajListuTipovaAutomobila();
                            response.setResult(sviTipovi);
                            response.setUspesno(true);
                            break;
                        case UCITAJ_LISTU_KLIJENATA:
                            List<Klijent> sviKlijenti = Controller.getInstance().ucitajListuKlijenata();
                            response.setResult(sviKlijenti);
                            response.setUspesno(true);
                            break;
                        case UCITAJ_LISTU_AUTOMOBILA:
                            List<Automobil> sviAutomobili = Controller.getInstance().ucitajListuAutomobila();
                            response.setResult(sviAutomobili);
                            response.setUspesno(true);
                            break;
                        case KREIRAJ_UVERENJE:
                            Uverenje uverenje = (Uverenje) request.getArgument();
                            Controller.getInstance().kreirajUverenje(uverenje);
                            response.setUspesno(true);
                            break;
                        case NADJI_UVERENJA:
                            Uverenje uverenjeNadji = (Uverenje) request.getArgument();
                            List<Uverenje> uverenja = Controller.getInstance().nadjiUverenja(uverenjeNadji);
                            response.setResult(uverenja);
                            response.setUspesno(true);
                            break;
                        case UCITAJ_UVERENJE:
                            Uverenje uverenjeUcitaj = (Uverenje) request.getArgument();
                            Uverenje uverenjeNadjen = Controller.getInstance().ucitajUverenje(uverenjeUcitaj);
                            response.setResult(uverenjeNadjen);
                            response.setUspesno(true);
                            break;
                        case ZAPAMTI_UVERENJE:
                            Uverenje uverenjeIzmeni = (Uverenje) request.getArgument();
                            Controller.getInstance().izmeniUverenje(uverenjeIzmeni);
                            response.setUspesno(true);
                            break;
                        case UCITAJ_LISTU_SERVISA:
                            List<Servis> servisi = Controller.getInstance().ucitajListuServisa();
                            response.setResult(servisi);
                            response.setUspesno(true);
                            break;
                        case KREIRAJ_STAVKU_AUTOMOBILA:
                            StavkaAutomobila sa = (StavkaAutomobila) request.getArgument();
                            Controller.getInstance().kreirajStavku(sa);
                            response.setUspesno(true);
                            break;
                        default:
                            System.err.println("Operacija "+request.getOperation() +" nije implementirana");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                    response.setUspesno(false);
                }
                sender.send(response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void krajRada() {
        kraj = true;
    }
}
