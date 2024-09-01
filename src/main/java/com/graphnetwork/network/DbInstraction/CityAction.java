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
        JsonNode stateNode = data.path("state");
        String stateName = stateNode.path("stateName").asText();
        String cityName = data.path("cityName").asText();
        String desc = data.path("notes").asText();
        cityRepo.updateCity(stateName, cityName, desc);
    }

    private void DeleteCity(KafaMessagedto message) {
        JsonNode data = message.getData();
        String cityName = data.path("cityName").asText();
        cityRepo.deleteCity(cityName);
    }
}
