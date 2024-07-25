package com.graphnetwork.network.Repo;

import com.graphnetwork.network.Entity.State;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface StateRepo extends Neo4jRepository<State, Long> {
    State findByName(String name);

    @Query("MATCH (c:Country {name: $countryName}) " +
            "WITH c MERGE (s:State {name: $stateName}) " +
            "ON CREATE SET s.desc = $notes " +
            "ON MATCH SET s.desc = $notes " +
            "MERGE (c)-[:COUNTRY_TO_STATE]->(s) RETURN s")
    State CreateState(String countryName, String stateName, String notes);
}
