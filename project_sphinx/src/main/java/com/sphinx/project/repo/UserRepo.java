package com.sphinx.project.repo;


import com.sphinx.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, UserRepoCustom {

    User findByUsername(String username);

    User findUserById(Integer id);

    @Query(value = "SELECT * FROM USER WHERE active = :status LIMIT :size OFFSET :page", nativeQuery = true)
    List<User> findAllByStatus(@Param("status") Integer status,@Param("size") Integer size, @Param("page") Integer page);
}
