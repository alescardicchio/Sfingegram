package asw.sfingegram.enigmiseguiti.eventlistner;

import asw.sfingegram.common.api.event.DomainEvent;
import asw.sfingegram.enigmiseguiti.domain.EnigmiSeguitiEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnigmaEventListner {

    @Autowired
    private EnigmiSeguitiEventHandler enigmiSeguitiEventHandler;


    @KafkaListener(topics="${asw.kafka.channel.enigmi.in}")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        DomainEvent event = record.value();
        enigmiSeguitiEventHandler.onEvent(event);
    }

}
