package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.dto.*;
import com.pfeproject.EspritAcademy.Services.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This controller handles all HTTP requests for reclamations
// All endpoints start with /api/v1/auth
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class ReclamationController {

    // The service that does all the work
    @Autowired
    private ReclamationService reclamationService;

    // POST /addReclamation - Create a new reclamation
    @PostMapping("/addReclamation")
    public ReclamationResponse addReclamation(@RequestBody ReclamationRequest request) {
        return reclamationService.AddReclamation(request);
    }

    // GET /getReclam - Get all reclamations
    @GetMapping("/getReclam")
    public List<ReclamationResponse> getAll() {
        return reclamationService.GetAll();
    }

    // GET /getReclambyId/{id} - Get a single reclamation by ID
    @GetMapping("/getReclambyId/{id}")
    public ReclamationResponse getReclamById(@PathVariable Long id) {
        return reclamationService.getReclamById(id);
    }

    // GET /getnbReclam - Get total count of reclamations
    @GetMapping("/getnbReclam")
    public long getNbReclam() {
        return reclamationService.GetCount();
    }

    // PUT /updateStatus/{id} - Update the status of a reclamation
    @PutMapping("/updateStatus/{id}")
    public ReclamationResponse updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        return reclamationService.updateStatus(id, request.getStatus());
    }

    // DELETE /deleteReclam/{id} - Delete a reclamation
    @DeleteMapping("/deleteReclam/{id}")
    public ResponseEntity<String> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.ok("Deleted");
    }

    // POST /addComment/{id} - Add a comment to a reclamation
    @PostMapping("/addComment/{id}")
    public CommentResponse addComment(@PathVariable Long id, @RequestBody CommentRequest request) {
        return reclamationService.addComment(id, request.getAuthor(), request.getText());
    }

    // PUT /assignReclam/{id} - Assign a reclamation to someone
    @PutMapping("/assignReclam/{id}")
    public ReclamationResponse assignReclamation(@PathVariable Long id, @RequestBody AssignmentRequest request) {
        return reclamationService.assignReclamation(id, request.getAssignedTo());
    }

    // PUT /rateReclam/{id} - Rate the resolution of a reclamation
    @PutMapping("/rateReclam/{id}")
    public ReclamationResponse rateReclamation(@PathVariable Long id, @RequestBody RatingRequest request) {
        return reclamationService.rateReclamation(id, request.getRating());
    }

    // ============ NEW ENDPOINTS ============

    // PUT /updateReclam/{id} - Update a reclamation's details (title, description, etc.)
    @PutMapping("/updateReclam/{id}")
    public ReclamationResponse updateReclamation(@PathVariable Long id, @RequestBody ReclamationRequest request) {
        return reclamationService.updateReclamation(id, request);
    }

    // GET /getReclamByUser/{username} - Get all reclamations by a specific user
    @GetMapping("/getReclamByUser/{username}")
    public List<ReclamationResponse> getReclamationsByUser(@PathVariable String username) {
        return reclamationService.getReclamationsByUser(username);
    }

    // GET /searchReclam?q=query - Search reclamations by title or description
    @GetMapping("/searchReclam")
    public List<ReclamationResponse> searchReclamations(@RequestParam String q) {
        return reclamationService.searchReclamations(q);
    }

    // GET /getReclamByStatus/{status} - Get reclamations filtered by status
    @GetMapping("/getReclamByStatus/{status}")
    public List<ReclamationResponse> getReclamationsByStatus(@PathVariable String status) {
        return reclamationService.getReclamationsByStatus(status);
    }

    // GET /getReclamByCategory/{category} - Get reclamations filtered by category
    @GetMapping("/getReclamByCategory/{category}")
    public List<ReclamationResponse> getReclamationsByCategory(@PathVariable String category) {
        return reclamationService.getReclamationsByCategory(category);
    }

    // GET /getReclamByPriority/{priority} - Get reclamations filtered by priority
    @GetMapping("/getReclamByPriority/{priority}")
    public List<ReclamationResponse> getReclamationsByPriority(@PathVariable String priority) {
        return reclamationService.getReclamationsByPriority(priority);
    }
}
