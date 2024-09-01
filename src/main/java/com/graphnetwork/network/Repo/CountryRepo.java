package com.graphnetwork.network.Repo;

import com.graphnetwork.network.Entity.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends Neo4jRepository<Country, Long> {
    @Query("MATCH (n:Country) WHERE n.name = $name RETURN n")
    Country findByName(String name);

    @Query("MATCH (c:Country {name: $name}) DETACH DELETE c")
    void deleteByName(@Param("name") String name);

}