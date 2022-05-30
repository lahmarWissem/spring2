package com.wissem.posts.controllers;

import java.text.ParseException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wissem.posts.Service.*;
import com.wissem.posts.entities.*;



@Controller
public class CommentController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping("/showCreateComment")
	public String showCreateCat(ModelMap modelMap)
	{
	modelMap.addAttribute("comments", new Comment());
	modelMap.addAttribute("mode", "new");
	return "formComments";
	}
	
	
	@RequestMapping("/ListeComments")
	public String ListeComments(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
	
	Page<Comment> coms = commentService.getAllCommentsParPage(page, size);
	modelMap.addAttribute("comments", coms);
	modelMap.addAttribute("pages", new int[coms.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	return "ListeComments";
	}
	
	@RequestMapping("/supprimerComment")
	public String supprimerComment(@RequestParam("id") Long id,
	 ModelMap modelMap)
	{ 
		commentService.deleteCommentById(id);
	List<Comment> coms = commentService.findAll();
	modelMap.addAttribute("comments", coms);
	return "ListeComments";
	}
	
	@RequestMapping("/saveComment")
	public String saveComment(@ModelAttribute("comment") Comment comment,ModelMap modelMap) throws ParseException 
	{
	Comment saveComment= commentService.saveComment(comment);
	return "redirect:/ListeComments";
	}
	


	
	@RequestMapping("/modifierComment")
	public String editerComment(@RequestParam("id") Long id,ModelMap modelMap)
	{
	Comment c= commentService.getComment(id);
	modelMap.addAttribute("comments", c);
	modelMap.addAttribute("mode", "edit");
	return "formComments";
	}
	@RequestMapping("/updateComment")
	public String updateComment(@ModelAttribute("comment") Comment comment,ModelMap modelMap) throws ParseException {
		commentService.updateComment(comment);
		 List<Comment> coms = commentService.findAll();
		 modelMap.addAttribute("comments", coms);
		return "ListeCat";
		}

}
