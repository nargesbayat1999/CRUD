package org.bayat.crud.service.mapper;

import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.About;
import org.bayat.crud.model.entity.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MappingDataTest {


    private MappingData mappingData;
    private Data data;
    private About about;
    private DataDTO dto;

    @BeforeEach
    void setUp() {
        mappingData = new MappingData();
        data = new Data();
        data.setName("John Doe");
        data.setPhone("1234556545456789");
        about = new About();
        about.setAddress("تهران،دلاوران");
        dto = new DataDTO();
        dto.setName("narges");
        dto.setPhone("1234556545456789");
        dto.setAddress("تهران دلاوران");
        dto.setNewPhoneNumber("1234556545456789");


    }

    @Test
    void dataToDataDTOTest() {
        DataDTO dataDTO = mappingData.dataToDataDTO(data);
        assertEquals(dataDTO.getName(), data.getName());
        assertEquals(dataDTO.getPhone(), data.getPhone());

    }

    @Test
    void convertNewDataDTOtoDataTest() {
        Data data = mappingData.convertNewDataDTOtoData(dto);
        assertEquals(data.getName(), dto.getName());
        assertEquals(data.getPhone(), dto.getPhone());

    }

    @Test
    void convertExistedDataDTOtoDataTest() {
        Data data1 = mappingData.convertExistedDataDTOtoData(dto, data);
        assertEquals(data1.getName(), dto.getName());
        if (dto.getNewPhoneNumber() != null) {
            assertEquals(data1.getPhone(), dto.getPhone());
        } else {
            assertEquals(data1.getPhone(), dto.getPhone());
        }
    }
    @Test
    void aboutDTOToDataTest() {
        About about=mappingData.aboutDTOToData(dto);
        assertEquals(about.getAddress(), dto.getAddress());
    }


}
