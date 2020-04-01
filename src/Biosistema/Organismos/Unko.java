package Biosistema.Organismos;

import java.awt.Color;

import Biosistema.Produtor;


public class Unko extends Produtor{

	public Unko(int eX, int eY) {
		super(eX, eY,"UNKO");

		this.setCor(Color.GREEN);
		this.TornarAdulto(12);
		this.setReproducao(10, 5, 10);
		this.LimitarVida(300);
	}
}
