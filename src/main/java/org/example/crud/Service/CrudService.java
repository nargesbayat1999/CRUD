package org.example.crud.Service;

import org.example.crud.Entity.Data;
import org.example.crud.Repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudService {

    private final DataRepository dataRepository;

    public CrudService(DataRepository dataRepository) {
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
