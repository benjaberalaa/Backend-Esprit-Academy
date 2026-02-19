package com.pfeproject.EspritAcademy.Services.impl;

import com.pfeproject.EspritAcademy.Entity.ReclamationComment;
import com.pfeproject.EspritAcademy.Entity.reclamation;
import com.pfeproject.EspritAcademy.Exceptions.ReclamationNotFoundException;
import com.pfeproject.EspritAcademy.Repository.ReclamationCommentRepository;
import com.pfeproject.EspritAcademy.Repository.ReclamationRepository;
import com.pfeproject.EspritAcademy.Services.ReclamationService;
import com.pfeproject.EspritAcademy.dto.CommentResponse;
import com.pfeproject.EspritAcademy.dto.ReclamationRequest;
import com.pfeproject.EspritAcademy.dto.ReclamationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// This class implements all the reclamation operations
// It talks to the database through the repository
@Service
public class ReclamationServiceImpl implements ReclamationService {

    // Repository to access reclamation data in database
    @Autowired
    private ReclamationRepository reclamationRepository;

    // Repository to access comments in database
    @Autowired
    private ReclamationCommentRepository commentRepository;

    // Create a new reclamation
    @Override
    public ReclamationResponse AddReclamation(ReclamationRequest request) {
        // Create a new reclamation object
        reclamation reclam = new reclamation();
        reclam.setTitre(request.getTitre());
        reclam.setCategorie(request.getCategorie());
        reclam.setDescription(request.getDescription());
        reclam.setCreerPar(request.getCreerpar());
        reclam.setStatus("Open");
        reclam.setPriority(request.getPriority() != null ? request.getPriority() : "Medium");
        reclam.setCreatedAt(LocalDateTime.now());

        // Save to database
        reclamation saved = reclamationRepository.save(reclam);

        // Return the saved reclamation as a response
        return convertToResponse(saved);
    }

    // Get all reclamations from database
    @Override
    public List<ReclamationResponse> GetAll() {
        List<reclamation> reclamations = reclamationRepository.findAll();
        List<ReclamationResponse> responses = new ArrayList<>();

        // Convert each reclamation to a response object
        for (reclamation rec : reclamations) {
            responses.add(convertToResponse(rec));
        }

        return responses;
    }

    // Get a single reclamation by ID
    @Override
    public ReclamationResponse getReclamById(Long id) {
        reclamation reclam = findReclamationById(id);
        return convertToResponse(reclam);
    }

    // Get total count of reclamations
    @Override
    public long GetCount() {
        return reclamationRepository.count();
    }

    // Update the status of a reclamation
    @Override
    public ReclamationResponse updateStatus(Long id, String status) {
        reclamation reclam = findReclamationById(id);
        reclam.setStatus(status);
        reclamation updated = reclamationRepository.save(reclam);
        return convertToResponse(updated);
    }

    // Delete a reclamation
    @Override
    public void deleteReclamation(Long id) {
        reclamation reclam = findReclamationById(id);
        reclamationRepository.delete(reclam);
    }

    // Add a comment to a reclamation
    @Override
    public CommentResponse addComment(Long reclamationId, String author, String text) {
        reclamation reclam = findReclamationById(reclamationId);

        // Create new comment
        ReclamationComment comment = new ReclamationComment();
        comment.setAuthor(author);
        comment.setText(text);
        comment.setDate(LocalDateTime.now());
        comment.setReclamation(reclam);

        // Save comment
        ReclamationComment saved = commentRepository.save(comment);
        return convertCommentToResponse(saved);
    }

    // Assign a reclamation to someone
    @Override
    public ReclamationResponse assignReclamation(Long id, String assignedTo) {
        reclamation reclam = findReclamationById(id);
        reclam.setAssignedTo(assignedTo);
        reclamation updated = reclamationRepository.save(reclam);
        return convertToResponse(updated);
    }

    // Rate the resolution of a reclamation
    @Override
    public ReclamationResponse rateReclamation(Long id, Integer rating) {
        reclamation reclam = findReclamationById(id);
        reclam.setRating(rating);
        reclamation updated = reclamationRepository.save(reclam);
        return convertToResponse(updated);
    }

    // NEW: Update a reclamation's details
    @Override
    public ReclamationResponse updateReclamation(Long id, ReclamationRequest request) {
        reclamation reclam = findReclamationById(id);

        // Update only the fields that are provided
        if (request.getTitre() != null && !request.getTitre().isEmpty()) {
            reclam.setTitre(request.getTitre());
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            reclam.setDescription(request.getDescription());
        }
        if (request.getCategorie() != null && !request.getCategorie().isEmpty()) {
            reclam.setCategorie(request.getCategorie());
        }
        if (request.getPriority() != null && !request.getPriority().isEmpty()) {
            reclam.setPriority(request.getPriority());
        }

        reclamation updated = reclamationRepository.save(reclam);
        return convertToResponse(updated);
    }

    // NEW: Get all reclamations by a specific user
    @Override
    public List<ReclamationResponse> getReclamationsByUser(String username) {
        List<reclamation> reclamations = reclamationRepository.findByCreerPar(username);
        List<ReclamationResponse> responses = new ArrayList<>();

        for (reclamation rec : reclamations) {
            responses.add(convertToResponse(rec));
        }

        return responses;
    }

    // NEW: Search reclamations by title or description
    @Override
    public List<ReclamationResponse> searchReclamations(String query) {
        List<reclamation> reclamations = reclamationRepository.findByTitreContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        List<ReclamationResponse> responses = new ArrayList<>();

        for (reclamation rec : reclamations) {
            responses.add(convertToResponse(rec));
        }

        return responses;
    }

    // NEW: Get reclamations by status
    @Override
    public List<ReclamationResponse> getReclamationsByStatus(String status) {
        List<reclamation> reclamations = reclamationRepository.findByStatusIgnoreCase(status);
        List<ReclamationResponse> responses = new ArrayList<>();

        for (reclamation rec : reclamations) {
            responses.add(convertToResponse(rec));
        }

        return responses;
    }

    // NEW: Get reclamations by category
    @Override
    public List<ReclamationResponse> getReclamationsByCategory(String category) {
        List<reclamation> reclamations = reclamationRepository.findByCategorieIgnoreCase(category);
        List<ReclamationResponse> responses = new ArrayList<>();

        for (reclamation rec : reclamations) {
            responses.add(convertToResponse(rec));
        }

        return responses;
    }

    // NEW: Get reclamations by priority
    @Override
    public List<ReclamationResponse> getReclamationsByPriority(String priority) {
        List<reclamation> reclamations = reclamationRepository.findByPriorityIgnoreCase(priority);
        List<ReclamationResponse> responses = new ArrayList<>();

        for (reclamation rec : reclamations) {
            responses.add(convertToResponse(rec));
        }

        return responses;
    }

    // Helper method to find a reclamation by ID
    // Throws an error if not found
    private reclamation findReclamationById(Long id) {
        Optional<reclamation> optionalReclamation = reclamationRepository.findById(id);
        return optionalReclamation.orElseThrow(() ->
            new ReclamationNotFoundException("Reclamation non trouvee pour l'ID : " + id));
    }

    // Helper method to convert entity to response DTO
    private ReclamationResponse convertToResponse(reclamation reclam) {
        ReclamationResponse response = new ReclamationResponse();
        response.setId(reclam.getId());
        response.setTitre(reclam.getTitre());
        response.setCategorie(reclam.getCategorie());
        response.setDescription(reclam.getDescription());
        response.setCreerpar(reclam.getCreerPar());
        response.setStatus(reclam.getStatus());
        response.setPriority(reclam.getPriority());
        response.setAssignedTo(reclam.getAssignedTo());
        response.setRating(reclam.getRating());
        response.setCreatedAt(reclam.getCreatedAt());

        // Convert comments
        List<CommentResponse> commentResponses = new ArrayList<>();
        if (reclam.getComments() != null) {
            for (ReclamationComment comment : reclam.getComments()) {
                commentResponses.add(convertCommentToResponse(comment));
            }
        }
        response.setComments(commentResponses);

        return response;
    }

    // Helper method to convert comment entity to response DTO
    private CommentResponse convertCommentToResponse(ReclamationComment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setAuthor(comment.getAuthor());
        response.setText(comment.getText());
        response.setDate(comment.getDate());
        return response;
    }
}
