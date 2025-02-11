package org.bayat.crud.service;

import org.bayat.crud.model.entity.DataC;


import java.util.Optional;

public interface CrudService {
    public void delete(DataC datac);
    public void saveOrUpdate(DataC datac);
    public Optional<DataC> findById(Integer id);
}
