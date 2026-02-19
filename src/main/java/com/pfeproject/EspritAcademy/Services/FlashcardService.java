package com.pfeproject.EspritAcademy.Services;



import com.pfeproject.EspritAcademy.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlashcardService {
    FlashcardResponse create(String ownerEmail, FlashcardCreateRequest req);
    Page<FlashcardResponse> list(String ownerEmail, Pageable pageable);
    FlashcardResponse get(String ownerEmail, Long id);
    FlashcardResponse update(String ownerEmail, Long id, FlashcardUpdateRequest req);
    void delete(String ownerEmail, Long id);
}
