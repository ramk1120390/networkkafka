package com.graphnetwork.network.DbInstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Entity.Country;
import com.graphnetwork.network.Operation;
import com.graphnetwork.network.Repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("CountryAction")
public class CountryAction implements Operation {
    @Autowired
    CountryRepo countryRepo;


    @Override
    public void crudoperation(KafaMessagedto kafaMessagedto) {
        if (kafaMessagedto.getAction().equals("CreateCountry")) {
            insertCountry(kafaMessagedto);
        }
    }

    public void insertCountry(KafaMessagedto message) {
        JsonNode data = message.getData();
        Long id = data.get("id").asLong();
        String countryName = data.get("countryName").asText();
        String notes = data.get("notes").asText();
        Country country = new Country();
        country.setName(countryName);
        country.setDesc(notes);
        countryRepo.save(country);
    }

    public void DeleteCountry(KafaMessagedto message) {
        JsonNode data = message.getData();
        String countryName = data.get("countryName").asText();
       // countryRepo.deleteByName(countryName);
    }

   /* public void updateCountry(KafaMessagedto message) {
        JsonNode data = message.getData();
        String countryName = data.get("countryName").asText();
        String notes = data.get("notes").asText();
        Optional<Country> excountry = countryRepo.findByName(countryName);
        excountry.setName(countryName);
        excountry.setDesc(notes);
        countryRepo.save(excountry);
    }

    */
}
