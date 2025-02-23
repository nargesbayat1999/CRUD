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
//    @Size(min=2, max=50)
    private  String name;
//    @NotNull
    private  String phone;


}
