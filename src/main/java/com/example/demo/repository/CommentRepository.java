package com.example.demo.repository;

import com.example.demo.mongoEntity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {
    public List<Comment>  findByPid(String aid);
}
