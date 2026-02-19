package com.pfeproject.EspritAcademy.Repository;


import com.pfeproject.EspritAcademy.Entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {


}
