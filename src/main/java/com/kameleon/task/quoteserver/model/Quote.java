package com.kameleon.task.quoteserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Quote {
    @Id
    @Column(name = "quote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String quoteValue;

    private Date creationDate;

    @Transient
    private int quoteVotes = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "quote_votes",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id"))
    private List<Vote> voteList;

    public int quoteVotesValue(){
        for(Vote vote : voteList){
            if(vote.getVoteValue()){
                quoteVotes++;
            }else{
                quoteVotes--;
            }
        }
        return quoteVotes;
    }
}
