package com.graphnetwork.network.DbInstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Entity.Country;
import com.graphnetwork.network.Operation;
import com.graphnetwork.network.Repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("CountryAction")
public class CountryAction implements Operation {

    @Autowired
    CountryRepo countryRepo;

    @Override
    public void crudoperation(KafaMessagedto kafaMessagedto) {
        switch (kafaMessagedto.getAction()) {
            case "CreateCountry":
                insertCountry(kafaMessagedto);
                break;
            case "UpdateCountry":
                updateCountry(kafaMessagedto);
                break;
            case "deleteCountry":
                deleteCountry(kafaMessagedto);
                break;
            default:
                throw new RuntimeException("Invalid Operation");
        }
    }

    public void insertCountry(KafaMessagedto message) {
        JsonNode data = message.getData();
        String countryName = data.get("countryName").asText();
        String notes = data.get("notes").asText();
        Country country = new Country();
        country.setName(countryName);
        country.setDesc(notes);
        countryRepo.save(country);
    }

    public void deleteCountry(KafaMessagedto message) {
        JsonNode data = message.getData();
        String countryName = data.get("countryName").asText();
        countryRepo.deleteByName(countryName);
    }

    public void updateCountry(KafaMessagedto message) {
        JsonNode data = message.getData();
        String countryName = data.get("countryName").asText();
        String notes = data.get("notes").asText();
        Optional<Country> optionalCountry = Optional.ofNullable(countryRepo.findByName(countryName));
        if (optionalCountry.isPresent()) {
            Country existingCountry = optionalCountry.get();
            existingCountry.setName(countryName);
            existingCountry.setDesc(notes);
            countryRepo.save(existingCountry);
        } else {
            throw new RuntimeException("Country not found");
        }
    }
}
