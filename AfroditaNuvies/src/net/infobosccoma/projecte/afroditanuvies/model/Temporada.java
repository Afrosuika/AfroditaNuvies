package net.infobosccoma.projecte.afroditanuvies.model;

public class Temporada extends Element{

	@Override
	public String getImatgeurl() {
		return "/img/temporades/";
	}
	public Temporada() {

	}

	public Temporada(int id, String nom, String descripcio, String imatge) {
		super(id, nom, descripcio, imatge);
	}
}
