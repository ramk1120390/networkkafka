package com.graphnetwork.network.DbInstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Entity.City;
import com.graphnetwork.network.Entity.Country;
import com.graphnetwork.network.Entity.State;
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
        if (kafaMessagedto.getAction().equals("CreateCity")) {
            insertCity(kafaMessagedto);
        }
    }

    private void insertCity(KafaMessagedto message) {
        JsonNode data = message.getData();
        JsonNode stateNode = data.path("state");
        String StateName = stateNode.path("stateName").asText();
        String cityName = data.path("cityName").asText();
        String Desc = data.path("notes").asText();
        cityRepo.createCity(StateName, cityName, Desc);
    }


}
