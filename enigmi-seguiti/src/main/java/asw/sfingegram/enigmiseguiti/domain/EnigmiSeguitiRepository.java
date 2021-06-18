package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EnigmiSeguitiRepository extends CrudRepository<EnigmiSeguiti, Long> {

    public Collection<EnigmiSeguiti> findByUtente(String utente);
}
