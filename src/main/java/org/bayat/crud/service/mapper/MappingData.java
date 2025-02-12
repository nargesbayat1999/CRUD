package org.bayat.crud.service.mapper;


import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.Data;
import org.springframework.stereotype.Component;

@Component
public class MappingData {


    //Data to Dto
    public DataDTO DataToDataDto(Data data) {
        DataDTO dataDTO = new DataDTO(data.getName(), data.getPhone());
        return dataDTO;
    }

    //Dto to Data
    public Data DataDtoTodata(DataDTO dataDTO) {
        Data data = new Data();
        data.setPhone(dataDTO.getPhone());
        data.setName(dataDTO.getName());
        return data;
    }

}

