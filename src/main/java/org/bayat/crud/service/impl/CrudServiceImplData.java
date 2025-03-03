package org.bayat.crud.service.impl;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.About;
import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.enums.Message;
import org.bayat.crud.model.repository.AboutRepository;
import org.bayat.crud.model.repository.DataRepository;
import org.bayat.crud.service.CrudService;
import org.bayat.crud.service.mapper.MappingData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CrudServiceImplData implements CrudService {


    private final DataRepository dataRepository;
    private final MappingData mappingData;
    private final AboutRepository aboutRepository;


    public CrudServiceImplData(DataRepository dataRepository, MappingData mappingData, AboutRepository aboutRepository) {
        this.mappingData = mappingData;
        this.dataRepository = dataRepository;
        this.aboutRepository = aboutRepository;
    }

    @Transactional
    public ResponseEntity<GenericResponse<DataDTO>> delete(long id) {
        Optional<Data> data = dataRepository.findById(id);
        if (data.isPresent()) {
            Data foundData = data.get();
            foundData.setDeleted(true);
            List<About> abouts = aboutRepository.findByData(foundData);
            if (!abouts.isEmpty()) {
                abouts.forEach(about -> {
                    about.setDeleted(true);
//                    aboutRepository.save(about);
                });
                aboutRepository.saveAll(abouts);
            }
            dataRepository.save(foundData);
            return ResponseEntity.ok(new GenericResponse<>(Message.DELETE_USER.getMessage(), "2", null));
        } else {
            System.out.println(Message.DATA_NOT_FOUND.getMessage() + id);
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<GenericResponse<DataDTO>> edit(DataDTO dataDTO) {
        try {
            Optional<Data> returnedData = dataRepository.findByPhone(dataDTO.getPhone());
            if (returnedData.isPresent()) {
                Data data = mappingData.convertExistedDataDTOtoData(dataDTO, returnedData.get());
                if (dataDTO.getAddress() != null) {
                    About about = mappingData.aboutDTOToData(dataDTO);
                    about.setData(data);
                    aboutRepository.save(about);
                }
                dataRepository.save(data);
            } else {
                Data data = mappingData.convertNewDataDTOtoData(dataDTO);
                dataRepository.save(data);
                if (dataDTO.getAddress() != null) {
                    About about = mappingData.aboutDTOToData(dataDTO);
                    about.setData(data);
                    aboutRepository.save(about);
                }
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
            if (dataDTO != null) {
                return ResponseEntity.ok(new GenericResponse<>(Message.FIND_USER.getMessage(), "2", dataDTO));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<GenericResponse<DataDTO>> insert(DataDTO dataDTO) {
        try {
            Data data = dataRepository.save(mappingData.convertNewDataDTOtoData(dataDTO));
            if (dataDTO.getAddress() != null) {
                About about = mappingData.aboutDTOToData(dataDTO);
                about.setData(data);
                aboutRepository.save(about);
            }
            return ResponseEntity.ok(new GenericResponse<>(Message.SUCCESSFUL.getMessage(), "201", dataDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new GenericResponse<>(Message.USER_ERROR.getMessage(), "400", null));
        }
    }

    @Transactional
    public ResponseEntity<GenericResponse<DataDTO>> update(DataDTO dataDTO) {
        try {
            Optional<Data> returnedData = dataRepository.findByPhone(dataDTO.getPhone());
            if (returnedData.isPresent()) {
                Data data = mappingData.convertExistedDataDTOtoData(dataDTO, returnedData.get());
                data = dataRepository.save(data);
                if (dataDTO.getAddress() != null) {
                    About about = mappingData.aboutDTOToData(dataDTO);
                    about.setData(data);
                    aboutRepository.save(about);
                }
                dataDTO = mappingData.dataToDataDTO(data);
                return ResponseEntity.ok(new GenericResponse<>(Message.SUCCESSFUL.getMessage(), "20", dataDTO));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
