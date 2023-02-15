package com.kameleon.task.quoteserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.hibernate.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quote_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
    private Date creationDate;

    @JsonBackReference
    @OneToMany(cascade = {CascadeType.ALL},mappedBy="user")
    private List<Quote> quoteList;


    @JsonBackReference
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "user")
    private List<Vote> voteList;
}
