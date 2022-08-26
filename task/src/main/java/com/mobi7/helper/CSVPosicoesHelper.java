package com.mobi7.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

import org.springframework.web.multipart.MultipartFile;

import com.mobi7.model.Posicoes;

public class CSVPosicoesHelper {

	public static String TYPE = "text/csv";
	 static String[] HEADERs = { "Id", "Placa", "Data", "Velocdade", "Longitude", "Latitude", "Ignicao" };
	
	
	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Posicoes> csvToDatabase(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Posicoes> posicoes = new ArrayList<Posicoes>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				//Wed Dec 12 2018 00:04:03 GMT-0200 (Hora oficial do Brasil)
			    Date date = null;
				try {
					date = new SimpleDateFormat("MMM dd YYYY HH:mm:ss", Locale.ENGLISH).parse(
							csvRecord.get("data_posicao").substring(4).split("GMT")[0].trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			    
				Posicoes posicao = new Posicoes(
						csvRecord.get("placa"), 
						date.toInstant(), 
						Integer.parseInt(csvRecord.get("velocidade")), 
						csvRecord.get("longitude"),
						csvRecord.get("latitude"), 
						Boolean.parseBoolean(csvRecord.get("ignicao")));

				posicoes.add(posicao);
			}

			return posicoes;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream databaseToCSV(List<Posicoes> posicoes) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (Posicoes posicao : posicoes) {
				List<String> data = Arrays.asList(
						posicao.getPlaca(), 
						String.valueOf(posicao.getData_posicao()), 
						String.valueOf(posicao.getVelocidade()),
						posicao.getLongitude(),
						posicao.getLatitude(), 
						String.valueOf(posicao.isIgnicao()));
				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

}
