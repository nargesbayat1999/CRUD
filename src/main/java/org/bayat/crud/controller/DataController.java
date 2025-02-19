package org.bayat.crud.controller;


import lombok.extern.slf4j.Slf4j;
import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.Data;
import org.bayat.crud.model.enums.Message;
import org.bayat.crud.service.CrudService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@Deprecated
@RequestMapping("/data/v1")
@RestController
@Slf4j
public class DataController {

    final CrudService crudService;

    public DataController(CrudService crudService) {
        this.crudService = crudService;
    }


    @GetMapping(value = "/getData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> getData(@RequestParam(name = "id") int id) {
        try {
            log.debug(Message.GETDATA_SERVICE_CALL.getMessage());
            return crudService.findById(id);

        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(), e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> add(@RequestBody DataDTO dataDTO) {
        try {
            log.debug(Message.ADD_SERVICE_CALL.getMessage());

            return crudService.add(dataDTO);
        }  catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(),e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> delete(@RequestParam(name = "id") int id) {
        try {
            log.debug(Message.DELETED_SERVICE_CALL.getMessage());
            return crudService.delete(id);
        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(),e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }


    @PatchMapping(value = "/editPhone", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> update(@RequestBody DataDTO dataDTO) {
        try {
            log.debug(Message.EDIT_PHONE_SERVICE_CALL.getMessage());
        return  crudService.update(dataDTO);
        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(),e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> registerFirst(@RequestBody DataDTO dataDTO) {
        try {
            log.debug(Message.REGISTER_SERVICE_CALL.getMessage());
            return crudService.register(dataDTO);
        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

}

