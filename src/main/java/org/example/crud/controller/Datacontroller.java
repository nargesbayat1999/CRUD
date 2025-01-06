package org.example.crud.controller;

import org.example.crud.Entity.Data;
import org.example.crud.Service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/Data")
@RestController

public class DataController {
    @Autowired
    CrudService crudService;

    @GetMapping("/ID")
    public void getData(@RequestParam int id) {
        crudService.findById(id);

    }

    @PostMapping("/add")
    public void addData(@RequestBody Data data) {
        crudService.saveOrUpdate(data);

    }

    @DeleteMapping("/delete")
    public void deleteData(@RequestParam int id) {
        Optional<Data> data = crudService.findById(id);
        if (data.isPresent()) {
            crudService.delete(data.get());
        } else {

            System.out.println("Data not found with id: " + id);
        }


    }
//    @poll maping
}

