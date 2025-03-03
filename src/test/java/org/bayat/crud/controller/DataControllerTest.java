package org.bayat.crud.controller;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.service.CrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class  DataControllerTest {

    @Mock
    private CrudService crudService;

    @InjectMocks
    private DataController dataController;

    private DataDTO dataDTO;

    @BeforeEach
    void setUp() {
        dataDTO = new DataDTO();
        dataDTO.setName("Test Name");
        dataDTO.setPhone("1234567890");
    }

    @Test
    void testGetData() {
        // Arrange
        GenericResponse<DataDTO> response = new GenericResponse<>("test","code",dataDTO);
        response.setData(dataDTO);
        when(crudService.findById(1L)).thenReturn(ResponseEntity.ok(response));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.getData(1L);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(dataDTO, result.getBody().getData());
        verify(crudService, times(1)).findById(1L);
    }

    @Test
    void testGetData_NoData() {

        when(crudService.findById(2L)).thenReturn(ResponseEntity.notFound().build());

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.getData(2L);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(crudService, times(1)).findById(2L);
    }

    @Test
    void testEdit() {
        // Arrange
        GenericResponse<DataDTO> response = new GenericResponse<>("test","code",dataDTO);
        response.setData(dataDTO);
        when(crudService.edit(dataDTO)).thenReturn(ResponseEntity.ok(response));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.edit(dataDTO);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(dataDTO, result.getBody().getData());
        verify(crudService, times(1)).edit(dataDTO);
    }

    @Test
    void testDelete() {
        // Arrange
        GenericResponse<DataDTO> response = new GenericResponse<>("test","code",dataDTO);
        response.setData(dataDTO);
        when(crudService.delete(1L)).thenReturn(ResponseEntity.ok(response));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.delete(1L);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(dataDTO, result.getBody().getData());
        verify(crudService, times(1)).delete(1L);
    }

    @Test
    void testUpdate() {
        // Arrange
        GenericResponse<DataDTO> response = new GenericResponse<>("test","code",dataDTO);
        response.setData(dataDTO);
        when(crudService.update(dataDTO)).thenReturn(ResponseEntity.ok(response));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.update(dataDTO);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(dataDTO, result.getBody().getData());
        verify(crudService, times(1)).update(dataDTO);
    }

    @Test
    void testInsert() {
        // Arrange
        GenericResponse<DataDTO> response = new GenericResponse<>("test","code",dataDTO);
        response.setData(dataDTO);
        when(crudService.insert(dataDTO)).thenReturn(ResponseEntity.ok(response));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.insert(dataDTO);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(dataDTO, result.getBody().getData());
        verify(crudService, times(1)).insert(dataDTO);
    }

    @Test
    void testGetData_Exception() {
        // Arrange
        when(crudService.findById(1L)).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.getData(1L);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(crudService, times(1)).findById(1L);
    }

    @Test
    void testEdit_Exception() {
        // Arrange
        when(crudService.edit(dataDTO)).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.edit(dataDTO);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(crudService, times(1)).edit(dataDTO);
    }

    @Test
    void testDelete_Exception() {
        // Arrange
        when(crudService.delete(1L)).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.delete(1L);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(crudService, times(1)).delete(1L);
    }

    @Test
    void testUpdate_Exception() {
        // Arrange
        when(crudService.update(dataDTO)).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.update(dataDTO);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(crudService, times(1)).update(dataDTO);
    }

    @Test
    void testInsert_Exception() {
        // Arrange
        when(crudService.insert(dataDTO)).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> result = dataController.insert(dataDTO);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(crudService, times(1)).insert(dataDTO);
    }

    // Example of testing a private method using reflection
//    @Test
//    void testPrivateMethod() throws Exception {
//        // Arrange
//        Method method = DataController.class.getDeclaredMethod("privateMethodName", ParameterType.class);
//        method.setAccessible(true);
//
//        // Act
//        Object result = method.invoke(dataController, argument);
//
//        // Assert
//        assertNotNull(result);
//        // Add more assertions as needed
//    }
}