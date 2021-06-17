package asw.sfingegram.connessioni.domain;

import asw.sfingegram.common.api.event.DomainEvent;

/* La definizione di un’interfaccia per l’adapter eventpublisher e la sua implementazione */

public interface ConnessioneDomainEventPublisher {

    public void publish(DomainEvent domainEvent);
}
