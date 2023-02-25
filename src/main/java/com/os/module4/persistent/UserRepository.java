package com.os.module4.persistent;

import com.os.module4.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query("{'sport.sportName' : ?0}")
    List<User> findBySportName(String sportName);
}
