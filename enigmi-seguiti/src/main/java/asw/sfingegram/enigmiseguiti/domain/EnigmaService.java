package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnigmaService {

	@Autowired
	private EnigmaRepository enigmaRepository;

	public Collection<Enigma> getEnigmiByAutori(Collection<String> autori) {
		return this.enigmaRepository.findByAutoreIn(autori);
	}

	public Collection<Enigma> getEnigmiByTipi(Collection<String> tipi) {
		return this.enigmaRepository.findByTipoIn(tipi);
	}

	public Collection<Enigma> getEnigmiByTipo(String tipo) {
		return this.enigmaRepository.findByTipoStartingWith(tipo);
	}

	public Collection<Enigma> getEnigmiByAutore(String autore) {
		return this.enigmaRepository.findByAutore(autore);
	}

	/* Memorizza un enigma (in formato breve) nel database del servizio EnigmiSeguiti (tabella Enigmi) */
	public Enigma createEnigma(String autore, String tipo, String titolo, String[] testo) {
		Enigma enigma = new Enigma();
		enigma.setAutore(autore);
		enigma.setTipo(tipo);
		enigma.setTitolo(titolo);
		enigma.setTesto(testo);
		return this.enigmaRepository.save(enigma);
	}

}
