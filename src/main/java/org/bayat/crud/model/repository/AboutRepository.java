package org.bayat.crud.model.repository;

import org.bayat.crud.model.entity.About;
import org.bayat.crud.model.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AboutRepository extends JpaRepository<About, Long> {
    List<About> findByData(Data data);
}
