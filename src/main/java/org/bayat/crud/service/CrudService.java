package org.bayat.crud.service;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.springframework.http.ResponseEntity;

public interface CrudService {
    ResponseEntity<GenericResponse<DataDTO>> delete(long id);
    ResponseEntity<GenericResponse<DataDTO>> edit(DataDTO dataDTO);//add
    ResponseEntity<GenericResponse<DataDTO>> findById(long id);
    ResponseEntity<GenericResponse<DataDTO>> insert(DataDTO dataDTO);//add
    ResponseEntity<GenericResponse<DataDTO>> update(DataDTO dataDTO);
}
