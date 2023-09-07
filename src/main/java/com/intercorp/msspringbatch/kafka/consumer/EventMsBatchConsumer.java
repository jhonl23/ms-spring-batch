package com.intercorp.msspringbatch.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.msspringbatch.kafka.producer.EventMsTwoProducer;
import com.intercorp.msspringbatch.models.Root;
import com.intercorp.msspringbatch.services.IRootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventMsBatchConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventMsTwoProducer eventMsTwoProducer;

    @Autowired
    private IRootService rootService;

    /**
     * Consumer with MS-ONE
     * @param message
     * @throws Exception
     */
    @KafkaListener(topics = "${spring.kafka.topic3}")
    public void listenForEventMsOne(String message) throws Exception {

        try {
            /** STEP (5) **/
            Root rootUpdated = objectMapper.readValue(message, Root.class);

            System.out.println("MENSAJE RECIBIDO EN MS-BATCH FROM MS-ONE:");
            System.out.println(rootUpdated.toString());

            /**
             * STEP (6)
             * Registrar los datos en BD (From MS-ONE)
             */
            System.out.println("SAVE DATA UPDATED IN DATABASE..");
            Root rootUpdatedDB = this.rootService.save(rootUpdated);

            /**
             * STEP (7)
             * Enviar los datos al MS-TWO
             */
            System.out.println("DATOS GUARDADOS...");
            System.out.println("SEND MESSAGE MS-BATCH TO MS-TWO...");
            this.sendMessageEventTwoConsumer(rootUpdatedDB);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Consumer With MS-TWO
     * @param message
     * @throws Exception
     */
    @KafkaListener(topics = "${spring.kafka.topic4}")
    public void listenForEventMsTwo(String message) throws Exception {

        try {

            /** STEP (10) **/
            Root rootModified = objectMapper.readValue(message, Root.class);

            /**
             * STEP (11) - FIN FLUJO
             * Registrar los datos en BD (From MS-TWO)
             */
            this.rootService.save(rootModified);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageEventTwoConsumer(Root root) {
        try {
            eventMsTwoProducer.sendMessage(root);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
