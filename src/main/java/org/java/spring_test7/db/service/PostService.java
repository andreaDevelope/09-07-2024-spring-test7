package org.java.spring_test7.db.service;

import java.util.List;
import java.util.Optional;

import org.java.spring_test7.db.pojo.Post;
import org.java.spring_test7.db.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepo pr;

    public List<Post> getAll(){
        return pr.findAll();
    }

    public Optional<Post> getPostById(int id){
        return pr.findById(id);
    }

    public void save(Post p){
        pr.save(p);
    }

    public void delete(Post p){
        pr.delete(p);
    }

}
