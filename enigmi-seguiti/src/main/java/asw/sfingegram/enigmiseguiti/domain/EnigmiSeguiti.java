package asw.sfingegram.enigmiseguiti.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/* Enigma seguito (in formato breve, senza soluzione). */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@IdClass(EnigmiSeguitiCompositeKey.class)
public class EnigmiSeguiti {

    @Id
    @EqualsAndHashCode.Include
    private Long idEnigma;

    @Id
    @EqualsAndHashCode.Include
    private String utente;

    private String autoreEnigma;

    private String titoloEnigma;

    private String tipoEnigma;

    private String[] testoEnigma;
}
