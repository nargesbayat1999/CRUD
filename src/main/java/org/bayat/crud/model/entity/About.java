package org.bayat.crud.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "data_id")
    @PrimaryKeyJoinColumn(referencedColumnName = "id")
    private Data data;

}
