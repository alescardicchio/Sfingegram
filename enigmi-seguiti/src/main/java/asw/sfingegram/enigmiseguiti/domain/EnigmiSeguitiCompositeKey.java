package asw.sfingegram.enigmiseguiti.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnigmiSeguitiCompositeKey implements Serializable {

    private Long idEnigma;

    private String utente;
}
