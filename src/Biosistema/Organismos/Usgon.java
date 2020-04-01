package Biosistema.Organismos;

import java.awt.Color;

import Biosistema.Produtor;

public class Usgon extends Produtor{

	public Usgon(int eX, int eY) {
		super(eX, eY,"USGON");

		this.setCor(Color.BLUE);
		this.TornarAdulto(100);
		this.setReproducao(30, 5, 10);
		this.LimitarVida(800);

	}
}
