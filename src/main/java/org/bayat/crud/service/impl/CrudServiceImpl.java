package org.bayat.crud.service.impl;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.enums.Message;
import org.bayat.crud.model.repository.DataRepository;
import org.bayat.crud.service.CrudService;
import org.bayat.crud.service.mapper.MappingData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudServiceImpl implements CrudService {


    private final DataRepository dataRepository;
    private final MappingData mappingData;


    public CrudServiceImpl(DataRepository dataRepository, MappingData mappingData) {
        this.mappingData = mappingData;
        this.dataRepository = dataRepository;
    }

    public ResponseEntity<GenericResponse<DataDTO>> delete(long id) {
        Optional<Data> data = dataRepository.findById(id);
        if (data.isPresent()) {
            dataRepository.deleteById(id);
            return ResponseEntity.ok(new GenericResponse<>(Message.DELETE_USER.getMessage(), "2", null));
        } else {
            System.out.println(Message.DATA_NOT_FOUND.getMessage() + id);
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<GenericResponse<DataDTO>> edit(DataDTO dataDTO) {


        try {
            Data data = mappingData.dataDTOToData(dataDTO);
            Optional<Data> returnedData = dataRepository.findByPhone(data.getPhone());
            if (returnedData.isPresent()) {
                data.setId(returnedData.get().getId());
                dataRepository.save(data);
            } else {
                dataRepository.save(data);
            }
            return ResponseEntity.ok(new GenericResponse<>(Message.USER_REGISTERED.getMessage(), "20", dataDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<GenericResponse<DataDTO>> findById(long id) {
        Optional<Data> optionalData = dataRepository.findById(id);
        DataDTO dataDTO = optionalData.map(mappingData::dataToDataDTO).orElse(null);
        try {
            return ResponseEntity.ok(new GenericResponse<>(Message.FIND_USER.getMessage(), "2", dataDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<GenericResponse<DataDTO>> insert(DataDTO dataDTO) {
        try {
            dataRepository.save(mappingData.dataDTOToData(dataDTO));
            return ResponseEntity.ok(new GenericResponse<>(Message.SUCCESSFUL.getMessage(), "201", dataDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse<>(Message.USER_ERROR.getMessage(), "400", null));
       }

    }

    public ResponseEntity<GenericResponse<DataDTO>> update(DataDTO dataDTO) {
        try {
            Data data = mappingData.dataDTOToData(dataDTO);
            Optional<Data> returnedData = dataRepository.findByPhone(data.getPhone());
            if (returnedData.isPresent()) {
                data.setId(returnedData.get().getId());
                dataRepository.save(data);
                return ResponseEntity.ok(new GenericResponse<>(Message.USER_REGISTERED.getMessage(), "20", dataDTO));
            } else {
                return ResponseEntity.notFound().build();

            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
