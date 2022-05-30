package com.wissem.posts.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wissem.posts.Service.*;
import com.wissem.posts.entities.*;
import com.wissem.posts.repos.*;



@Controller
public class PostsController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;

	
	@RequestMapping("/savePost")
	public String savePost(@Valid Post posts, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
			return "formposts";
		  postService.savePost(posts);
		return "redirect:/ListePosts";
	}
	
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		List<Comment> coms = postService.getAllComments();
		Post pos = new Post();
		Comment com = new Comment();
		com = coms.get(0); // prendre la première catégorie de la liste
		pos.setComment(com); // affedter une catégorie par défaut au posts pour éviter le pb avec une				// catégorie null
		modelMap.addAttribute("posts", pos);
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("comments", coms);
		return "/formposts";
	}
	
	
	@RequestMapping("/ListePosts")
	public String ListePosts(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "4") int size) {
		Page<Post> pos = postService.getAllPostsParPage(page, size);
		modelMap.addAttribute("posts", pos);
		modelMap.addAttribute("pages", new int[pos.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "/ListePosts";
	}
	
	
	@RequestMapping("/supprimerPost")
	public String supprimerPost(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		postService.deletePostById(id);
		Page<Post> pos = postService.getAllPostsParPage(page, size);
		modelMap.addAttribute("posts", pos);
		modelMap.addAttribute("pages", new int[pos.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "ListePosts";
	}

	@RequestMapping("/modifierPost")
	public String modifierPost(@RequestParam("id") Long id, ModelMap modelMap) {
		Post p = postService.getPost(id);
	
	  List<Comment> cats = commentService.findAll();
	   cats.remove(p.getComment());
		modelMap.addAttribute("comments", cats);
		modelMap.addAttribute("posts", p);
		modelMap.addAttribute("mode", "edit");
		return "formposts";
	}
	
	@RequestMapping("/SearchPost")
	public String SearchPost(ModelMap modelMap, @Valid String textComment) {
		List<Post> pos = postService.findBycommentaire(textComment);

		modelMap.addAttribute("posts", pos);

		return "/recherchePost";
	}

	@RequestMapping("/updatePost")
	public String updatePost(@ModelAttribute("posts") Post posts, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date CreationDate = dateformat.parse(String.valueOf(date));
		posts.setCreationDate(CreationDate);
		postService.updatePost(posts);
		List<Post> pos = postService.getAllPosts();
		modelMap.addAttribute("produits", pos);

		return "ListePosts";
	}
	
}
