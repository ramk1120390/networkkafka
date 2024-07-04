package com.graphnetwork.network.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.Country;
import com.graphnetwork.network.Dto1.KafaMessagedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    @Autowired
    private CountryRepo countryRepo; // Assuming CountryRepo is a Spring Data Neo4j repository

    @KafkaListener(topics = "inventory1", groupId = "test-group")
    public void listen(KafaMessagedto message) {
        System.out.println("Received Message:");
        System.out.println("Action: " + message.getAction());
        System.out.println("Data: " + message.getData());

        // Process the message
        if ("test".equals(message.getAction())) {
            JsonNode data = message.getData();
            Long id = data.get("id").asLong();
            String countryName = data.get("countryName").asText();
            String notes = data.get("notes").asText();

            // Save to Neo4j database
            Country country = new Country(id, countryName, notes);
            countryRepo.save(country);

            System.out.println("Saved to Neo4j: " + country);
        }
    }
}
