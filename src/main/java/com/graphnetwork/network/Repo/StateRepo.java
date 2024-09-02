package com.graphnetwork.network.Repo;

import com.graphnetwork.network.Entity.State;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface StateRepo extends Neo4jRepository<State, Long> {
    State findByName(String name);

    @Query("MATCH (c:Country {name: $countryName}) " +
            "WITH c MERGE (s:State {name: $stateName}) " +
            "ON CREATE SET s.desc = $notes " +
            "ON MATCH SET s.desc = $notes " +
            "MERGE (c)-[:COUNTRY_TO_STATE]->(s) RETURN s")
    State CreateState(String countryName, String stateName, String notes);

    @Query("MATCH (s:State {name: $oldStateName}) " +
            "SET s.name = $newStateName, " +
            "s.desc = $desc " +
            "WITH s " +
            "MATCH (c:Country {name: $countryName}) " +
            "MERGE (c)-[:COUNTRY_TO_STATE]->(s) " +
            "RETURN s")
    State updateStateNameAndLinkCountry(String oldStateName, String newStateName, String desc, String countryName);

    @Query("MATCH (oldState:State {name: $oldStateName})-[r:COUNTRY_TO_STATE]->(oldCountry:Country {name: $oldCountryName}) " +
            "DELETE r " +
            "DETACH DELETE oldState")
    void deleteStateRel(@Param("oldStateName") String oldStateName,
                        @Param("oldCountryName") String oldCountryName);



    @Query("MATCH (s:State {name: $stateName}) " +
            "DETACH DELETE s")
    void deleteState(@Param("stateName") String stateName);

}
