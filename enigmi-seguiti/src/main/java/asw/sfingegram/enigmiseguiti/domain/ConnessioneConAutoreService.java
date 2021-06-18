package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConnessioneConAutoreService {

    @Autowired
    private ConnessioneConAutoreRepository connessioneConAutoreRepository;

    public Collection<ConnessioneConAutore> getConnessioniConAutoriByUtente(String utente) {
        return this.connessioneConAutoreRepository.findByUtente(utente);
    }

    public Collection<ConnessioneConAutore> getConnessioniConAutoriByAutore(String autore) {
        return this.connessioneConAutoreRepository.findAllByAutore(autore);
    }

    /* Memorizza una connessione con autore nel database del servizio EnigmiSeguiti (tabella Enigmi) */
    public ConnessioneConAutore createConnessioneConAutore(String autore, String utente) {
        ConnessioneConAutore connessioneConAutore = new ConnessioneConAutore();
        connessioneConAutore.setAutore(autore);
        connessioneConAutore.setUtente(utente);
        System.out.println("ConnessioneConAutore creata nel database Enigmi-Seguiti!");
        return this.connessioneConAutoreRepository.save(connessioneConAutore);
    }

}
