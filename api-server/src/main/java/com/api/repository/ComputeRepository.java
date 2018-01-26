package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.model.ComputeModel;

@Repository
public interface ComputeRepository extends JpaRepository<ComputeModel, Integer>{

	@Query("select i from ComputeModel i where i.i_teamname = ?1")
	ComputeModel findByTeamname(String i_teamname);
}