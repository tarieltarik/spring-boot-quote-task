package com.kameleon.task.quoteserver.DAO;

import com.kameleon.task.quoteserver.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Integer> {
}
