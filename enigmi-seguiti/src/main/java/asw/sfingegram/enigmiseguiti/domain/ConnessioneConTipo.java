package asw.sfingegram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
public class ConnessioneConTipo {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id; 
	private String utente; 
	private String tipo; 
	
}
