package org.bayat.crud.service;

import org.bayat.crud.model.entity.Data;
import org.bayat.crud.view.controller.dto.DataDTO;

import java.util.Optional;

public interface CrudService {
    public void delete(Data data);

    public void saveOrUpdate(DataDTO datac);

    public Optional<Data> findById(Integer id);
}
