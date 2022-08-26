package com.mobi7.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobi7.helper.CSVBaseHelper;
import com.mobi7.model.Base;
import com.mobi7.repository.BaseRepository;

@Service
public class CSVBaseService {

	@Autowired
	BaseRepository repository;

	public void save(MultipartFile file) {
		try {
			List<Base> base = CSVBaseHelper.csvToDatabase(file.getInputStream());
			repository.saveAll(base);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public ByteArrayInputStream load() {
		List<Base> base = repository.findAll();

		ByteArrayInputStream in = CSVBaseHelper.databaseToCSV(base);
		return in;
	}

	public List<Base> getAllBases() {
		return repository.findAll();
	}

	public Base getBasesByName(String area) {
		return repository.findByName(area);
	}

}
