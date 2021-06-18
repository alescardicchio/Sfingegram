package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

@Service 
public class EnigmiSeguitiService {

	@Autowired
	private ConnessioneConTipoService connessioneConTipoService;

	@Autowired
	private ConnessioneConAutoreService connessioneConAutoreService;

	@Autowired
	private EnigmaService enigmaService;

	@Autowired
	private EnigmaRepository enigmaRepository;

	@Autowired
	private ConnessioneConAutoreRepository connessioneConAutoreRepository;

	@Autowired
	private ConnessioneConTipoRepository connessioneConTipoRepository;

	@Autowired
	private EnigmiSeguitiRepository enigmiSeguitiRepository;


	public void updateEnigmiSeguiti(Enigma enigma) {
		EnigmiSeguiti es;
		for(ConnessioneConTipo c : connessioneConTipoService.getConnessioniConTipoByTipo(enigma.getTipo())) {
			es = new EnigmiSeguiti();
			es.setUtente(c.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTestoEnigma(enigma.getTesto());

			enigmiSeguitiRepository.save(es);
		}

		for(ConnessioneConAutore c : connessioneConAutoreService.getConnessioniConAutoriByAutore(enigma.getAutore())) {
			es = new EnigmiSeguiti();
			es.setUtente(c.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTestoEnigma(enigma.getTesto());

			enigmiSeguitiRepository.save(es);
		}
	}

	public void updateEnigmiSeguiti(ConnessioneConTipo connessione) {
		EnigmiSeguiti es;
		for(Enigma enigma : enigmaService.getEnigmiByTipo(connessione.getTipo())) {
			es = new EnigmiSeguiti();
			es.setUtente(connessione.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTestoEnigma(enigma.getTesto());

			enigmiSeguitiRepository.save(es);
		}

	}

	public void updateEnigmiSeguiti(ConnessioneConAutore connessione) {
		EnigmiSeguiti es;
		for(Enigma enigma : enigmaService.getEnigmiByAutore(connessione.getAutore())) {
			es = new EnigmiSeguiti();
			es.setUtente(connessione.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTestoEnigma(enigma.getTesto());

			enigmiSeguitiRepository.save(es);
		}
	}

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<EnigmiSeguiti> getEnigmiSeguiti(String utente) {
		return this.enigmiSeguitiRepository.findByUtente(utente);
	}

	private Collection<Enigma> getEnigmiDiAutoriSeguiti(String utente) {
		Collection<Enigma> enigmi = new TreeSet<>(); 
		Collection<ConnessioneConAutore> connessioniConAutori = connessioneConAutoreService.getConnessioniConAutoriByUtente(utente);
		Collection<String> autoriSeguiti = 
			connessioniConAutori
				.stream()
				.map(c -> c.getAutore())
				.collect(Collectors.toSet()); 
		if (autoriSeguiti.size()>0) {
			Collection<Enigma> enigmiDiAutoriSeguiti = enigmaService.getEnigmiByAutori(autoriSeguiti);
			enigmi.addAll(enigmiDiAutoriSeguiti); 
		}
		return enigmi; 
	}

	private Collection<Enigma> getEnigmiDiTipiSeguiti(String utente) {
		Collection<Enigma> enigmi = new TreeSet<>(); 
		Collection<ConnessioneConTipo> connessioniConTipi = connessioneConTipoService.getConnessioniConTipoByUtente(utente);
		Collection<String> tipiSeguiti = 
			connessioniConTipi
				.stream()
				.map(c -> c.getTipo())
				.collect(Collectors.toSet()); 
		if (tipiSeguiti.size()>0) {
			Collection<Enigma> enigmiDiTipiSeguiti = enigmaService.getEnigmiByTipi(tipiSeguiti);
			enigmi.addAll(enigmiDiTipiSeguiti); 
		}
		return enigmi; 
	}

}
