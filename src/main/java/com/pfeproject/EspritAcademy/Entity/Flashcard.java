package com.pfeproject.EspritAcademy.Entity;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name = "flashcards", indexes = {
        @Index(name = "idx_flashcard_owner_email", columnList = "ownerEmail")
})

public class Flashcard {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 200)
        private String front;

        @Column(nullable = false, length = 2000)
        private String back;

        @Column(nullable = false, length = 100)
        private String category;

        @Column(nullable = false)
        private Boolean memorized = false;

        @Column(nullable = false, length = 254)
        private String ownerEmail; // connected user email

        @Column(nullable = false, updatable = false)
        private Instant createdAt = Instant.now();

        @Column(nullable = false)
        private Instant updatedAt = Instant.now();

        @PreUpdate
        public void preUpdate() {
            this.updatedAt = Instant.now();
        }

        // getters/setters
        public Long getId() { return id; }
        public String getFront() { return front; }
        public void setFront(String front) { this.front = front; }
        public String getBack() { return back; }
        public void setBack(String back) { this.back = back; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public Boolean getMemorized() { return memorized; }
        public void setMemorized(Boolean memorized) { this.memorized = memorized; }
        public String getOwnerEmail() { return ownerEmail; }
        public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
        public Instant getCreatedAt() { return createdAt; }
        public Instant getUpdatedAt() { return updatedAt; }

}
