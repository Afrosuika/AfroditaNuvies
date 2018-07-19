package net.infobosccoma.projecte.afroditanuvies.model;

public class Vestit extends Element{

	@Override
	public String getImatgeurl() {
		return "/img/vestits/";
	}
	public Vestit() {

	}

	public Vestit(int id, String nom, String descripcio, String imatge) {
		super(id, nom, descripcio, imatge);
	}
}
