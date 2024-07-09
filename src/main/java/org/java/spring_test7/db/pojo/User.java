package org.java.spring_test7.db.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64, nullable = false)
    private String realName;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Column(length = 6, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(String realName, String username, String password) {
        this.realName = realName;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) throws IllegalArgumentException {
        if (realName.length() < 3 || realName.isEmpty()) {
            throw new IllegalArgumentException("Hai inserito una stringa vuota, o non valida");
        }
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if (username.length() < 3 || username.isEmpty()) {
            throw new IllegalArgumentException("Hai inserito un username non valido");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if (password.length() < 3 || password.isEmpty()) {
            throw new IllegalArgumentException("Hai inserito una password non valida");
        }
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "[" + getId() + "]" + " | real name: " + getRealName() + " | username: " + getUsername() + " | password: " + getPassword();
    }
}
