package com.solvd.buildingcompany.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.buildingcompany.model.Equipment;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EquipmentJsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Equipment> readEquipment(String filePath) {
        try {
            Equipment[] equipmentArray = objectMapper.readValue(new File(filePath), Equipment[].class);
            return Arrays.asList(equipmentArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeEquipment(String filePath, List<Equipment> equipmentList) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), equipmentList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
