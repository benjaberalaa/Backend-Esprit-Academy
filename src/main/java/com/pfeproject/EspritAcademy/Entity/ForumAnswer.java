package com.pfeproject.EspritAcademy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "forum_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private ForumQuestion question;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Integer votes = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean isAccepted = false;

    @ElementCollection
    @CollectionTable(name = "answer_upvotes", joinColumns = @JoinColumn(name = "answer_id"))
    @Column(name = "user_id")
    private Set<String> upvotedBy = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "answer_downvotes", joinColumns = @JoinColumn(name = "answer_id"))
    @Column(name = "user_id")
    private Set<String> downvotedBy = new HashSet<>();
}
