package net.infobosccoma.projecte.afroditanuvies.model;

public class Teixit extends Element{
	
	@Override
	public String toString() {
		return "Teixit [getImatgeurl()=" + getImatgeurl() + ", getId()="
				+ getId() + ", getNom()=" + getNom() + ", getDescripcio()="
				+ getDescripcio() + ", getImatge()=" + getImatge()
				+ ", getImatgethumburl()=" + getImatgethumburl()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	@Override
	public String getImatgeurl() {
		return "/img/teixits/";
	}
	public Teixit() {

	}

	public Teixit(int id, String nom, String descripcio, String imatge) {
		super(id, nom, descripcio, imatge);
	}
}
