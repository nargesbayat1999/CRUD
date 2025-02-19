package org.bayat.crud.service;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.springframework.http.ResponseEntity;

public interface CrudService {
    public ResponseEntity<GenericResponse<DataDTO>> delete(int id);
    public ResponseEntity<GenericResponse<DataDTO>> add (DataDTO dataDTO);
    public ResponseEntity<GenericResponse<DataDTO>> findById(Integer id);
    public ResponseEntity<GenericResponse<DataDTO>> register( DataDTO dataDTO);
    public ResponseEntity<GenericResponse<DataDTO>> update(DataDTO dataDTO);
}
