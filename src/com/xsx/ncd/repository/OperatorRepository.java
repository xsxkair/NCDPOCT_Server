package com.xsx.ncd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Integer>{
	
	public List<Operator> findByNameAndDepartment(String name, Department department);
	
	public List<Operator> findByDepartment(Department department);
	
	@Query("SELECT o FROM Operator o where o.id in (:ids) ")
	public List<Operator> findByids(@Param("ids") List<Integer> ids);
	
	public Operator findByDepartmentAndName(Department department, String name);
}
