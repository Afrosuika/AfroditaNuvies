package net.infobosccoma.projecte.afroditanuvies.model;

public class Coleccio extends Element {

	@Override
	public String getImatgeurl() {
		return "/img/coleccions/";
	}

	public Coleccio() {

	}

	public Coleccio(int id, String nom, String descripcio, String imatge) {
		super(id, nom, descripcio, imatge);
	}
}
