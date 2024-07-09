package org.java.spring_test7.db.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 36, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true )
    private String content;

    private int likeCount;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public Post(){

    }

    public Post(String title, String Content, int likeCount, User user){
        setId(id);
        setTitle(title);
        setContent(Content);
        setLikeCount(likeCount);
        setUser(user);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null || title.length() < 3) {
            throw new IllegalArgumentException("Hai inserito un titolo non accettato");
        }
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikeCount(int likeCount) throws IllegalArgumentException {
        if (getLikeCount() < 0) {
            throw new IllegalArgumentException("questo campo non può essere negativo");
        }
        this.likeCount = likeCount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "post id: [" + getId() + "]" + " | titolo: " + getTitle() + " | content: " + getContent() + " | like count: " + getLikeCount() + " | user associato: " + getUser();
    }
}
