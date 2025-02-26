package org.bayat.crud.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DataDTO {

    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    @Pattern(regexp = "^0\\d{10}$", message = "شماره تلفن باید 11 رقم باشد و با 0 شروع شود.")

    private String phone;

    private String newPhoneNumber;

    private String address;

}
