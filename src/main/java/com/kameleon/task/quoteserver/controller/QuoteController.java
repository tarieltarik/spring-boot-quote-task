package com.kameleon.task.quoteserver.controller;

import com.kameleon.task.quoteserver.model.Quote;
import com.kameleon.task.quoteserver.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @GetMapping("/quote")
    public List<Quote> getAllQuote(){return quoteService.getAllQuotes();}

    @GetMapping("/quote/{id}")
    public Quote getQuote(@PathVariable("id")Integer id){
        return quoteService.getQuoteById(id);
    }

    @PostMapping("/user/{userId}/quote")
    public void createQuote(@RequestBody Quote quote,@PathVariable("userId") Integer userId){
        quoteService.createQuote(quote,userId);
    }

    @PutMapping("/user/{userId}/quote")
    public void updateUser(@RequestBody Quote quote,@PathVariable("userId") Integer userId){
        quoteService.updateOrCreate(quote,userId);
    }

    @DeleteMapping("/quote/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        quoteService.deleteQuote(id);
    }

    @GetMapping("/quote/top/ten")
    public List<Quote> getTopTenQuote(){
        return quoteService.getTopTenQuotes();
    }

    @GetMapping("/quote/random")
    public Quote getRandomQuote(){
        return quoteService.getRandomQuote();
    }
}
