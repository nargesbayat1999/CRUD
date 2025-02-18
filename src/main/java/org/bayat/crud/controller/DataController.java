package org.bayat.crud.controller;


import lombok.extern.slf4j.Slf4j;
import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
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
            log.debug("getData service called");
            return crudService.findById(id);

        } catch (Exception e) {
            log.error("unknown error is {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> add(@RequestBody DataDTO dataDTO) {
        try {
            log.debug("getData service called");
            return crudService.add(dataDTO);
        }  catch (Exception e) {
            log.error("unknown error is {}", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> delete(@RequestParam(name = "id") int id) {
        try {
            log.debug("Delete service called");
            return crudService.delete(id);
        } catch (Exception e) {
            log.error("unknown error is {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }


    @PatchMapping(value = "/phoneEdite", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> update(@RequestBody DataDTO dataDTO) {
        try {
            log.debug("update service called");
        return  crudService.update(dataDTO);
        } catch (Exception e) {
            log.error("unknown error is {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> registerFirst(@RequestBody DataDTO dataDTO) {
        try {
            log.debug("register service called");
            return crudService.register(dataDTO);
        } catch (Exception e) {
            log.error("unknown error is {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

}

