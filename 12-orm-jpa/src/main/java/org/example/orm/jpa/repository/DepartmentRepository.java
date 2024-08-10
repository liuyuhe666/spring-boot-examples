package org.example.orm.jpa.repository;

import org.example.orm.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    /**
     * 根据层级查询部门
     *
     * @param level 层级
     * @return 部门列表
     */
    List<Department> findDepartmentsByLevels(Integer level);
}
