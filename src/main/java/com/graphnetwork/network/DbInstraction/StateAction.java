package com.graphnetwork.network.DbInstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Entity.Country;
import com.graphnetwork.network.Operation;
import com.graphnetwork.network.Repo.CountryRepo;
import com.graphnetwork.network.Repo.StateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("StateAction")
public class StateAction implements Operation {

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Override
    public void crudoperation(KafaMessagedto kafaMessagedto) {
        String action = kafaMessagedto.getAction();
        switch (action) {
            case "CreateState":
                insertState(kafaMessagedto);
                break;
            case "UpdateState":
                updateState(kafaMessagedto);
                break;
            case "deleteState":
                DeleteState(kafaMessagedto);
                break;

            default:
                throw new RuntimeException("Invalid Operation: " + action);
        }
    }

    private void insertState(KafaMessagedto message) {
        JsonNode data = message.getData();
        Long stateId = data.get("id").asLong();
        String stateName = data.get("stateName").asText();
        String stateNotes = data.get("notes").asText();
        JsonNode countryData = data.get("country");
        String countryName = countryData.get("countryName").asText();
        stateRepo.CreateState(countryName, stateName, stateNotes);
    }

    private void updateState(KafaMessagedto message) {
        JsonNode data = message.getData();
        // Extract old state and country details
        JsonNode oldStateNode = data.path("old");
        JsonNode oldCountryNode = oldStateNode.path("country");
        String oldStateName = oldStateNode.path("stateName").asText();
        String oldCountryName = oldCountryNode.path("countryName").asText();
        System.out.println(oldStateNode);
        // Extract new state and country details
        JsonNode newStateNode = data.path("new");
        JsonNode newCountryNode = newStateNode.path("country");
        String newStateName = newStateNode.path("stateName").asText();
        String newCountryName = newCountryNode.path("countryName").asText();
        String newStateNotes = newStateNode.path("notes").asText();
        System.out.println(newStateNode);
        stateRepo.updateStateNameAndLinkCountry(oldStateName, newStateName, newStateNotes, newCountryName);
    }


    private void DeleteState(KafaMessagedto message) {
        JsonNode data = message.getData();
        String stateName = data.get("stateName").asText();
        stateRepo.deleteState(stateName);
    }


}
