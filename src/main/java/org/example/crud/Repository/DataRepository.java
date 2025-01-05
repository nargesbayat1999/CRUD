package org.example.crud.Repository;

import org.aspectj.apache.bcel.util.Repository;
import org.example.crud.Entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataRepository extends JpaRepository<Data,Integer>
{


}
