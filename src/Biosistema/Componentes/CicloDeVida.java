package Biosistema.Componentes;

import Biosistema.Fases;
import OmegaEngine.Componentes.Componente;

public class CicloDeVida extends Componente {

	public static final String ID = "CicloDeVida";

	private boolean mVivo;
	protected int mCiclos;
	private int mAdulto;
	private int mLimiteDeVida;
	private Fases mFase;

	public CicloDeVida() {
		super(ID);

		mFase = Fases.JOVEM;

		mLimiteDeVida = 0;
		mCiclos = 0;
		mVivo = true;

	}

	public boolean EstaVivo() {
		return mVivo;
	}

	public boolean EstaMorto() {
		return !mVivo;
	}

	public void Morrer() {
		mVivo = false;
	}

	public int getCiclos() {
		return mCiclos;
	}

	public Fases getFase() {
		return mFase;
	}

	public void setFase(Fases eFase) {
		mFase = eFase;
	}

	public void TornarAdulto(int eAdulto) {
		mAdulto = eAdulto;
	}

	public void LimitarVida(int eLimiteDeVida) {
		mLimiteDeVida = eLimiteDeVida;
	}

	public int getAdulto() {
		return mAdulto;
	}

	public int LimiteDeVida() {
		return mLimiteDeVida;
	}

	public void Vivendo() {

		mCiclos += 1;

		if (this.getCiclos() >= mAdulto) {
			if (this.getFase() == Fases.JOVEM) {
				this.setFase(Fases.ADULTO);
				// System.out.println(this.getEspecie() + " Ficando Adulto");
			}

		}

		if (this.getCiclos() >= mLimiteDeVida) {

			this.Morrer();

		}

	}

}
