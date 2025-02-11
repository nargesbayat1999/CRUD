package org.bayat.crud.view.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDTO {

    private long id;
    private String name;
    private String phone;

    public DataDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
