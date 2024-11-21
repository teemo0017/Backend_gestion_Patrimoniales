package com.api.crud.repositories;

import com.api.crud.models.BienesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBienesRepository extends JpaRepository<BienesModel, Long> {
}
