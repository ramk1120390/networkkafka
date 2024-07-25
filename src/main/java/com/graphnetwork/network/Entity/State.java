package com.graphnetwork.network.Entity;

import com.graphnetwork.network.Entity.Country;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class State {
    @Id
    @GeneratedValue
    private Long id;
    private String name;  // Changed 'Name' to 'name' to match JSON structure
    private String desc;

    @Relationship(type = "COUNTRY_TO_STATE", direction = Relationship.Direction.INCOMING)
    private Country country;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
