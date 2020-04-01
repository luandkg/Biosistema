package Biosistema;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import Biosistema.Componentes.CicloDeVida;
import Biosistema.Componentes.Reproducao;
import OmegaEngine.Componentes.Entidade;
import OmegaEngine.Componentes.Posicao;
import OmegaEngine.Componentes.Tamanho;


public class Organismo extends Entidade {

	private Posicao mPosicao;
	private Tamanho mTamanho;

	private Color mCor;
	private String mEspecie;
	private boolean mMovimento;

	private OrganismoTipo mOrganismoTipo;

	private Reproducao mReproducao;
	private CicloDeVida mCicloDeVida;

	public Organismo(int eX, int eY, OrganismoTipo eOrganismoTipo, String eEspecie) {

		mPosicao = (Posicao) this.adicionarEObterComponente(new Posicao(eX, eY));
		mTamanho = (Tamanho) this.adicionarEObterComponente(new Tamanho(10, 10));

		mReproducao = (Reproducao) this.adicionarEObterComponente(new Reproducao(0, 0, 0));
		mCicloDeVida = (CicloDeVida) this.adicionarEObterComponente(new CicloDeVida());

		mCor = Color.black;
		mOrganismoTipo = eOrganismoTipo;
		mEspecie = eEspecie;
		mMovimento = false;

	}

	public Posicao getPos() {
		return mPosicao;
	}

	public Color getCor() {
		return mCor;
	}

	public void setCor(Color eCor) {
		mCor = eCor;
	}

	public OrganismoTipo getOrganismoTipo() {
		return mOrganismoTipo;
	}

	public String getEspecie() {
		return mEspecie;
	}

	public boolean getPodeMover() {
		return mMovimento;
	}

	public void setPodeMover(boolean eMovimento) {
		mMovimento = eMovimento;
	}

	public boolean EstaVivo() {
		return mCicloDeVida.EstaVivo();
	}

	public boolean EstaMorto() {
		return mCicloDeVida.EstaMorto();
	}

	public void Morrer() {
		mCicloDeVida.Morrer();
	}

	protected void ViverMais() {

		mCicloDeVida.Vivendo();

		if (mCicloDeVida.EstaVivo() && mCicloDeVida.getFase() == Fases.ADULTO) {

			mReproducao.Reproduzindo();

		}

	}

	public int getCiclos() {
		return mCicloDeVida.getCiclos();
	}

	public Fases getFase() {
		return mCicloDeVida.getFase();
	}

	public void setFase(Fases eFase) {
		mCicloDeVida.setFase(eFase);
	}

	public void TornarAdulto(int eAdulto) {
		mCicloDeVida.TornarAdulto(eAdulto);
	}

	public void LimitarVida(int eLimiteDeVida) {
		mCicloDeVida.LimitarVida(eLimiteDeVida);
	}

	public int getAdulto() {
		return mCicloDeVida.getAdulto();
	}

	public int LimiteDeVida() {
		return mCicloDeVida.LimiteDeVida();
	}

	public void setReproducao(int eIntervalo, int eCiclo, int eDuracao) {
		mReproducao.setReproducao(eIntervalo, eCiclo, eDuracao);
	}

	public int Reproducao_Intervalo() {
		return mReproducao.Reproducao_Intervalo();
	}

	public int Reproducao_Ciclo() {
		return mReproducao.Reproducao_Ciclo();
	}

	public int Reproducao_Duracao() {
		return mReproducao.Reproducao_Duracao();
	}

	public int getDescentesGerados() {
		return mReproducao.getDescentesGerados();
	}

	public void DescentesGerados_Zerar() {
		mReproducao.DescentesGerados_Zerar();
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(mCor);

		g.fillRect(mPosicao.getX() * 10, (mPosicao.getY() + 3) * 10, mTamanho.getLargura(), mTamanho.getAltura());

	}

}
