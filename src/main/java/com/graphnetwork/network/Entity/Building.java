package com.graphnetwork.network.Entity;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node
public class Building {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String clliCode;
    private String phoneNumber;
    private String contactPerson;
    private String address;
    private String latitude;
    private String longitude;
    private String drivingInstructions;
    private String href;
    private String notes;
    @Relationship(type = "CITY_TO_BUILDING", direction = Relationship.Direction.INCOMING)
    private City city;

    @Relationship(type = "BUILDING_TO_ADDITIONAL_ATTRIBUTE", direction = Relationship.Direction.OUTGOING)
    private ArrayList<AdditionalAttribute> additionalAttributes;

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

    public String getClliCode() {
        return clliCode;
    }

    public void setClliCode(String clliCode) {
        this.clliCode = clliCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDrivingInstructions() {
        return drivingInstructions;
    }

    public void setDrivingInstructions(String drivingInstructions) {
        this.drivingInstructions = drivingInstructions;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<AdditionalAttribute> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(ArrayList<AdditionalAttribute> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    public Building() {
    }

    public Building(Long id, String name, String clliCode, String phoneNumber, String contactPerson, String address, String latitude, String longitude, String drivingInstructions, String href, String notes, City city, ArrayList<AdditionalAttribute> additionalAttributes) {
        this.id = id;
        this.name = name;
        this.clliCode = clliCode;
        this.phoneNumber = phoneNumber;
        this.contactPerson = contactPerson;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.drivingInstructions = drivingInstructions;
        this.href = href;
        this.notes = notes;
        this.city = city;
        this.additionalAttributes = additionalAttributes;
    }
}