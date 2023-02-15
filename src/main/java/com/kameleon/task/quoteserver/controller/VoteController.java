package com.kameleon.task.quoteserver.controller;

import com.kameleon.task.quoteserver.model.Vote;
import com.kameleon.task.quoteserver.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoteController {
    @Autowired
    VoteService voteService;

    @GetMapping("/vote")
    public List<Vote> getAllVotes(){return voteService.getAllVotes();}

    @GetMapping("/vote/{id}")
    public Vote getVoteById(@PathVariable("id")Integer id){
        return voteService.getVoteById(id);
    }

    @PostMapping("/user/{userId}/quote/{quoteId}/vote")
    public void createVote(@PathVariable("userId") Integer userId,@PathVariable("quoteId") Integer quoteId,@RequestBody Vote vote){
        voteService.createVote(userId,quoteId,vote);
    }

    @PutMapping("/user/{userId}/quote/{quoteId}/vote")
    public void updateOrSaveVote(@PathVariable("userId") Integer userId,@PathVariable("quoteId") Integer quoteId,@RequestBody Vote vote){
        voteService.updateOrCreate(userId,quoteId,vote);
    }

//    @DeleteMapping("/vote/{id}")
//    public void deleteUser(@PathVariable("id") Integer id){
//        voteService.deleteVote(id);
//    }
}
