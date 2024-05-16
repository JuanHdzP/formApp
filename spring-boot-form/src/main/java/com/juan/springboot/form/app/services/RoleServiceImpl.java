package com.juan.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juan.springboot.form.app.models.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private List<Role> lista;

	public RoleServiceImpl() {
		this.lista = new ArrayList<>();
		this.lista.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		this.lista.add(new Role(2, "Usuario", "ROLE_USER"));
		this.lista.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
	}

	@Override
	public List<Role> listar() {
		return lista;
	}

	@Override
	public Role getRolePorId(Integer id) {
		Role resultado = null;
		for (Role role : lista) {
			if (id == role.getId()) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}

}
