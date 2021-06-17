package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

@Service 
public class EnigmiSeguitiService {

	@Autowired
	private ConnessioniService connessioniService;

	@Autowired
	private EnigmiService enigmiService;

	@Autowired
	private EnigmaRepository enigmaRepository;

	@Autowired
	private ConnessioneConAutoreRepository connessioneConAutoreRepository;

	@Autowired
	private ConnessioneConTipoRepository connessioneConTipoRepository;

	/* Memorizza un enigma (in formato breve) nel database del servizio EnigmiSeguiti */
	public Enigma createEnigma(String autore, String tipo, String titolo, String[] testo) {
		Enigma enigma = new Enigma();
		enigma.setAutore(autore);
		enigma.setTipo(tipo);
		enigma.setTitolo(titolo);
		enigma.setTesto(testo);
		System.out.println("Enigma creato nel database Enigmi-Seguiti!");
		return this.enigmaRepository.save(enigma);
	}

	/* Memorizza una connessione con tipo nel database del servizio EnigmiSeguiti */
	public ConnessioneConTipo createConnessioneConTipo(String tipo, String utente) {
		ConnessioneConTipo connessioneConTipo = new ConnessioneConTipo();
		connessioneConTipo.setTipo(tipo);
		connessioneConTipo.setUtente(utente);
		System.out.println("ConnessioneConTipo creata nel database Enigmi-Seguiti!");
		return this.connessioneConTipoRepository.save(connessioneConTipo);
	}

	/* Memorizza una connessione con autore nel database del servizio EnigmiSeguiti */
	public ConnessioneConAutore createConnessioneConAutore(String autore, String utente) {
		ConnessioneConAutore connessioneConAutore = new ConnessioneConAutore();
		connessioneConAutore.setAutore(autore);
		connessioneConAutore.setUtente(utente);
		System.out.println("ConnessioneConAutore creata nel database Enigmi-Seguiti!");
		return this.connessioneConAutoreRepository.save(connessioneConAutore);
	}

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<Enigma> getEnigmiSeguiti(String utente) {
		Collection<Enigma> enigmi = new TreeSet<>(); 		
		Collection<Enigma> enigmiDiAutoriSeguiti = getEnigmiDiAutoriSeguiti(utente);
		Collection<Enigma> enigmiDiTipiSeguiti = getEnigmiDiTipiSeguiti(utente); 
		enigmi.addAll(enigmiDiAutoriSeguiti); 
		enigmi.addAll(enigmiDiTipiSeguiti); 
		return enigmi; 
	}

	private Collection<Enigma> getEnigmiDiAutoriSeguiti(String utente) {
		Collection<Enigma> enigmi = new TreeSet<>(); 
		Collection<ConnessioneConAutore> connessioniConAutori = connessioniService.getConnessioniConAutoriByUtente(utente); 
		Collection<String> autoriSeguiti = 
			connessioniConAutori
				.stream()
				.map(c -> c.getAutore())
				.collect(Collectors.toSet()); 
		if (autoriSeguiti.size()>0) {
			Collection<Enigma> enigmiDiAutoriSeguiti = enigmiService.getEnigmiByAutori(autoriSeguiti);
			enigmi.addAll(enigmiDiAutoriSeguiti); 
		}
		return enigmi; 
	}

	private Collection<Enigma> getEnigmiDiTipiSeguiti(String utente) {
		Collection<Enigma> enigmi = new TreeSet<>(); 
		Collection<ConnessioneConTipo> connessioniConTipi = connessioniService.getConnessioniConTipiByUtente(utente); 
		Collection<String> tipiSeguiti = 
			connessioniConTipi
				.stream()
				.map(c -> c.getTipo())
				.collect(Collectors.toSet()); 
		if (tipiSeguiti.size()>0) {
			Collection<Enigma> enigmiDiTipiSeguiti = enigmiService.getEnigmiByTipi(tipiSeguiti);
			enigmi.addAll(enigmiDiTipiSeguiti); 
		}
		return enigmi; 
	}

}
