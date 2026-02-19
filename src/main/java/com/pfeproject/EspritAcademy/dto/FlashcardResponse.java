package com.pfeproject.EspritAcademy.dto;


import java.time.Instant;

public class FlashcardResponse {
    private Long id;
    private String front;
    private String back;
    private String category;
    private Boolean memorized;
    private Instant createdAt;
    private Instant updatedAt;

    public FlashcardResponse(
            Long id, String front, String back, String category, Boolean memorized,
            Instant createdAt, Instant updatedAt
    ) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.category = category;
        this.memorized = memorized;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getFront() { return front; }
    public String getBack() { return back; }
    public String getCategory() { return category; }
    public Boolean getMemorized() { return memorized; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
