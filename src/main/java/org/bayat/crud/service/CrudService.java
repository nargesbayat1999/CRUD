package org.bayat.crud.service;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudService {
    public ResponseEntity<GenericResponse<DataDTO>> delete(int id);
    public ResponseEntity<GenericResponse<DataDTO>> add (@RequestBody DataDTO dataDTO);
    public ResponseEntity<GenericResponse<DataDTO>> findById(Integer id);
    public ResponseEntity<GenericResponse<DataDTO>> register(@RequestBody DataDTO dataDTO);
    public ResponseEntity<GenericResponse<DataDTO>> update(DataDTO dataDTO);
}
