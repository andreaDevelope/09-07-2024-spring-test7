package org.java.spring_test7.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_test7.db.pojo.Post;
import org.java.spring_test7.db.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepo pr;

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

    @Transactional
    public Optional<Post> getByIdWithTags(int id) {

        Optional<Post> optPost = getPostById(id);

        if (optPost.isEmpty())
            return Optional.empty();

        Post post = optPost.get();
        Hibernate.initialize(post.getTags());

        return Optional.of(post);
    }

}
