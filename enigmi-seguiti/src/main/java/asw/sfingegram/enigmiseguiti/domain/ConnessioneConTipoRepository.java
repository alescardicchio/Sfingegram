package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConnessioneConTipoRepository extends CrudRepository<ConnessioneConTipo,Long> {

    public Collection<ConnessioneConTipo> findAll();

    public Collection<ConnessioneConTipo> findByUtente(String utente);

    public Collection<ConnessioneConTipo> findAllByTipo(String tipo);
}