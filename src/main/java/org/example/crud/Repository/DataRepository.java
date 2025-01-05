package org.example.crud.Repository;

import org.example.crud.Entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DataRepository extends JpaRepository<Data, Integer> {


}
