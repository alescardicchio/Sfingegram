package asw.sfingegram.enigmi.eventpublisher;

import asw.sfingegram.common.api.event.DomainEvent;
import asw.sfingegram.enigmi.domain.EnigmaDomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnigmaDomainEventPublisherImpl implements EnigmaDomainEventPublisher {

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Value("${asw.kafka.channel.enigmi.out}")
    private String channel;

    @Override
    public void publish(DomainEvent domainEvent) {
        kafkaTemplate.send(channel, domainEvent);
    }
}
