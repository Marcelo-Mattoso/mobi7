package com.mobi7.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobi7.helper.CSVPosicoesHelper;
import com.mobi7.model.Posicoes;
import com.mobi7.repository.PosicoesRepository;

@Service
public class CSVService {

	@Autowired
	PosicoesRepository repository;

	public void save(MultipartFile file) {
		try {
			List<Posicoes> posicoes = CSVPosicoesHelper.csvToDatabase(file.getInputStream());
			repository.saveAll(posicoes);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public ByteArrayInputStream load() {
		List<Posicoes> posicoes = repository.findAll();

		ByteArrayInputStream in = CSVPosicoesHelper.databaseToCSV(posicoes);
		return in;
	}

	public List<Posicoes> getAllTutorials() {
		return repository.findAll();
	}

}
