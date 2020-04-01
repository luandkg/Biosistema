package Biosistema;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import Biosistema.Organismos.Alka;
import Biosistema.Organismos.Carbox;
import Biosistema.Organismos.Unko;
import Biosistema.Organismos.Usgon;
import Biosistema.Organismos.Vellox;
import OmegaEngine.Windows;
import OmegaEngine.Cenarios.Cena;
import OmegaEngine.Estrutural.Vetor2;
import OmegaEngine.Utils.Cronometro;



public class Biosistema extends Cena {

	private Windows mWindows;
	private Ambiente mAmbiente;
	private Mapa mMapa;
	private ArrayList<Organismo> mOrganismos;

	private Cronometro mCron;
	private Cronometro mCron2SEG;

	private int LimiteX;
	private int LimiteY;;

	public Biosistema(String eNome, Windows eWindows) {
		super.setNome(eNome, eWindows.getLargura(), eWindows.getLargura());

		
		Biosistema_Testes ET = new Biosistema_Testes();
		
		
		
		
		
		mWindows = eWindows;

		mAmbiente = new Ambiente(mWindows);

		LimiteX = 150;
				LimiteY=(3 * 28);
		mMapa = new Mapa(LimiteX,LimiteY  + 2);
		mMapa.Zerar();

		mOrganismos = new ArrayList<Organismo>();

		mCron = new Cronometro(500);
		mCron2SEG = new Cronometro(2000);

		for (int i = 0; i < 15; i++) {

			Vetor2 P = ObterLugar();

			Unko UnkoC = new Unko(P.getX(), P.getY());

			mOrganismos.add(UnkoC);

		}

		for (int i = 0; i < 15; i++) {

			Vetor2 P = ObterLugar();

			Usgon UsgonC = new Usgon(P.getX(), P.getY());

			mOrganismos.add(UsgonC);

		}

		for (int i = 0; i < 5; i++) {

			Vetor2 P = ObterLugar();

			Alka AlkaC = new Alka(P.getX(), P.getY());

			mOrganismos.add(AlkaC);

		}

		for (int i = 0; i < 12; i++) {

			Vetor2 P = ObterLugar();

			Vellox VelloxC = new Vellox(P.getX(), P.getY());

			mOrganismos.add(VelloxC);

		}

		for (int i = 0; i < 5; i++) {

			Vetor2 P = ObterLugar();

			Carbox CarboxC = new Carbox(P.getX(), P.getY());

			mOrganismos.add(CarboxC);

		}

	}

	public Vetor2 ObterLugar() {
		Random Ale = new Random();

		int PX = 0;
		int PY = 0;

		boolean adicionado = false;

		while (adicionado == false) {

			PX = Ale.nextInt(150);
			PY = Ale.nextInt(3 * 20);

			if (LugarVazio(PX, PY, 150, (3 * 28) - 1)) {

				adicionado = true;
			}

		}

		return new Vetor2(PX, PY);

	}

	@Override
	public void iniciar() {

		mWindows.setTitle(this.getNome());

	}

	@Override
	public void update(double dt) {

		mCron.Esperar();
		mCron2SEG.Esperar();

		mMapa.Zerar();
		mMapa.MapearTudo(2);

		ArrayList<Organismo> Mortos = new ArrayList<Organismo>();

		for (Organismo OrganismoC : mOrganismos) {

			if (OrganismoC.EstaMorto()) {

				Mortos.add(OrganismoC);

			}

		}
		for (Organismo OrganismoMorto : Mortos) {
			mOrganismos.remove(OrganismoMorto);
			System.out.println("Removendo : " + OrganismoMorto.getEspecie());
		}

		ArrayList<Organismo> Novos = new ArrayList<Organismo>();

		if (mCron.Esperado()) {
			for (Organismo OrganismoC : mOrganismos) {

				if (OrganismoC.EstaVivo()){
					
					OrganismoC.ViverMais();
					
					if (OrganismoC.getOrganismoTipo()==OrganismoTipo.AUTOTROFICO) {
						Produtor ProdutorC = (Produtor) OrganismoC;
						Novos.addAll(ProdutorC.NascerDescentes(mOrganismos,LimiteX,LimiteY));

					}
					
					if (OrganismoC.getOrganismoTipo()==OrganismoTipo.HETEROTROFICO) {
						Consumidor ConsumidorC = (Consumidor) OrganismoC;
						Novos.addAll(ConsumidorC.NascerDescentes(mOrganismos,LimiteX,LimiteY));

					}
					
					
				if ( OrganismoC.getPodeMover()) {

					Consumidor OrganismoMovimentador = (Consumidor) OrganismoC;

					OrganismoMovimentador.Mover(mOrganismos, mMapa.getLargura(), mMapa.getAltura() - 2);

				}}

			}
		}

		mOrganismos.addAll(Novos);
		
		if (mCron2SEG.Esperado()) {

			System.out.println("ORGANISMOS : " + (getOrganismos().size()) + " AUTOTROFOS : " + (getAutotroficos().size()) + " HETEROTROFOS : " + (getHeterotroficos().size()));
		}
		/**
		 * System.out.println("ORG : " + getOrganismos().size());
		 * System.out.println("AUT : " + getAutotroficos().size());
		 * System.out.println("HET : " + getHeterotroficos().size());
		 * System.out.println("ESPECIES : " + getEspecies().size());
		 **/

	}

	@Override
	public void draw(Graphics g) {

		mWindows.Limpar(g);

		for (Organismo OrganismoC : mOrganismos) {

			OrganismoC.draw(g);
		}

		//mAmbiente.Gradear(g, 10);

	}

	public ArrayList<Organismo> getOrganismos() {
		return mOrganismos;
	}

	public ArrayList<Organismo> getAutotroficos() {
		ArrayList<Organismo> mLista = new ArrayList<Organismo>();

		for (Organismo OrganismoC : mOrganismos) {
			if (OrganismoC.getOrganismoTipo() == OrganismoTipo.AUTOTROFICO) {
				mLista.add(OrganismoC);
			}
		}

		return mLista;
	}

	public ArrayList<Organismo> getHeterotroficos() {
		ArrayList<Organismo> mLista = new ArrayList<Organismo>();

		for (Organismo OrganismoC : mOrganismos) {
			if (OrganismoC.getOrganismoTipo() == OrganismoTipo.HETEROTROFICO) {
				mLista.add(OrganismoC);
			}
		}

		return mLista;
	}

	public ArrayList<String> getEspecies() {
		ArrayList<String> mLista = new ArrayList<String>();

		for (Organismo OrganismoC : mOrganismos) {
			if (!mLista.contains(OrganismoC.getEspecie())) {
				mLista.add(OrganismoC.getEspecie());
			}
		}

		return mLista;
	}

	public boolean LugarVazio(int X, int Y, int mLX, int mLY) {

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
