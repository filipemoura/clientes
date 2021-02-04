package br.projeto.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.projeto.clientes.model.entity.Cliente;

public interface ServicoRepository extends JpaRepository<Cliente, Integer>{

}
