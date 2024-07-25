package com.graphnetwork.network.DbInstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphnetwork.network.Dto.KafaMessagedto;
import com.graphnetwork.network.Entity.*;
import com.graphnetwork.network.Operation;
import com.graphnetwork.network.Repo.BuildingRepo;
import com.graphnetwork.network.Repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;

@Component("BuildingAction")
public class BuildingAction implements Operation {
    @Autowired
    BuildingRepo buildingRepo;
    @Autowired
    CityRepo cityRepo;
    @Autowired
    AddtionalAttributeApi addtionalAttributeApi;

    @Override
    public void crudoperation(KafaMessagedto kafaMessagedto) {
        if (kafaMessagedto.getAction().equals("CreateBuilding")) {
            insertBuilding(kafaMessagedto);
        }
    }

    private void insertBuilding(KafaMessagedto kafaMessagedto) {
        JsonNode rootNode = kafaMessagedto.getData();
        Building building = new Building();
        building.setName(rootNode.get("buildingName").asText());
        building.setClliCode(rootNode.get("clliCode").asText("NA"));
        building.setPhoneNumber(rootNode.get("phoneNumber").asText("NA"));
        building.setContactPerson(rootNode.get("contactPerson").asText("NA"));
        building.setAddress(rootNode.get("address").asText("NA"));
        building.setLatitude(rootNode.get("latitude").asText("NA"));
        building.setLongitude(rootNode.get("longitude").asText("NA"));
        building.setDrivingInstructions(rootNode.get("drivingInstructions").asText("NA"));
        building.setHref(rootNode.get("href").asText("NA"));
        building.setNotes(rootNode.get("notes").asText("NA"));
        JsonNode cityNode = rootNode.get("city");
        String cityName = cityNode.get("cityName").asText();
        buildingRepo.createBuilding(cityName, building.getName(), building.getClliCode(), building.getPhoneNumber(),
                building.getContactPerson(), building.getAddress(), building.getLatitude(), building.getLongitude(),
                building.getDrivingInstructions(), building.getHref(), building.getNotes());
        JsonNode additionalAttributesNode = rootNode.get("additionalAttributes");
        if (additionalAttributesNode != null) {
            ArrayList<AdditionalAttribute> additionalAttributes = new ArrayList<>();
            Iterator<JsonNode> elements = additionalAttributesNode.elements();
            while (elements.hasNext()) {
                JsonNode attributeNode = elements.next();
                AdditionalAttribute attribute = new AdditionalAttribute();
                attribute.setKey(attributeNode.get("key").asText());
                attribute.setValue(attributeNode.get("value").asText());
                additionalAttributes.add(attribute);
            }
            addtionalAttributeApi.addAdditionalAttributes(additionalAttributes, building);
        }
    }
}
