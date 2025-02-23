package org.bayat.crud.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DataDTO {

    @Size(min=2, max=50)
    private String name;

    @NotNull
    private String phone;

    public DataDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


}
