package com.project.ordercartAPI.repository;

import com.project.ordercartAPI.model.Iten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItenRepository extends JpaRepository<Iten, Long> {
}
