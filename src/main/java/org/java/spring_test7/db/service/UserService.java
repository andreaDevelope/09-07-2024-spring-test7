package org.java.spring_test7.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_test7.db.pojo.Post;
import org.java.spring_test7.db.pojo.User;
import org.java.spring_test7.db.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private  UserRepo ur;

    @Autowired
    private PostService pos;

    public List<User> getAll() {
        return ur.findAll();
    }

    public Optional<User> getUserById(int id) {
        return ur.findById(id);
    }

    public void save(User u) {
        ur.save(u);
    }

    public void delete(User u) {
        ur.delete(u);
    }

    // PER STAMPARE TUTTI I POST ASSOCIATI AGLI USER
    @Transactional
    public List<User> getAllUsersPosts() {
        List<User> users = ur.findAll();

        for (User user : users) {
            Hibernate.initialize(user.getPosts());
        }

        return users;
    }

    // PER OTTENERE I POST ASSOCIATI A UN USER
    @Transactional
    public Optional<User> getUserPostById(int id) {
        Optional<User> optUsers = getUserById(id);
    
        if (optUsers.isEmpty())
            return Optional.empty();
    
        Hibernate.initialize(optUsers.get().getPosts());
    
        return optUsers;
    }

    // PER ELIMINARE L'UTENTE E TUTTI I POST ASSOCIATI
    @Transactional
    public void deleteUserAndPosts(int userId) {
        Optional<User> optUser = getUserById(userId);
        if (optUser.isPresent()) {
            User userWithId = optUser.get();
            // Elimina tutti i post associati all'utente
            for (Post post : userWithId.getPosts()) {
                pos.delete(post);
            }
            // Elimina l'utente
            ur.delete(userWithId);
        }
    }
}
