package com.kameleon.task.quoteserver.service;

import com.kameleon.task.quoteserver.DAO.QuoteRepository;
import com.kameleon.task.quoteserver.DAO.UserRepository;
import com.kameleon.task.quoteserver.model.Quote;
import com.kameleon.task.quoteserver.model.User;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    UserRepository userRepository;

    public List<Quote> getAllQuotes(){
        return  quoteRepository.findAll();
    }

    public Quote getQuoteById(Integer id){
        Optional<Quote> quoteOptional = quoteRepository.findById(id);
        return quoteOptional.orElse(null);
    }

    public void createQuote(Quote quote,Integer userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            quoteRepository.save(quote);
            if(user.getQuoteList() != null){
                user.getQuoteList().add(quote);
                userRepository.save(user);
            }else{
                List<Quote> quoteList = new ArrayList<>();
                quoteList.add(quote);
                user.setQuoteList(quoteList);
                userRepository.save(user);
            }
        }
    }

    public void updateOrCreate(Quote quote,Integer userId){
        if(quote.getId() != null){
            Optional<Quote> quoteOptional = quoteRepository.findById(quote.getId());
            if(quoteOptional.isPresent()){
                Quote tmpQuote = quoteOptional.get();
                tmpQuote.setQuoteValue(quote.getQuoteValue());
                tmpQuote.setCreationDate(new Date());
                quoteRepository.save(tmpQuote);
            }
        }else{
            this.createQuote(quote,userId);
        }
    }

    public void deleteQuote(Integer id){
        quoteRepository.deleteById(id);
    }

    public List<Quote> getTopTenQuotes(){
        List<Quote> listQuote = this.getAllQuotes();
        List<Quote> topTen = listQuote.stream()
                .peek(q -> q.quoteVotesValue())
                .sorted(Comparator.comparingInt(Quote::getQuoteVotes).reversed())
                .limit(10)
                .collect(Collectors.toList());
        return topTen;
    }

    public Quote getRandomQuote(){
        List<Quote> listQuote = this.getAllQuotes();
        if(listQuote.size() > 1){
            int size = listQuote.size(); ;
            return listQuote.get(getRandomNumber(size - 1));
        }
        return null;
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max - 0)) + 0);
    }
}
