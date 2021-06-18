package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface EnigmaRepository extends CrudRepository<Enigma,Long> {

    public Collection<Enigma> findAll();

    public Collection<Enigma> findByAutore(String autore);

    public Collection<Enigma> findByAutoreIn(Collection<String> autori);

    public Collection<Enigma> findByTipoIn(Collection<String> tipi);

    public Collection<Enigma> findByTipoStartingWith(String tipo);

}
