package Biosistema.Componentes;

import OmegaEngine.Componentes.Componente;

public class Reproducao extends Componente {

	public static final String ID = "Reproducao";

	private int mReproducao_Intervalo;
	private int mReproducao_Ciclo;
	private int mReproducao_CicloContagem;
	private int mReproducao_Duracao;

	private int mDescentesGerados;
	
	public Reproducao(int eIntervalo, int eCiclo, int eDuracao) {
		super(ID);

		mReproducao_Intervalo = eIntervalo;
		mReproducao_Ciclo = eCiclo;
		mReproducao_Duracao = eDuracao;
		mReproducao_CicloContagem = 0;

	}

	public void setReproducao(int eIntervalo, int eCiclo, int eDuracao) {
		mReproducao_Intervalo = eIntervalo;
		mReproducao_Ciclo = eCiclo;
		mReproducao_Duracao = eDuracao;
	}

	public int Reproducao_Intervalo() {
		return mReproducao_Intervalo;
	}

	public int Reproducao_Ciclo() {
		return mReproducao_Ciclo;
	}

	public int Reproducao_Duracao() {
		return mReproducao_Duracao;
	}

	public int getDescentesGerados() {
		return mDescentesGerados;
	}

	public void DescentesGerados_Zerar() {
		mDescentesGerados = 0;
	}
	
	
	public void Reproduzindo() {
		
		mReproducao_Intervalo += 1;
		if (mReproducao_Intervalo >= mReproducao_Ciclo) {
			mReproducao_CicloContagem += 1;

			// System.out.println(this.getEspecie() + " Iniciando Ciclo de Reproducao..." +
			// mReproducao_CicloContagem);

			if (mReproducao_CicloContagem >= mReproducao_Duracao) {

				// System.out.println(
				// this.getEspecie() + " Finalizando Ciclo de Reproducao..." +
				// mReproducao_CicloContagem);

				mReproducao_Intervalo = 0;
				mReproducao_CicloContagem = 0;

				this.mDescentesGerados = 1;

			}
		}
		
	}

}
