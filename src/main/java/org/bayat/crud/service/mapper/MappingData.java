package org.bayat.crud.service.mapper;


import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.entity.About;
import org.bayat.crud.model.entity.Data;
import org.springframework.stereotype.Component;

@Component
public class MappingData {

    //Data to Dto
    public DataDTO dataToDataDTO(Data data) {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setName(data.getName());
        dataDTO.setPhone(data.getPhone());
        return dataDTO;
    }

    //Dto to Data
    public Data convertNewDataDTOtoData(DataDTO dataDTO) {
        Data data = new Data();
        data.setPhone(dataDTO.getPhone());
        data.setName(dataDTO.getName());
        return data;
    }

    public Data convertExistedDataDTOtoData(DataDTO dataDTO, Data data) {
        data.setName(dataDTO.getName());
        if (dataDTO.getNewPhoneNumber() != null) {
            data.setPhone(dataDTO.getNewPhoneNumber());
        }
        return data;
    }

    //Dto to About
    public About aboutDTOToData(DataDTO aboutDTO) {
        About about = new About();
        about.setAddress(aboutDTO.getAddress());
        return about;
    }

}

