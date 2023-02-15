package com.kameleon.task.quoteserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vote {
    @Id
    @Column(name = "vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @JsonIgnore
    @ManyToMany(mappedBy = "voteList")
    private List<Quote> quoteList;

    private Boolean voteValue;
}
