package Biosistema;

import java.util.ArrayList;
import java.util.Random;

public class Produtor extends Organismo {

	

	public Produtor(int eX, int eY, String eEspecie) {
		super(eX, eY, OrganismoTipo.AUTOTROFICO, eEspecie);

		

	}

	
	public ArrayList<Organismo> NascerDescentes(ArrayList<Organismo> mOrganismos, int mLX, int mLY) {
		ArrayList<Organismo> Novos = new ArrayList<Organismo>();

		if (getDescentesGerados() > 0) {
			for (int iD = 0; iD < getDescentesGerados(); iD++) {

				Random Ale = new Random();
				int d = Ale.nextInt(8);

				int PX = this.getPos().getX();
				int PY = this.getPos().getY();

				if (d == 0) {
					PX -= 1;
				}
				if (d == 1) {
					PX += 1;
				}
				if (d == 2) {
					PY -= 1;
				}
				if (d == 3) {
					PY += 1;
				}

				// Diagonal
				if (d == 4) {
					PY -= 1;
					PX -= 1;
				}
				if (d == 5) {
					PY -= 1;
					PX += 1;
				}
				if (d == 6) {
					PY += 1;
					PX -= 1;
				}
				if (d == 7) {
					PY += 1;
					PX += 1;
				}

				if (LugarVazio(mOrganismos, PX, PY, mLX, mLY)) {

					Produtor Novo = new Produtor(PX, PY, this.getEspecie());
					Novo.setReproducao(Reproducao_Intervalo(), Reproducao_Ciclo(), Reproducao_Duracao());
					Novo.setCor(this.getCor());
					Novo.LimitarVida(this.LimiteDeVida());
					Novos.add(Novo);
					// System.out.println("Reproduzindo-se");

				} else {

				//	System.out.println("Abortando reprodução ... ");

				}

			}

		}
		DescentesGerados_Zerar();

		return Novos;
	}

	public boolean LugarVazio(ArrayList<Organismo> mOrganismos, int X, int Y, int mLX, int mLY) {

		boolean enc = true;

		if (X >= 0 && Y >= 0 && X < mLX && Y < mLY) {

			for (Organismo OrganismoC : mOrganismos) {

				if (OrganismoC.getPos().getX() == X && OrganismoC.getPos().getY() == Y) {

					enc = false;
					break;
				}

			}

		} else {
			enc = false;
		}

		return enc;
	}

	
}
