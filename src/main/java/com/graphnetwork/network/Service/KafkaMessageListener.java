package com.graphnetwork.network.Service;


import com.graphnetwork.network.DbInstraction.BuildingAction;
import com.graphnetwork.network.DbInstraction.CityAction;
import com.graphnetwork.network.DbInstraction.CountryAction;
import com.graphnetwork.network.DbInstraction.StateAction;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaMessageListener {
    @Autowired
    @Qualifier("CountryAction")
    private CountryAction countryAction;
    @Autowired
    @Qualifier("StateAction")
    private StateAction stateAction;
    @Autowired
    @Qualifier("CityAction")
    private CityAction cityAction;
    @Autowired
    @Qualifier("BuildingAction")
    private BuildingAction buildingAction;

    @KafkaListener(topics = "inventory", groupId = "test-group")
    public void listen(KafaMessagedto message) {
        String dtoname = message.getDto();
        System.out.println(dtoname);
        switch (dtoname) {
            case "Country":
                countryAction.crudoperation(message);
                break;
            case "State":
                stateAction.crudoperation(message);
                break;
            case "City":
                cityAction.crudoperation(message);
                break;
            case "Building":
                buildingAction.crudoperation(message);
                break;
            default:
                break;
        }
    }
}
