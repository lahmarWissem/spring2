package com.wissem.posts.Service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wissem.posts.entities.Comment;
import com.wissem.posts.entities.Post;
import com.wissem.posts.repos.*;


@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostsRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public Post savePost(Post p) {
		return postRepository.save(p);
	}
	


	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}



	@Override
	public Page<Post> getAllPostsParPage(int page, int size) {
		return postRepository.findAll(PageRequest.of(page, size));
	}


	@Override
	public Post updatePost(Post p) {
		return postRepository.save(p);
	}



	@Override
	public void deletePost(Post p) {
		postRepository.delete(p);
		
	}



	@Override
	public void deletePostById(Long id) {
		postRepository.deleteById(id);
		
	}



	@Override
	public Post getPost(Long id) {
		return postRepository.findById(id).get();
	}



	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}



	@Override
	public List<Post> findByNomPost(String nom) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Post> findByNomPostContains(String nom) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Post> findByNomPrix(String nom, Double prix) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Post> findByCategorie(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Post> findByCategorieIdCat(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Post> findByOrderByNomPostAsc() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Post> trierPostsNomsPrix() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Post> findBycommentaire( String textComment) {
		return postRepository.findBycommentaire(textComment);
	}


	
	


}
