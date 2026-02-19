package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.CoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<CoursEntity, Long> {


}
