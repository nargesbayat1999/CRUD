package org.bayat.crud.service.impl;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.repository.DataRepository;
import org.bayat.crud.service.CrudService;
import org.bayat.crud.service.mapper.MappingData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class CrudServiceImpl implements CrudService {


    private final DataRepository dataRepository;
    private final MappingData mappingData;


    public CrudServiceImpl(DataRepository dataRepository, MappingData mappingData) {
        this.mappingData = mappingData;
        this.dataRepository = dataRepository;
    }

    public ResponseEntity<GenericResponse<DataDTO>> delete(@RequestParam(name = "id") int id) {
        Optional<Data> data = dataRepository.findById(id);
        if (data.isPresent()) {

            return ResponseEntity.ok(new GenericResponse<>("کاربر با آیدی مورد نظر پاک شد", "2", null));
        } else {
            System.out.println("Data not found with id: " + id);

            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<GenericResponse<DataDTO>> add(DataDTO dataDTO) {
        try {
            dataRepository.save(mappingData.dataDTOToData(dataDTO));
            return ResponseEntity.ok(new GenericResponse<>("موفق", "201", dataDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse<>("خطای کاربر", "400", null));
        }

    }

    public ResponseEntity<GenericResponse<DataDTO>> findById(Integer id) {
        Optional<Data> optionalData = dataRepository.findById(id);
        DataDTO dataDTO = optionalData.map(mappingData::dataToDataDTO).orElse(null);
        try {
            return ResponseEntity.ok(new GenericResponse<>("کاربر مورد نظر یافت شد", "2", dataDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<GenericResponse<DataDTO>> register(DataDTO dataDTO) {
        try {
            Data data = mappingData.dataDTOToData(dataDTO);
            Optional<Data> returnedData = dataRepository.findByPhone(data.getPhone());
            if (returnedData.isPresent()) {
                data.setId(returnedData.get().getId());
                dataRepository.save(data);
            } else {
                dataRepository.save(data);
            }
            return ResponseEntity.ok(new GenericResponse<>("کاربر مورد نظر ثبت شد", "20", dataDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<GenericResponse<DataDTO>> update(DataDTO dataDTO) {
        dataRepository.save(mappingData.dataDTOToData(dataDTO));
        return ResponseEntity.ok(new GenericResponse<>("شماره تلفن بروزرسانی شد", "200", dataDTO));
    }
}
