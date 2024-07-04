package com.graphnetwork.network.Service;

import com.graphnetwork.network.Dto.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CountryRepo extends Neo4jRepository<Country, Long> {
}
