package Biosistema.Organismos;

import java.awt.Color;

import Biosistema.Consumidor;


public class Alka extends Consumidor {

	public Alka(int eX, int eY) {
		super(eX, eY, "ALKA");

		this.setCor(Color.ORANGE);
		this.setPodeMover(true);
		this.setVelocidade(1);
		this.setMover_Quantidade(20);
		this.setAlimentar("USGON");
		this.LimitarVida(80000);
		this.setReproducao(100, 50, 10);

	}

}
