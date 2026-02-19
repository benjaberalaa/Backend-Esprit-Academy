package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// This interface handles all database operations for reclamations
// Spring Data JPA automatically creates the SQL queries based on method names
@Repository
public interface ReclamationRepository extends JpaRepository<reclamation, Long> {

    // Find all reclamations created by a specific user
    List<reclamation> findByCreerPar(String creerpar);

    // Search by title or description (case insensitive)
    List<reclamation> findByTitreContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String titre, String description);

    // Find by status (case insensitive)
    List<reclamation> findByStatusIgnoreCase(String status);

    // Find by category (case insensitive)
    List<reclamation> findByCategorieIgnoreCase(String categorie);

    // Find by priority (case insensitive)
    List<reclamation> findByPriorityIgnoreCase(String priority);

    // Count by status
    long countByStatusIgnoreCase(String status);

    // Count by category
    long countByCategorieIgnoreCase(String categorie);

    // Count by priority
    long countByPriorityIgnoreCase(String priority);
}
