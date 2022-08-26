package com.mobi7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobi7.model.Base;

public interface BaseRepository extends JpaRepository<Base, Long>{

	@Query("FROM Base WHERE nome = ?1")
	Base findByName(String nomeBase);

}
