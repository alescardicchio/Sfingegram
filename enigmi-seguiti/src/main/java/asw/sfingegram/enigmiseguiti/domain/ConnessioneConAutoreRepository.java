package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConnessioneConAutoreRepository extends CrudRepository<ConnessioneConAutore,Long> {

    public Collection<ConnessioneConAutore> findAll();

    public Collection<ConnessioneConAutore> findByUtente(String utente);

    public Collection<ConnessioneConAutore> findAllByAutore(String autore);

}
