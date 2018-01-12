package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.ComputeModel;

@Repository
public interface ComputeRepository extends JpaRepository<ComputeModel, Integer>{

}