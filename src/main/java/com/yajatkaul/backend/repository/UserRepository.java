package com.yajatkaul.backend.repository;

import com.yajatkaul.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
