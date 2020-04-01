package Biosistema.Organismos;

import java.awt.Color;

import Biosistema.Consumidor;

public class Vellox extends Consumidor {

	public Vellox(int eX, int eY) {
		super(eX, eY, "VELLOX");

		this.setCor(Color.RED);
		this.setPodeMover(true);
		;
		this.setMover_Quantidade(50);
		this.setVelocidade(2);
		this.setAlimentar("UNKO");
		this.setCapacidadeDeAlimentacao(30,50);
		
		this.LimitarVida(60000);
		this.setReproducao(200, 50, 100);

	}

}
