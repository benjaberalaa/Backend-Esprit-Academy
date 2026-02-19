package com.pfeproject.EspritAcademy.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "forum_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String author;

    @ElementCollection
    @CollectionTable(name = "question_tags", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    @Column(nullable = false)
    private Integer votes = 0;

    @Column(nullable = false)
    private Integer views = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ForumAnswer> answers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "question_upvotes", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "user_id")
    private Set<String> upvotedBy = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "question_downvotes", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "user_id")
    private Set<String> downvotedBy = new HashSet<>();

}
