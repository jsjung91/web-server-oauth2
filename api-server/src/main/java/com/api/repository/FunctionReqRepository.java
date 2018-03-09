package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.FunctionReqModel;

@Repository
public interface FunctionReqRepository extends JpaRepository<FunctionReqModel, Integer>{

}