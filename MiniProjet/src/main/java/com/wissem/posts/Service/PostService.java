package com.wissem.posts.Service;

import java.util.List;



import org.springframework.data.domain.Page;

import com.wissem.posts.entities.*;


public interface PostService {
	Post savePost(Post p);

	List<Comment> getAllComments();

	Page<Post> getAllPostsParPage(int page, int size);

	Post updatePost(Post p);
	
	void deletePost(Post p);

	void deletePostById(Long id);

	Post getPost(Long id);

	List<Post> getAllPosts();

	List<Post> findByNomPost(String nom);

	List<Post> findByNomPostContains(String nom);

	List<Post> findByNomPrix(String nom, Double prix);

	List<Post> findByCategorie(Comment comment);

	List<Post> findByCategorieIdCat(Long id);

	List<Post> findByOrderByNomPostAsc();

	List<Post> trierPostsNomsPrix();

	List<Post> findBycommentaire( String textComment);


}
