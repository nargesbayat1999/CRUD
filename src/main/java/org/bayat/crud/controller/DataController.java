package org.bayat.crud.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
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

    @Operation(summary = "Get data by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DataDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = @Content)})
    @GetMapping(value = "/getData",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> getData(@RequestParam(name = "id") long id) {
        try {
            log.debug(Message.GETDATA_SERVICE_CALL.getMessage());
            return crudService.findById(id);

        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(), e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "edit data or add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add data or edit",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DataDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = @Content)})
    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> edit(@Valid @RequestBody DataDTO dataDTO) {
        try {
            log.debug(Message.Edit_SERVICE_CALL.getMessage());

            return crudService.edit(dataDTO);
        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(), e.getMessage());
            return ResponseEntity.internalServerError().build();

        }
    }


    @Operation(summary = "delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = @Content)})
    @DeleteMapping(value = "/delete",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> delete(@RequestParam(name = "id") long id) {
        try {
            log.debug(Message.DELETED_SERVICE_CALL.getMessage());
            return crudService.delete(id);
        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(), e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @Operation(summary = "edit phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "phone number",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DataDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = @Content)})
    @PatchMapping(value = "/editPhone", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> update(@Valid@RequestBody DataDTO dataDTO) {
        try {
            log.debug(Message.EDIT_PHONE_SERVICE_CALL.getMessage());
            return crudService.update(dataDTO);
        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(), e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "insertdata")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "insert data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DataDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = @Content)})
    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<DataDTO>> insert(@Valid@RequestBody DataDTO dataDTO) {
        try {
            log.debug(Message.REGISTER_SERVICE_CALL.getMessage());
            return crudService.insert(dataDTO);
        } catch (Exception e) {
            log.error(Message.UNKNOWN_ERROR_IS.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

}

