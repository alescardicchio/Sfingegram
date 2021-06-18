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
    private EnigmaService enigmaService;

    @Autowired
    private ConnessioneConAutoreService connessioneConAutoreService;

    @Autowired
    private ConnessioneConTipoService connessioneConTipoService;

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
        ConnessioneConTipo c = this.connessioneConTipoService.createConnessioneConTipo(
                e.getTipo(),
                e.getUtente()
        );
        this.enigmiSeguitiService.updateEnigmiSeguiti(c);
    }

    private void createEnigma(EnigmaCreatedEvent e) {
        Enigma enigma = this.enigmaService.createEnigma(
                e.getAutore(),
                e.getTipo(),
                e.getTitolo(),
                e.getTesto()
        );
        this.enigmiSeguitiService.updateEnigmiSeguiti(enigma);
    }

    private void createConnessioneConAutore(ConnessioneConAutoreCreatedEvent e) {
        ConnessioneConAutore c = this.connessioneConAutoreService.createConnessioneConAutore(
                e.getAutore(),
                e.getUtente()
        );
        this.enigmiSeguitiService.updateEnigmiSeguiti(c);
    }
}