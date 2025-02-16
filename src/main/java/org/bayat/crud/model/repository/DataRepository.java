package org.bayat.crud.model.repository;

import org.bayat.crud.model.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    Optional<Data> findByPhone(String phone);


}
