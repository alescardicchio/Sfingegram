package asw.sfingegram.common.api.event;

import asw.sfingegram.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnessioneConTipoCreatedEvent implements DomainEvent {

    private Long id;
    private String utente;
    private String tipo;
}
