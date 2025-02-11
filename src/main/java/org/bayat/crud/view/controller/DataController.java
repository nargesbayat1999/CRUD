package org.bayat.crud.view.controller;


import org.bayat.crud.model.entity.DataC;
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
        crudServiceSmile.findById(id);
    }

    @PostMapping("/add")
    public void addData(@RequestBody DataC datac) {
        crudServiceSmile.saveOrUpdate(datac);
    }

    @DeleteMapping("/delete")
    public void deleteData(@RequestParam int id) {
        Optional<DataC> datac = crudServiceSmile.findById(id);
        if (datac.isPresent()) {
            crudServiceSmile.delete (datac.get());
        } else {

            System.out.println("Data not found with id: " + id);
        }
    }

}

