package asw.sfingegram.enigmiseguiti.domain;

import asw.sfingegram.common.api.event.DomainEvent;
import asw.sfingegram.common.api.event.ConnessioneConAutoreCreatedEvent;
import asw.sfingegram.common.api.event.ConnessioneConTipoCreatedEvent;
import asw.sfingegram.common.api.event.EnigmaCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnigmiSeguitiEventHandler {

    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;


    public void onEvent(DomainEvent event) {
        if (event.getClass().equals(EnigmaCreatedEvent.class)) {
            EnigmaCreatedEvent e = (EnigmaCreatedEvent) event;
            this.createEnigma(e);
        }
        else if (event.getClass().equals(ConnessioneConAutoreCreatedEvent.class)) {
            ConnessioneConAutoreCreatedEvent e = (ConnessioneConAutoreCreatedEvent) event;
            this.createConnessioneConAutore(e);
        }
        else if (event.getClass().equals(ConnessioneConTipoCreatedEvent.class)) {
            ConnessioneConTipoCreatedEvent e = (ConnessioneConTipoCreatedEvent) event;
            this.createConnessioneConTipo(e);
        }
        else {
            throw new RuntimeException("Event not found");
        }
    }

    private void createConnessioneConTipo(ConnessioneConTipoCreatedEvent e) {
        this.enigmiSeguitiService.createConnessioneConTipo(
                e.getTipo(),
                e.getUtente()
        );
    }

    private void createEnigma(EnigmaCreatedEvent e) {
        this.enigmiSeguitiService.createEnigma(
                e.getAutore(),
                e.getTipo(),
                e.getTitolo(),
                e.getTesto()
        );
    }

    private void createConnessioneConAutore(ConnessioneConAutoreCreatedEvent e) {
        this.enigmiSeguitiService.createConnessioneConAutore(
                e.getAutore(),
                e.getUtente()
        );
    }
}