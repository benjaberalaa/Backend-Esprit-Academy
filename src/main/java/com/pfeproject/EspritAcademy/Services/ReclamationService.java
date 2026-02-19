package com.pfeproject.EspritAcademy.Services;

import com.pfeproject.EspritAcademy.dto.CommentResponse;
import com.pfeproject.EspritAcademy.dto.ReclamationRequest;
import com.pfeproject.EspritAcademy.dto.ReclamationResponse;

import java.util.List;

// This interface defines all the operations we can do with reclamations
// The implementation is in ReclamationServiceImpl
public interface ReclamationService {

    // Create a new reclamation
    ReclamationResponse AddReclamation(ReclamationRequest request);

    // Get all reclamations
    List<ReclamationResponse> GetAll();

    // Get a single reclamation by its ID
    ReclamationResponse getReclamById(Long id);

    // Get total count of reclamations
    long GetCount();

    // Update the status of a reclamation (Open, In Progress, Resolved, Closed)
    ReclamationResponse updateStatus(Long id, String status);

    // Delete a reclamation
    void deleteReclamation(Long id);

    // Add a comment to a reclamation
    CommentResponse addComment(Long reclamationId, String author, String text);

    // Assign a reclamation to someone
    ReclamationResponse assignReclamation(Long id, String assignedTo);

    // Rate the resolution of a reclamation (1-5 stars)
    ReclamationResponse rateReclamation(Long id, Integer rating);

    // NEW: Update a reclamation's details (title, description, category, priority)
    ReclamationResponse updateReclamation(Long id, ReclamationRequest request);

    // NEW: Get all reclamations created by a specific user
    List<ReclamationResponse> getReclamationsByUser(String username);

    // NEW: Search reclamations by title or description
    List<ReclamationResponse> searchReclamations(String query);

    // NEW: Get reclamations filtered by status
    List<ReclamationResponse> getReclamationsByStatus(String status);

    // NEW: Get reclamations filtered by category
    List<ReclamationResponse> getReclamationsByCategory(String category);

    // NEW: Get reclamations filtered by priority
    List<ReclamationResponse> getReclamationsByPriority(String priority);
}
