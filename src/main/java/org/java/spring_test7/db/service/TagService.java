package org.java.spring_test7.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_test7.db.pojo.Tag;
import org.java.spring_test7.db.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TagService {

    @Autowired
    TagRepo tr;


    public List<Tag> getAll() {
        return tr.findAll();
    }


    public Optional<Tag> findById(int id) {
        return tr.findById(id);
    }


    @Transactional
    public Optional<Tag> findByIdWithPosts(int id) {
        Optional<Tag> optTag = tr.findById(id);

        if (optTag.isEmpty())
            return Optional.empty();

        Tag tag = optTag.get();
        Hibernate.initialize(tag.getPosts());

        return Optional.of(tag);
    }


    public void save(Tag tag) {
        tr.save(tag);
    }


    public void delete(Tag tag) {
        tr.delete(tag);
    }

}
