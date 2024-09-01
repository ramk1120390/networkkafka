package com.graphnetwork.network.Repo;

import com.graphnetwork.network.Entity.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepo extends Neo4jRepository<City, Long> {
    City findByName(String name);

    @Query("match(s:State {name: $stateName})" +
            "WITH s MERGE (c:City {name: $cityName}) ON CREATE SET c.desc=$notes ON MATCH SET c.desc=$notes " +
            "MERGE (s)-[:STATE_TO_CITY]->(c) RETURN c")
    City createCity(@Param("stateName") String stateName, @Param("cityName") String cityName,
                    @Param("notes") String notes);

    @Query("MATCH (s:State {name: $stateName})" +
            "MATCH (c:City {name: $cityName}) " +
            "WHERE (s)-[:STATE_TO_CITY]->(c) " +
            "SET c.desc = $notes " +
            "RETURN c")
    City updateCity(@Param("stateName") String stateName,
                    @Param("cityName") String cityName,
                    @Param("notes") String notes);

    @Query("MATCH (c:City {name: $cityName}) " +
            "DETACH DELETE c")
    void deleteCity(@Param("cityName") String cityName);

}
