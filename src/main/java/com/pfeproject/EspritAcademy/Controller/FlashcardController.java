package com.pfeproject.EspritAcademy.Controller;
import com.pfeproject.EspritAcademy.Services.FlashcardService;
import com.pfeproject.EspritAcademy.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/flashcards")
@AllArgsConstructor
@Slf4j
public class FlashcardController {

    private final FlashcardService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlashcardResponse create( @RequestBody FlashcardCreateRequest req, Principal principal) {
        String ownerEmail = principal.getName(); // email/username from auth
        return service.create(ownerEmail, req);
    }

    @GetMapping
    public Page<FlashcardResponse> list(Pageable pageable, Principal principal) {
        String ownerEmail = principal.getName();
        return service.list(ownerEmail, pageable);
    }

    @GetMapping("/{id}")
    public FlashcardResponse get(@PathVariable Long id, Principal principal) {
        return service.get(principal.getName(), id);
    }

    @PutMapping("/{id}")
    public FlashcardResponse update(@PathVariable Long id,
                                     @RequestBody FlashcardUpdateRequest req,
                                    Principal principal) {
        return service.update(principal.getName(), id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Principal principal) {
        service.delete(principal.getName(), id);
    }
}
