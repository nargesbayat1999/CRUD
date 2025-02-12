package org.bayat.crud.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDTO {

    private String name;
    private String phone;

    public DataDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


}
