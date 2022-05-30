package com.wissem.posts.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.wissem.posts.entities.Comment;


public interface CommentService {

	
	List<Comment> findAll();

	Comment saveComment(Comment c);

	Comment updateComment(Comment c);

	void deleteComment(Comment c);

	void deleteCommentById(Long id);

	Comment getComment(Long idComment);

	Page<Comment> getAllCommentsParPage(int page, int size);
		
}
