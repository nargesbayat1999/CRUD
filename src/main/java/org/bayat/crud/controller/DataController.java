package org.bayat.crud.controller;


import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.service.CrudService;
import org.springframework.web.bind.annotation.*;


//@Deprecated
@RequestMapping("/data/v1")
@RestController
public class DataController {

    final CrudService crudService;

    public DataController(CrudService crudService) {
        this.crudService = crudService;
    }

    //todo
    @GetMapping("/getData")
    public DataDTO getData(@RequestParam(name = "id") int id) {
        return crudService.findById(id);
    }

    @PutMapping("/add")
    public void addData(@RequestBody DataDTO dataDTO) {
        crudService.add(dataDTO);
    }


    @DeleteMapping("/delete")
    public void deleteData(@RequestParam(name = "id") int id) {
        crudService.delete(id);

    }

    @PatchMapping("/phone")
    public void patchData(@RequestBody DataDTO dataDTO) {
        crudService.saveOrUpdate(dataDTO);
    }

}

