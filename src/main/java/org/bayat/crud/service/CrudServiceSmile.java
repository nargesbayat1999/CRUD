package org.bayat.crud.service;

import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.entity.DataC;
import org.bayat.crud.model.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudServiceSmile implements CrudService {

    private final DataRepository dataRepository;



    public CrudServiceSmile(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    public void delete(DataC datac) {
        Data data=new Data();
        data.setId(datac.getId());
        dataRepository.delete(data);
    }

    public void saveOrUpdate(DataC datac) {
        Data data=new Data();
        data.setId(datac.getId());
        data.setPhone(datac.getPhone());
        data.setName(datac.getName());
        dataRepository.save(data);
    }

    public Optional<DataC> findById(Integer id) {
        return dataRepository.findById(id);
    }

}
