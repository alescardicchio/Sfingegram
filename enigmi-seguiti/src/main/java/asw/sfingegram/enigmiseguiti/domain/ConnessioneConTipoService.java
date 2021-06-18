package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConnessioneConTipoService {

	@Autowired
	private ConnessioneConTipoRepository connessioneConTipoRepository;

	public Collection<ConnessioneConTipo> getConnessioniConTipoByUtente(String utente) {
		return this.connessioneConTipoRepository.findByUtente(utente);
	}

	public Collection<ConnessioneConTipo> getConnessioniConTipoByTipo(String tipo) {
		return this.connessioneConTipoRepository.findAllByTipo(tipo);
	}

	/* Memorizza una connessione con tipo nel database del servizio EnigmiSeguiti */
	public ConnessioneConTipo createConnessioneConTipo(String tipo, String utente) {
		ConnessioneConTipo connessioneConTipo = new ConnessioneConTipo();
		connessioneConTipo.setTipo(tipo);
		connessioneConTipo.setUtente(utente);
		System.out.println("ConnessioneConTipo creata nel database Enigmi-Seguiti!");
		return this.connessioneConTipoRepository.save(connessioneConTipo);
	}
}
