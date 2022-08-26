package com.mobi7.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base")
public class Base {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "nome")
	private String nome; 
	
	@Column(name = "raio")
	private Integer raio; 
	
	@Column(name = "longitude")
	private String longitude; 
	
	@Column(name = "latitude")
	private String latitude;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		nome = nome;
	}

	public Integer getRaio() {
		return raio;
	}

	public void setRaio(Integer raio) {
		this.raio = raio;
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
	
	

	public Base() {
		super();
	}

	public Base(String nome, Integer raio, String longitude, String latitude) {
		this.nome = nome;
		this.raio = raio;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Base [id=" + id + ", Nome=" + nome + ", raio=" + raio + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}
	
}