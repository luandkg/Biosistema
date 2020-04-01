package Biosistema.Organismos;

import java.awt.Color;

import Biosistema.Consumidor;


public class Carbox extends Consumidor {

	public Carbox(int eX, int eY) {
		super(eX, eY, "Carbox");

		this.setCor(Color.BLACK);
		this.setPodeMover(true);
		this.setVelocidade(1);
		this.setMover_Quantidade(20);
		this.setAlimentar("ALKA");
		this.setAlimentar("VELLOX");
this.LimitarVida(80000);
this.setReproducao(100, 50, 100);
this.setCapacidadeDeAlimentacao(2,50);

	}

}
