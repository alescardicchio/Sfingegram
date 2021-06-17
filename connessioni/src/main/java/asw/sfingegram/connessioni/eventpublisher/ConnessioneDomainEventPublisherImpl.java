package asw.sfingegram.connessioni.eventpublisher;

import asw.sfingegram.common.api.event.DomainEvent;
import asw.sfingegram.connessioni.domain.ConnessioneDomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConnessioneDomainEventPublisherImpl implements ConnessioneDomainEventPublisher {

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Value("${asw.kafka.channel.connessioni.out}")
    private String channel;

    @Override
    public void publish(DomainEvent domainEvent) {
        kafkaTemplate.send(channel, domainEvent);
    }
}
