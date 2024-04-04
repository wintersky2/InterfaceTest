package com.personal.example.domain.data.repository;

import com.personal.example.domain.data.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data,Long> {
}
