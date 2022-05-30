package com.wissem.posts.Service;

import java.util.List;

import com.wissem.posts.entities.Role;


public interface RoleService {
	 List <Role> findAll();
	    
	 Role saveRole(Role r);
	 Role updateRole(Role r);
	 Role getRole (Long idRole);
}
