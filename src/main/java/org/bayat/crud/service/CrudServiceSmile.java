package org.bayat.crud.service;

import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudServiceSmile implements CrudService {

    private final DataRepository dataRepository;

    public CrudServiceSmile(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    public void delete(Data data) {
        dataRepository.delete(data);
    }

    public void saveOrUpdate(Data data) {
        dataRepository.save(data);
    }

    public Optional<Data> findById(Integer id) {
        return dataRepository.findById(id);


    }


}
