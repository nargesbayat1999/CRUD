package org.bayat.crud.service.impl;

import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.repository.DataRepository;
import org.bayat.crud.service.CrudService;
import org.bayat.crud.service.mapper.MappingData;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CrudServiceImpl implements CrudService {

    private final DataRepository dataRepository;
    private final MappingData mappingData;


    public CrudServiceImpl(DataRepository dataRepository, MappingData mappingData) {
        this.dataRepository = dataRepository;
        this.mappingData = mappingData;
    }

    public void delete(int id) {
        Optional<Data> data = dataRepository.findById(id);
        if (data.isPresent()) {
            dataRepository.delete(data.get());
        } else {
            System.out.println("Data not found with id: " + id);
        }
    }

    public void saveOrUpdate(@RequestBody DataDTO dataDTO) {
        Data data = mappingData.DataDtoTodata(dataDTO);
        dataRepository.save(data);
    }

    public void add (@RequestBody DataDTO dataDTO) {
        dataRepository.save(mappingData.DataDtoTodata(dataDTO));
    }

    public DataDTO findById(Integer id) {
        Optional<Data> optionalData = dataRepository.findById(id);
        return optionalData.map(mappingData::DataToDataDto).orElse(null);
    }

}
