package com.graphnetwork.network.Dto1;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;

public class KafaMessagedto implements Serializable {
    private String Action;
    private JsonNode Data;

    public KafaMessagedto() {
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public JsonNode getData() {
        return Data;
    }

    public void setData(JsonNode data) {
        Data = data;
    }
}
