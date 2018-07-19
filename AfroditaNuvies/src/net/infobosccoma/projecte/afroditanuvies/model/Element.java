package net.infobosccoma.projecte.afroditanuvies.model;

import java.io.Serializable;

abstract public class Element implements Serializable{
	
	
	private int id;
	private String nom, descripcio,imatge;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	
	public Element(){
		
	}
	public Element(int id, String nom, String descripcio, String imatge) {
		super();
		this.id = id;
		this.nom = nom;
		this.descripcio = descripcio;
		this.imatge = imatge;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getImatge() {
		return imatge;
	}
	public void setImatge(String imatge) {
		this.imatge = imatge;
	}
	abstract String getImatgeurl();
	
	public String getImatgethumburl() {
		return getImatgeurl()+"thumbnails/";
	}

}
