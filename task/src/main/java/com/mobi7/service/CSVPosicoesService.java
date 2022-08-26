package com.mobi7.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobi7.helper.CSVPosicoesHelper;
import com.mobi7.model.Posicoes;
import com.mobi7.model.Base;
import com.mobi7.repository.BaseRepository;
import com.mobi7.repository.PosicoesRepository;

@Service
public class CSVPosicoesService {

	@Autowired
	PosicoesRepository repository;
	
	@Autowired
	BaseRepository repositoryBase;

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

	public List<Posicoes> getAllPosicoes() {
		return repository.findAll();
	}

	public List<Integer> getPosicaoInsideArea(Base base, List<Posicoes> posicoes) {
		
		ArrayList<Instant> momentoInArea = new ArrayList<>();
		ArrayList<Integer> timeInArea = new ArrayList<>();
		
		for (int i = 0; i < posicoes.size(); i++) {
			double distanceInMeters = distance(
					Double.parseDouble(base.getLatitude()), 
					Double.parseDouble(base.getLongitude()), 
					Double.parseDouble(posicoes.get(i).getLatitude()), 
					Double.parseDouble(posicoes.get(i).getLongitude()));
			//System.out.println(distanceInMeters);
			boolean isWithin = distanceInMeters < base.getRaio();
			if(isWithin) {
				momentoInArea.add(posicoes.get(i).getData_posicao());
			}else {
				if(!momentoInArea.isEmpty()) {
					Duration timeLeft = Duration.between(momentoInArea.get(0), momentoInArea.get(momentoInArea.size()-1));
					int seconds = (int) (timeLeft.toMillis() / 1000L);
					timeInArea.add(seconds);
					momentoInArea.clear();
				}
			}
		}
		

		return timeInArea;
	}
	
	private double distance(double lat1, double lon1, double lat2, double lon2) {
		final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);
	}

	public List<Posicoes> getCarroByPlaca(String placa) {
		return repository.findByPlaca(placa);
	}
	
}
