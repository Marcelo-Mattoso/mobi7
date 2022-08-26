package com.mobi7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobi7.model.Posicoes;

public interface PosicoesRepository extends JpaRepository<Posicoes, Long>{

	@Query("FROM Posicoes WHERE placa = ?1")
	List<Posicoes> findByPlaca(String placa);

}
