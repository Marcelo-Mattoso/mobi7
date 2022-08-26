package com.mobi7.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posicoes")
public class Posicoes {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "placa")
	private String placa; 
	
	@Column(name = "data_posicao")
	private Instant data_posicao; 
	
	@Column(name = "velocidade")
	private Integer velocidade; 
	
	@Column(name = "longitude")
	private String longitude; 
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "ignicao")
	private boolean ignicao;
	
	
	public Posicoes() {
		super();
	}

	public Posicoes(String placa, Instant data_posicao, Integer velocidade, String longitude,
			String latitude, boolean ignicao) {
		this.placa = placa;
		this.data_posicao = data_posicao;
		this.velocidade = velocidade;
		this.longitude = longitude;
		this.latitude = latitude;
		this.ignicao = ignicao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Instant getData_posicao() {
		return data_posicao;
	}

	public void setData_posicao(Instant data_posicao) {
		this.data_posicao = data_posicao;
	}

	public Integer getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public boolean isIgnicao() {
		return ignicao;
	}

	public void setIgnicao(boolean ignicao) {
		this.ignicao = ignicao;
	}

	@Override
	public String toString() {
		return "Posicoes [id=" + id + ", placa=" + placa + ", data_posicao=" + data_posicao + ", velocidade="
				+ velocidade + ", longitude=" + longitude + ", latitude=" + latitude + ", ignicao=" + ignicao + "]";
	}
	
	

}
