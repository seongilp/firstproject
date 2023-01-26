package com.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    @Test
    public void java_to_json() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티","순소고기 패티","토마토","양상추");
        Burger burger = new Burger("맥도날드 슈비버거",5500,ingredients);
        String json = objectMapper.writeValueAsString(burger);
        String expected = "{\"name\":\"맥도날드 슈비버거\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순소고기 패티\",\"토마토\",\"양상추\"]}";
        assertEquals(expected,json);
//        System.out.println(json);
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void json_to_java() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "{\"name\":\"맥도날드 슈비버거\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순소고기 패티\",\"토마토\",\"양상추\"]}";
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name","맥도날드 슈비버거");
        objectNode.put("price","5500");
        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우 패티");
        arrayNode.add("순소고기 패티");
        arrayNode.add("토마토");
        arrayNode.add("양상추");
        objectNode.set("ingredients",arrayNode);
        String json = objectNode.toString();

        Burger burger = objectMapper.readValue(json,Burger.class);
        List<String> ingredients = Arrays.asList("통새우 패티","순소고기 패티","토마토","양상추");
        Burger expected = new Burger("맥도날드 슈비버거",5500,ingredients);
        assertEquals(expected.toString(),burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }
}