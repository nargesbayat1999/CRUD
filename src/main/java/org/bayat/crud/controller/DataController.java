package org.bayat.crud.controller;


import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.service.CrudService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GenericResponse<DataDTO>> getData(@RequestParam(name = "id") int id) {
        try {
            DataDTO data = crudService.findById(id);
            return ResponseEntity.ok(new GenericResponse<>("کاربر جستجو شده یافت شد", "56", data));
        } catch (NullPointerException e) {
            e.getMessage();
            return ResponseEntity.notFound().build();

        }
        catch (IllegalArgumentException e) {
            e.getMessage();
            return null;
        }
        catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    @PutMapping("/add")
    public ResponseEntity<GenericResponse<DataDTO>> add(@RequestBody DataDTO dataDTO) {
        try {
    crudService.add(dataDTO);
            return ResponseEntity.ok(new GenericResponse<>("این کاربر به دیتا کاربران اضافه شد", "123", dataDTO));
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return null;
        }
        catch (Exception e) {
            e.getMessage();
            return null;
        }
    }




    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse<DataDTO>> delete(@RequestParam(name = "id") int id) {
        try{
            crudService.delete(id);
            DataDTO data = crudService.findById(id);
            return ResponseEntity.ok(new GenericResponse<>("کاربر با آیدی مورد نظر پاک شد", "2",data));
        }catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }


    @PatchMapping("/phone")
    public ResponseEntity<GenericResponse<DataDTO>> update(@RequestBody DataDTO dataDTO) {
        try {
            crudService.saveOrUpdate(dataDTO);
            return ResponseEntity.ok(new GenericResponse<>("شماره تلفن آپدیت شد", "7", dataDTO));

        }
        catch (NullPointerException e) {
            e.getMessage();
        }

        catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

  @PostMapping("/register")
    public ResponseEntity<GenericResponse<DataDTO>> registerFerst(@RequestBody DataDTO dataDTO) {
      try{
          crudService.register(dataDTO);
          return ResponseEntity.ok(new GenericResponse<>( "کاربر مورد نظر ثبت شد", "20", dataDTO));
      } catch (Exception e) {
          e.getMessage();
          return null;
      }

  }

}

