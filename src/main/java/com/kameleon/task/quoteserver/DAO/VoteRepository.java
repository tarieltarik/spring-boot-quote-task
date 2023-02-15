package com.kameleon.task.quoteserver.DAO;

import com.kameleon.task.quoteserver.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Integer> {
}
