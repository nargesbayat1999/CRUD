package org.bayat.crud.view.controller;


import org.bayat.crud.model.entity.Data;
import org.bayat.crud.view.controller.dto.DataDTO;
import org.bayat.crud.service.CrudServiceSmile;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/Data")
@RestController
public class DataController {
   final CrudServiceSmile crudServiceSmile;

    public DataController(CrudServiceSmile crudServiceSmile) {

        this.crudServiceSmile = crudServiceSmile;
    }

    @GetMapping("/id")
    public void getData(@RequestParam int id) {
        // you can convert it to dto and return dto
        crudServiceSmile.findById(id);
    }

    @PostMapping("/add")
    public void addData(@RequestBody DataDTO dataDTO) {
        crudServiceSmile.saveOrUpdate(dataDTO);
    }

    @DeleteMapping("/delete")
    public void deleteData(@RequestParam int id) {
        Optional<Data> data = crudServiceSmile.findById(id);
        if (data.isPresent()) {
            crudServiceSmile.delete(data.get());
        } else {
            System.out.println("Data not found with id: " + id);
        }
    }

}

