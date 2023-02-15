package com.kameleon.task.quoteserver.config;

import com.kameleon.task.quoteserver.DAO.QuoteRepository;
import com.kameleon.task.quoteserver.DAO.UserRepository;
import com.kameleon.task.quoteserver.DAO.VoteRepository;
import com.kameleon.task.quoteserver.model.Quote;
import com.kameleon.task.quoteserver.model.User;
import com.kameleon.task.quoteserver.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ValuesConfigClass {
    private List<User> userList = new ArrayList<>();
    private List<Quote> quoteList = new ArrayList<>();
    private List<Vote> voteList = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostConstruct
    public void initDb() {

        for(int i = 0; i < 10; i++) {
            User user = User.builder()
                    .username("user" + i)
                    .password(passwordEncoder.encode("password" + i))
                    .email("email" + i)
                    .creationDate(new Date())
                    .build();
            userRepository.save(user);
            userList.add(user);
        }

        for(int k = 0; k < 10; k++){

            Vote vote = Vote.builder()
                    .user(userList.get(k))
                    .voteValue(k % 2 == 0)
                    .build();

            voteRepository.save(vote);
            voteList.add(vote);
        }

        for(int j = 0; j < 10; j++) {
            List<Vote> votes = new ArrayList<>();
            votes.add(voteList.get(j));
            Quote quote = Quote.builder()
                    .quoteValue("Quote" + j)
                    .creationDate(new Date())
                    .user(userList.get(j))
                    .voteList(votes)
                    .build();

            quoteRepository.save(quote);
        }
   }
}
