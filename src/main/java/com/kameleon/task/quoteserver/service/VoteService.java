package com.kameleon.task.quoteserver.service;

import com.kameleon.task.quoteserver.DAO.QuoteRepository;
import com.kameleon.task.quoteserver.DAO.UserRepository;
import com.kameleon.task.quoteserver.DAO.VoteRepository;
import com.kameleon.task.quoteserver.model.Quote;
import com.kameleon.task.quoteserver.model.User;
import com.kameleon.task.quoteserver.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VoteService {
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuoteRepository quoteRepository;

    public List<Vote> getAllVotes(){
        return  voteRepository.findAll();
    }

    public Vote getVoteById(Integer id){
        Optional<Vote> voteOptional = voteRepository.findById(id);
        return voteOptional.orElse(null);
    }

    public void createVote(Integer userId, Integer quoteId,Vote vote){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Quote> quoteOptional = quoteRepository.findById(quoteId);
        if(userOptional.isPresent() && quoteOptional.isPresent()){
            User user = userOptional.get();
            Quote quote = quoteOptional.get();
            vote.setUser(user);
            if(vote.getQuoteList() != null){
                vote.getQuoteList().add(quote);
            }else{
                List<Quote> tmpQuoteList = new ArrayList<>();
                tmpQuoteList.add(quote);
            }
            voteRepository.save(vote);
        }
    }

    public void updateOrCreate(Integer userId,Integer quoteId,Vote vote){
        if(vote.getId() != null){
            Optional<Vote> voteOptional = voteRepository.findById(vote.getId());
            if(voteOptional.isPresent()){
                Vote tmpVote = voteOptional.get();
                tmpVote.setVoteValue(vote.getVoteValue());
                voteRepository.save(tmpVote);
            }
        }else{
            this.createVote(userId,quoteId,vote);
        }
    }

    public void deleteVote(Integer id){
        voteRepository.deleteById(id);
    }
}
