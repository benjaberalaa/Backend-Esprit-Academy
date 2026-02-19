package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.Role;
import com.pfeproject.EspritAcademy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


     Optional<User> findUserByEmail(String email);

     User findByEmail(String email);

     List<User> findUsersByRole(Role role);

     @Query("SELECT COUNT(e) FROM User e WHERE e.role = :role")
     long countByRole(Role role);


     @Query("SELECT u.id FROM User u WHERE u.firstname = :firstname")
     Long findIdByFirstname(@Param("firstname") String firstname);

}
