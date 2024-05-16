package com.juan.springboot.form.app.services;

import java.util.List;

import com.juan.springboot.form.app.models.domain.Role;

public interface RoleService {

	public List<Role> listar();
	public Role getRolePorId(Integer id);
}
