package com.wissem.posts.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wissem.posts.entities.Post;
@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

	
	
	

	@Query("select p from Post p, Comment c where p.comment.idComment = c.idComment and c.textComment like %:textComment%")
	List<Post> findBycommentaire(@Param("textComment") String textComment);
	

}