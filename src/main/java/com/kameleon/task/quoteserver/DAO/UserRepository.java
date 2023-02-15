package com.kameleon.task.quoteserver.DAO;

import com.kameleon.task.quoteserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
