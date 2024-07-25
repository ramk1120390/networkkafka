package com.graphnetwork.network.DbInstraction;

import com.graphnetwork.network.Entity.AdditionalAttribute;
import com.graphnetwork.network.Entity.Building;
import com.graphnetwork.network.Repo.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class AddtionalAttributeApi {
    @Autowired
    BuildingRepo buildingRepo;

    public void addAdditionalAttributes(ArrayList<AdditionalAttribute> additionalAttributes,
                                        Object inventoryObject) {
        if (additionalAttributes.size() != 0) {
            if (inventoryObject instanceof Building) {
                Building building = (Building) inventoryObject;
                building.setAdditionalAttributes(additionalAttributes);
                buildingRepo.save(building);
            }
        }
    }
}
