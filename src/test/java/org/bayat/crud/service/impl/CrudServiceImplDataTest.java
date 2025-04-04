package org.bayat.crud.service.impl;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.About;
import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.enums.Message;
import org.bayat.crud.model.repository.AboutRepository;
import org.bayat.crud.model.repository.DataRepository;
import org.bayat.crud.service.mapper.MappingData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudServiceImplDataTest {

    @Mock
    private DataRepository dataRepository;

    @Mock
    private AboutRepository aboutRepository;
    @Mock
    private MappingData mappingData;

    @InjectMocks
    private CrudServiceImplData crudService;

    private Data data;
    private About about;
    private DataDTO dataDTO;

    @BeforeEach
    void setUp() {
        data = new Data();
        data.setId(1L);
        data.setDeleted(false);

        about = new About();
        about.setId(1L);
        about.setDeleted(false);
        about.setData(data);

        dataDTO = new DataDTO();
        dataDTO.setNewPhoneNumber("45454545");
        dataDTO.setName("Test");
        dataDTO.setAddress("دلاوران");
        dataDTO.setPhone("123");
    }

    @Test
    void testDelete_DataFoundAndDeletedSuccessfully() {

        when(dataRepository.findById(1L)).thenReturn(Optional.of(data));
        when(aboutRepository.findByData(data)).thenReturn(List.of(about));


        ResponseEntity<GenericResponse<DataDTO>> response = crudService.delete(1L);


        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Message.DELETE_USER.getMessage(), response.getBody().getMessage());
        assertEquals("2", response.getBody().getErrorCode());
        assertNull(response.getBody().getData());


        assertTrue(data.getDeleted());
        assertTrue(about.getDeleted());

        // Verify interactions
        verify(dataRepository, times(1)).findById(1L);
        verify(aboutRepository, times(1)).findByData(data);
        verify(dataRepository, times(1)).save(data);
        verify(aboutRepository, times(1)).saveAll(List.of(about));
    }

    @Test
    void testDelete_DataNotFound() {
        // Arrange
        when(dataRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<GenericResponse<DataDTO>> response = crudService.delete(1L);


        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());

        // Verify interactions
        verify(dataRepository, times(1)).findById(1L);
        verify(aboutRepository, never()).findByData(any());
        verify(dataRepository, never()).save(any());
        verify(aboutRepository, never()).saveAll(any());
    }


    @Test
    void testDelete_aboutNotFound() {
//        // Arrange
        Data data = new Data();
        data.setId(1L);
        data.setDeleted(false);

        when(dataRepository.findById(1L)).thenReturn(Optional.of(data)); // داده پیدا شد
        when(aboutRepository.findByData(data)).thenReturn(Collections.emptyList()); // لیست abouts خالی است

        // Act
        ResponseEntity<GenericResponse<DataDTO>> response = crudService.delete(1L);

        // Assert
        assertNotNull(response);
        assertEquals("2", response.getBody().getErrorCode());
        assertNull(response.getBody().getData());

        // Verify interactions
        verify(dataRepository, times(1)).findById(1L); // findById فراخوانی شده است
        verify(aboutRepository, times(1)).findByData(data); // findByData فراخوانی شده است
        verify(dataRepository, times(1)).save(data); // data ذخیره شده است
        verify(aboutRepository, never()).saveAll(any()); // saveAll فراخوانی نشده است
    }


    @Test
    void testDelete_ExceptionThrown() {
        // Arrange
        when(dataRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> crudService.delete(1L));

        // Verify interactions
        verify(dataRepository, times(1)).findById(1L);
        verify(aboutRepository, never()).findByData(any());
        verify(dataRepository, never()).save(any());
        verify(aboutRepository, never()).saveAll(any());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testEdit_DataFoundAndEditSuccessfully(boolean isAddressNull) {
        when(dataRepository.findByPhone("123")).thenReturn(Optional.of(data));
        when(mappingData.convertExistedDataDTOtoData(dataDTO, data)).thenReturn(data);
        lenient().when(mappingData.aboutDTOToData(dataDTO)).thenReturn(about);
        if (isAddressNull) {
            dataDTO.setAddress(null);
        }
        ResponseEntity<GenericResponse<DataDTO>> response = crudService.edit(dataDTO);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        if (isAddressNull) {
            verify(aboutRepository, never()).save(about);
        } else {
            verify(aboutRepository, times(1)).save(about);
        }

    }

    @Test
    void testEdit_ExceptionThrown() {
        // Arrange
        when(dataRepository.findByPhone("123")).thenThrow(RuntimeException.class);

        // Act
        ResponseEntity<GenericResponse<DataDTO>> response = crudService.edit(dataDTO);

        // Assert
        assertNotNull(response);
        assertTrue(response.getStatusCode().is5xxServerError());

        verify(dataRepository, times(1)).findByPhone("123");

    }

    @Test
    void testEdit_DataNotFound() {
        when(dataRepository.findByPhone("123")).thenReturn(Optional.empty());
        when(mappingData.convertNewDataDTOtoData(dataDTO)).thenReturn(data);
        when(mappingData.aboutDTOToData(dataDTO)).thenReturn(about);

        ResponseEntity<GenericResponse<DataDTO>> response = crudService.edit(dataDTO);
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(dataRepository, times(1)).findByPhone("123");
        verify(aboutRepository, never()).findByData(any());
        verify(aboutRepository, times(1)).save(any(About.class));
        verify(dataRepository, times(1)).save(data);

    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testFindById(boolean isFindById) {
        when(dataRepository.findById(1L)).thenReturn(Optional.of(data));
        if (isFindById) {
            when(mappingData.dataToDataDTO(data)).thenReturn(dataDTO);
        } else {
            when(mappingData.dataToDataDTO(data)).thenReturn(null);
        }
        ResponseEntity<GenericResponse<DataDTO>> response = crudService.findById(1L);
        assertNotNull(response);
        if (isFindById) {
            assertTrue(response.getStatusCode().is2xxSuccessful());
        } else {
            assert (HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
        }
        verify(dataRepository, times(1)).findById(1L);

    }


//    @Test
//    void testFindById_ExceptionThrown() {
//        when(dataRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));
//        assertThrows(RuntimeException.class, () -> crudService.findById(1L));
//    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testInsert(boolean hasAddress) {
        // Arrange
        if (!hasAddress) {
            dataDTO.setAddress(null);
        }

        when(mappingData.convertNewDataDTOtoData(dataDTO)).thenReturn(data);
        when(dataRepository.save(data)).thenReturn(data);


        if (hasAddress) {
            when(mappingData.aboutDTOToData(dataDTO)).thenReturn(about);
            when(aboutRepository.save(about)).thenReturn(about);
        }


        ResponseEntity<GenericResponse<DataDTO>> response = crudService.insert(dataDTO);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        GenericResponse<DataDTO> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(Message.SUCCESSFUL.getMessage(), responseBody.getMessage());
        assertEquals("201", responseBody.getErrorCode());
        assertEquals(dataDTO, responseBody.getData());


        verify(mappingData, times(1)).convertNewDataDTOtoData(dataDTO);
        verify(dataRepository, times(1)).save(data);


        if (hasAddress) {
            verify(mappingData, times(1)).aboutDTOToData(dataDTO);
            verify(aboutRepository, times(1)).save(about);
        } else {
            verify(mappingData, never()).aboutDTOToData(any());
            verify(aboutRepository, never()).save(any());
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testUpdate_Successful(boolean hasAddress) {
        // Arrange
        if (!hasAddress) {
            dataDTO.setAddress(null);
        }

        when(dataRepository.findByPhone(dataDTO.getPhone())).thenReturn(Optional.of(data));
        when(mappingData.convertExistedDataDTOtoData(dataDTO, data)).thenReturn(data);
        when(dataRepository.save(data)).thenReturn(data);
        when(mappingData.dataToDataDTO(data)).thenReturn(dataDTO);

        if (hasAddress) {
            when(mappingData.aboutDTOToData(dataDTO)).thenReturn(about);
            when(aboutRepository.save(about)).thenReturn(about);
        }


        ResponseEntity<GenericResponse<DataDTO>> response = crudService.update(dataDTO);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        GenericResponse<DataDTO> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(Message.SUCCESSFUL.getMessage(), responseBody.getMessage());
        assertEquals("20", responseBody.getErrorCode());
        assertEquals(dataDTO, responseBody.getData());


        verify(dataRepository).findByPhone(dataDTO.getPhone());
        verify(mappingData).convertExistedDataDTOtoData(dataDTO, data);
        verify(dataRepository).save(data);
        verify(mappingData).dataToDataDTO(data);

        if (hasAddress) {
            verify(mappingData).aboutDTOToData(dataDTO);
            verify(aboutRepository).save(about);
        } else {
            verify(mappingData, never()).aboutDTOToData(any());
            verify(aboutRepository, never()).save(any());
        }
    }
    @Test
    void testUpdate_DataNotFound() {
        when(dataRepository.findByPhone(dataDTO.getPhone())).thenReturn(Optional.empty());
        ResponseEntity<GenericResponse<DataDTO>> response = crudService.update(dataDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


        verify(dataRepository).findByPhone(dataDTO.getPhone());
        verify(mappingData, never()).convertExistedDataDTOtoData(any(), any());
        verify(dataRepository, never()).save(any());
        verify(mappingData, never()).dataToDataDTO(any());
        verify(mappingData, never()).aboutDTOToData(any());
        verify(aboutRepository, never()).save(any());
    }

    @Test
    void testUpdate_InternalServerError() {

        when(dataRepository.findByPhone(dataDTO.getPhone())).thenThrow(new RuntimeException("Database error"));


        ResponseEntity<GenericResponse<DataDTO>> response = crudService.update(dataDTO);


        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());


        verify(dataRepository).findByPhone(dataDTO.getPhone());
        verify(mappingData, never()).convertExistedDataDTOtoData(any(), any());
        verify(dataRepository, never()).save(any());
    }
}






