package net.infobosccoma.projecte.afroditanuvies.model;

import java.io.Serializable;

public class ConjuntMides implements Serializable {
	private float midaPit, midaMaluc, midaCintura, midaAltura;
	
	public ConjuntMides(){
		super();
	}

	public ConjuntMides(float midaPit, float midaMaluc, float midaCintura,
			float midaAltura) {
		super();
		this.midaPit = midaPit;
		this.midaMaluc = midaMaluc;
		this.midaCintura = midaCintura;
		this.midaAltura = midaAltura;
	}

	public float getMidaPit() {
		return midaPit;
	}

	public void setMidaPit(float midaPit) {
		this.midaPit = midaPit;
	}

	public float getMidaMaluc() {
		return midaMaluc;
	}

	public void setMidaMaluc(float midaMaluc) {
		this.midaMaluc = midaMaluc;
	}

	public float getMidaCintura() {
		return midaCintura;
	}

	public void setMidaCintura(float midaCintura) {
		this.midaCintura = midaCintura;
	}

	public float getMidaAltura() {
		return midaAltura;
	}

	public void setMidaAltura(float midaAltura) {
		this.midaAltura = midaAltura;
	}

	@Override
	public String toString() {
		return "ConjuntMides [midaPit=" + midaPit + ", midaMaluc=" + midaMaluc
				+ ", midaCintura=" + midaCintura + ", midaAltura=" + midaAltura
				+ "]";
	}
	

}
