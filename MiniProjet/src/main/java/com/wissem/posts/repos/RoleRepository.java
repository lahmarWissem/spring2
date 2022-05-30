package com.wissem.posts.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wissem.posts.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
