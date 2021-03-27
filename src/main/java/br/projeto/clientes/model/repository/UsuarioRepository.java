package br.projeto.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.projeto.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
