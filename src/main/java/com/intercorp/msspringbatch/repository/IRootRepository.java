package com.intercorp.msspringbatch.repository;

import com.intercorp.msspringbatch.models.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRootRepository extends JpaRepository<Root, Long> {

    //

}
