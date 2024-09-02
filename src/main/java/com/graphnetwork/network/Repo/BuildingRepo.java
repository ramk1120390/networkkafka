package com.graphnetwork.network.Repo;

import com.graphnetwork.network.Entity.Building;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface BuildingRepo extends Neo4jRepository<Building, Long> {
    @Query("MERGE (d:Building {name: $buildingName}) ON CREATE SET d.name = $buildingName, d.clliCode = $clliCode," +
            "d.phoneNumber = $phoneNumber, d.contactPerson = $contactPerson, d.address = $address," +
            "d.latitude = $latitude, d.longitude = $longitude, d.drivingInstructions = $drivingInstructions," +
            "d.notes=$notes, d.href = $href " +
            "WITH d " +
            "match(c:City {name: $cityName}) " +
            "MERGE (c)-[:CITY_TO_BUILDING]->(d)" +
            "return d")
    Building createBuilding(@Param("cityName") String cityName, @Param("buildingName") String buildingName,
                            @Param("clliCode") String clliCode,
                            @Param("phoneNumber") String phoneNumber,
                            @Param("contactPerson") String contactPerson, @Param("address") String address,
                            @Param("latitude") String latitude,
                            @Param("longitude") String longitude,
                            @Param("drivingInstructions") String drivingInstructions,
                            @Param("href") String href,
                            @Param("notes") String notes);

    @Query("MATCH (b:Building {name: $stateName}) " +
            "DETACH DELETE b")
    void deleteBuilding(@Param("buildingName") String buildingName);

}
