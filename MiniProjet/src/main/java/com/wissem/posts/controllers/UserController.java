package com.wissem.posts.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wissem.posts.Service.PostService;
import com.wissem.posts.Service.RoleService;
import com.wissem.posts.Service.UsersService;
import com.wissem.posts.entities.Role;
import com.wissem.posts.entities.User;

@Controller
public class UserController {
	@Autowired
	RoleService roleService;
	@Autowired
	UsersService usersService;

	@RequestMapping("/saveRole")
	public String saveRole(@ModelAttribute("role") Role role, ModelMap modelMap) throws ParseException {
		Role saveRole = roleService.saveRole(role);
		return "redirect:/ListeUsers";
	}
	
	@RequestMapping("/showCreateUser")
	public String showCreateUser(ModelMap modelMap) {
		List<Role> roles = roleService.findAll();
		modelMap.addAttribute("roles", roles);
		modelMap.addAttribute("users", new User());
		modelMap.addAttribute("mode", "new");
		return "FormUser";
	}

	@RequestMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user, ModelMap modelMap) throws ParseException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder() ;
		String encodedPassword = encoder. encode (user.getPassword());
		user.setPassword(encodedPassword);
		 usersService.saveUser(user);
		return "redirect:/ListeUsers";
	}

	@RequestMapping("/ListeUsers")
	public String ListeUser(ModelMap modelMap) {
		List<User> users = usersService.findAll();
		modelMap.addAttribute("users", users);
		return "ListeUsers";
	}

	@RequestMapping("/supprimerUser")
	public String supprimerUser(@RequestParam("id") Long id, ModelMap modelMap) {
		usersService.deleteUserById(id);
		List<User> roles = usersService.findAll();
		modelMap.addAttribute("users", roles);
		return "ListeUsers";
	}

	@RequestMapping("/modifierUser")
	public String modifierUser(@RequestParam("id") Long id, ModelMap modelMap) {
		User u = usersService.getUser(id);
		List<Role> role = roleService.findAll();
		modelMap.addAttribute("roles", role);
		modelMap.addAttribute("users", u);
		modelMap.addAttribute("mode", "edit");
		return "FormUser";
	}

	@RequestMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user,ModelMap modelMap) throws ParseException {
		usersService.updateUser(user);
		 List<User> roles = usersService.findAll();
		 modelMap.addAttribute("users", roles);
		return "ListeUsers";
	}
}
