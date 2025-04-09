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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class CrudServiceImplData implements CrudService {


    private final DataRepository dataRepository;
    private final MappingData mappingData;
    private final AboutRepository aboutRepository;
    private static final Logger logger = LoggerFactory.getLogger(CrudServiceImplData.class);



    public CrudServiceImplData(DataRepository dataRepository, MappingData mappingData, AboutRepository aboutRepository) {
        this.mappingData = mappingData;
        this.dataRepository = dataRepository;
        this.aboutRepository = aboutRepository;
    }


    public ResponseEntity<GenericResponse<DataDTO>> delete(long id) {
        Optional<Data> data = dataRepository.findById(id);
        if (data.isPresent()) {
            Data foundData = data.get();
            foundData.setDeleted(true);
            List<About> abouts = aboutRepository.findByData(foundData);
            if (!abouts.isEmpty()) {
                abouts.forEach(about -> about.setDeleted(true));
                aboutRepository.saveAll(abouts);
            }
            dataRepository.save(foundData);
            return ResponseEntity.ok(new GenericResponse<>(Message.DELETE_USER.getMessageStatus(), "2", null));
        } else {
            logger.error("{}{}", Message.DATA_NOT_FOUND.getMessageStatus(), id);
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<GenericResponse<DataDTO>> edit(DataDTO dataDTO) {
        try {
            Optional<Data> returnedData = dataRepository.findByPhone(dataDTO.getPhone());
            if (returnedData.isPresent()) {
                Data data = mappingData.convertExistedDataDTOtoData(dataDTO, returnedData.get());
                updateAddress(dataDTO, data);
                dataRepository.save(data);
            } else {
                Data data = mappingData.convertDataDTOtoData(dataDTO);
                dataRepository.save(data);
                updateAddress(dataDTO, data);
            }
            return ResponseEntity.ok(new GenericResponse<>(Message.USER_REGISTERED.getMessageStatus(), "20", dataDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    public ResponseEntity<GenericResponse<DataDTO>> findById(long id) {
        try {
            Optional<Data> optionalData = dataRepository.findById(id);
            DataDTO dataDTO = optionalData.map(mappingData::dataToDataDTO).orElse(null);

            if (dataDTO != null) {
                return ResponseEntity.ok(new GenericResponse<>(Message.FIND_USER.getMessageStatus(), "2", dataDTO));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    public ResponseEntity<GenericResponse<DataDTO>> insert(DataDTO dataDTO) {
        try {
            Data data = dataRepository.save(mappingData.convertDataDTOtoData(dataDTO));
            if (dataDTO.getAddress() != null) {
                About about = mappingData.aboutDTOToData(dataDTO);
                about.setData(data);
                aboutRepository.save(about);
            }
            return ResponseEntity.ok(new GenericResponse<>(Message.SUCCESSFUL.getMessageStatus(), "201", dataDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new GenericResponse<>(Message.USER_ERROR.getMessageStatus(), "400", null));
        }
    }


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
                return ResponseEntity.ok(new GenericResponse<>(Message.SUCCESSFUL.getMessageStatus(), "20", dataDTO));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private void updateAddress(DataDTO dataDTO, Data data) {
        if (dataDTO.getAddress() != null) {
            About about = mappingData.aboutDTOToData(dataDTO);
            about.setData(data);
            aboutRepository.save(about);
        }
    }

}
