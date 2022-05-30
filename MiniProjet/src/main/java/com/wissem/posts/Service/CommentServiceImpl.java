package com.wissem.posts.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.wissem.posts.entities.Comment;
import com.wissem.posts.repos.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private CommentRepository CommentRepository;
	@Override
	public List<Comment> findAll() {
		return CommentRepository.findAll();
	}

	@Override
	public Comment saveComment(Comment c) {
		return CommentRepository.save(c);
	}

	@Override
	public Comment updateComment(Comment c) {
		return CommentRepository.save(c);
	}

	@Override
	public void deleteComment(Comment c) {
		CommentRepository.delete(c);
		
	}

	@Override
	public void deleteCommentById(Long idComment) {
		CommentRepository.deleteById(idComment);
		
	}

	@Override
	public Comment getComment(Long idComment) {
		return CommentRepository.findById(idComment).get();
	}

	@Override
	public Page<Comment> getAllCommentsParPage(int page, int size) {
		return CommentRepository.findAll(PageRequest.of(page, size));
	}
	
	

}
