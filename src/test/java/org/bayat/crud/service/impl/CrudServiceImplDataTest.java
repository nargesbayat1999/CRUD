package org.bayat.crud.service.impl;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.About;
import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.enums.Message;
import org.bayat.crud.model.repository.AboutRepository;
import org.bayat.crud.model.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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

    @InjectMocks
    private CrudServiceImplData crudService;

    private Data data;
    private About about;

    @BeforeEach
    void setUp() {
        data = new Data();
        data.setId(1L);
        data.setDeleted(false);

        about = new About();
        about.setId(1L);
        about.setDeleted(false);
        about.setData(data);
    }

    @Test
    void testDelete_DataFoundAndDeletedSuccessfully() {
        // Arrange
        when(dataRepository.findById(1L)).thenReturn(Optional.of(data));
        when(aboutRepository.findByData(data)).thenReturn(List.of(about));

        // Act
        ResponseEntity<GenericResponse<DataDTO>> response = crudService.delete(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Message.DELETE_USER.getMessage(), response.getBody().getMessage());
        assertEquals("2", response.getBody().getErrorCode());
        assertNull(response.getBody().getData());

        // بررسی تغییر وضعیت `deleted`
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
}
