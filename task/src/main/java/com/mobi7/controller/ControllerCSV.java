package com.mobi7.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mobi7.helper.CSVPosicoesHelper;
import com.mobi7.helper.CSVBaseHelper;
import com.mobi7.message.ResponseMessage;
import com.mobi7.model.Posicoes;
import com.mobi7.model.Base;
import com.mobi7.service.CSVBaseService;
import com.mobi7.service.CSVPosicoesService;

@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/csv")
public class ControllerCSV {

	@Autowired
	CSVPosicoesService filePosicoesService;
	
	@Autowired
	CSVBaseService fileBaseService;

	@PostMapping("/uploadPosicoes")
	public ResponseEntity<ResponseMessage> uploadPosicoes(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVPosicoesHelper.hasCSVFormat(file)) {
			try {
				filePosicoesService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = e.getMessage();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	@PostMapping("/uploadBase")
	public ResponseEntity<ResponseMessage> uploadBase(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVBaseHelper.hasCSVFormat(file)) {
			try {
				fileBaseService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = e.getMessage();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/listposicoes")
	public ResponseEntity<List<Posicoes>> getAllPosicoes() {
		try {
			List<Posicoes> posicoes = filePosicoesService.getAllPosicoes();

			if (posicoes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(posicoes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/posicaoinarea")
	public ResponseEntity<List<Integer>> getPosicao(@RequestParam("area") String area, 
			@RequestParam("placa") String placa) {
		try {
			
			Base base = fileBaseService.getBasesByName(area);
			List<Posicoes> posicoes = filePosicoesService.getCarroByPlaca(placa);
			
			List<Integer> momentoIn = filePosicoesService.getPosicaoInsideArea(base, posicoes);

			if (posicoes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(momentoIn, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listbases")
	public ResponseEntity<List<Base>> getAllBases() {
		try {
			List<Base> base = fileBaseService.getAllBases();

			if (base.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(base, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getbasebyname")
	public ResponseEntity<Base> getBasesByArea(@RequestParam("area") String area) {
		try {
			Base base = fileBaseService.getBasesByName(area);

			if (base == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(base, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getcarrobyplaca")
	public ResponseEntity<List<Posicoes>> getPosicoesByPlaca(@RequestParam("placa") String placa) {
		try {
			List<Posicoes> posicao = filePosicoesService.getCarroByPlaca(placa);

			if (posicao == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(posicao, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
