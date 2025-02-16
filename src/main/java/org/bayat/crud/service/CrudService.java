package org.bayat.crud.service;

import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface CrudService {
    public void delete(int id);
    public void add (@RequestBody DataDTO dataDTO);
    public void saveOrUpdate(DataDTO dataDTO);
    public DataDTO findById(Integer id);
   public void register(@RequestBody DataDTO dataDTO);
}
