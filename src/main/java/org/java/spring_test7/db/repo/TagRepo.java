package org.java.spring_test7.db.repo;

import org.java.spring_test7.db.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer>{

}
