package org.bayat.crud.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name="DATA",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"phone"})
)
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String name;
    private  String phone;


}
