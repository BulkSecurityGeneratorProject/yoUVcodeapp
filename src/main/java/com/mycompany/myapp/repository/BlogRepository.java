package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Blog;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Blog entity.
 */
@SuppressWarnings("unused")
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("select blog from Blog blog where blog.created_by.login = ?#{principal.username}")
    List<Blog> findByCreated_byIsCurrentUser();

    @Query("select blog from Blog blog where blog.last_chng_by.login = ?#{principal.username}")
    List<Blog> findByLast_chng_byIsCurrentUser();

}
