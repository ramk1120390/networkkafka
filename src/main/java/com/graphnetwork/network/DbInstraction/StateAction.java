package com.graphnetwork.network.DbInstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Entity.Country;
import com.graphnetwork.network.Entity.State;
import com.graphnetwork.network.Operation;
import com.graphnetwork.network.Repo.CountryRepo;
import com.graphnetwork.network.Repo.StateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("StateAction")
public class StateAction implements Operation {
    @Override
    public void crudoperation(KafaMessagedto kafaMessagedto) {
        if (kafaMessagedto.getAction().equals("CreateState")) {
            insertState(kafaMessagedto);
        }
    }

    @Autowired
    StateRepo stateRepo;
    @Autowired
    CountryRepo countryRepo;

    public void insertState(KafaMessagedto message) {
        JsonNode data = message.getData();
        Long stateId = data.get("id").asLong();
        String stateName = data.get("stateName").asText();
        String stateNotes = data.get("notes").asText();
        JsonNode countryData = data.get("country");
        String countryName = countryData.get("countryName").asText();
        stateRepo.CreateState(countryName, stateName, stateNotes);
    }
}
