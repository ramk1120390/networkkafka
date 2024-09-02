package com.graphnetwork.network.DbInstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Operation;
import com.graphnetwork.network.Repo.CityRepo;
import com.graphnetwork.network.Repo.StateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CityAction")
public class CityAction implements Operation {
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private StateRepo stateRepo;

    @Override
    public void crudoperation(KafaMessagedto kafaMessagedto) {
        String action = kafaMessagedto.getAction();
        switch (action) {
            case "CreateCity":
                insertCity(kafaMessagedto);
                break;
            case "UpdateCity":
                updateCity(kafaMessagedto);
                break;
            case "DeleteCity":
                DeleteCity(kafaMessagedto);
                break;
            default:
                throw new RuntimeException("Invalid Operation: " + action);
        }
    }

    private void insertCity(KafaMessagedto message) {
        JsonNode data = message.getData();
        JsonNode stateNode = data.path("state");
        String stateName = stateNode.path("stateName").asText();
        String cityName = data.path("cityName").asText();
        String desc = data.path("notes").asText();
        cityRepo.createCity(stateName, cityName, desc);
    }

    private void updateCity(KafaMessagedto message) {
        JsonNode data = message.getData();

        // Extract the "new" and "old" nodes
        JsonNode newNode = data.path("new");
        JsonNode oldNode = data.path("old");

        // Extract the state and city details from the "new" node
        JsonNode newStateNode = newNode.path("state");
        String newStateName = newStateNode.path("stateName").asText();
        String newCityName = newNode.path("cityName").asText();
        String newDesc = newNode.path("notes").asText();

        // Extract the state and city details from the "old" node if needed
        JsonNode oldStateNode = oldNode.path("state");
        String oldStateName = oldStateNode.path("stateName").asText();
        String oldCityName = oldNode.path("cityName").asText();
        String oldDesc = oldNode.path("notes").asText();

        // Update the city using the extracted details
        cityRepo.updateCityNameAndLinkState(oldCityName, newCityName, newDesc, newStateName);
    }


    private void DeleteCity(KafaMessagedto message) {
        JsonNode data = message.getData();
        String cityName = data.path("cityName").asText();
        cityRepo.deleteCity(cityName);
    }


}
