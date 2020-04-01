package Biosistema;

import java.util.ArrayList;

import LuanDKG.LuanDKG;
import LuanDKG.Pacote;

public class Mapa {

	private int mLargura;
	private int mAltura;

	private int[][] mMapa;

	public Mapa(int eLargura, int eAltura) {

		mLargura = eLargura;
		mAltura = eAltura;

		mMapa = new int[mLargura][mAltura];

		Zerar();

	}

	public int getLargura() {
		return mLargura;
	}

	public int getAltura() {
		return mAltura;
	}

	public void set(int eX, int eY, int eValor) {

		mMapa[eX][eY] = eValor;

	}

	public void setCondicao(int eX, int eY, int eValor) {

		if (eX >= 0 && eX < mLargura) {

			if (eY >= 0 && eY < mAltura) {

				mMapa[eX][eY] = eValor;

			}

		}

	}

	public int get(int eX, int eY) {

		return mMapa[eX][eY];

	}

	public void Zerar() {

		for (int x = 0; x < mLargura; x++) {

			for (int y = 0; y < mAltura; y++) {

				mMapa[x][y] = 0;

			}

		}

	}

	public void MapearTudo(int eValor) {

		for (int x = 0; x < mLargura; x++) {

			for (int y = 0; y < mAltura; y++) {

				mMapa[x][y] = eValor;

			}

		}

	}

	public void SalvarArquivo(String eArquivo) {

		LuanDKG ASP = new LuanDKG();

		Pacote P = ASP.UnicoPacote("MAPA");

		String Conteudo = "";

		for (int x = 0; x < mLargura; x++) {

			for (int y = 0; y < mAltura; y++) {

				Conteudo += String.valueOf(mMapa[x][y]);

			}

		}

		//MRDG Ver = new MRDG();

		P.Identifique("Largura", String.valueOf(mLargura));
		P.Identifique("Altura", String.valueOf(mAltura));
		P.Identifique("Versao", "1.0");
		P.Identifique("Autor", "Luan Freitas");
		P.Identifique("DDC", "2020 03 15");
		P.Identifique("DDA", "2020 03 16");

		// P.Identifique("Conteudo",Conteudo);

		ArrayList<String> Clusters = getClusters(Conteudo, mLargura);

		P.Identifique("Clusters", String.valueOf(Clusters.size()));

		int CID = 0;
		for (String eCluster : Clusters) {

			P.Identifique("Cluster_" + String.valueOf(CID), eCluster);
			CID += 1;
		}

		ASP.Salvar(eArquivo);

	}

	public ArrayList<String> getClusters(String eConteudo, int eTamanho) {
		ArrayList<String> Clusters = new ArrayList<String>();

		int i = 0;
		int o = eConteudo.length();
		int e = 0;

		String SubConteudo = "";

		while (i < o) {
			char l = eConteudo.charAt(i);

			if (e >= eTamanho) {
				e = 0;
				Clusters.add(SubConteudo);
				SubConteudo = String.valueOf(l);
			} else {
				SubConteudo += String.valueOf(l);
			}

			e += 1;
			i += 1;
		}

		if (SubConteudo.length() > 0) {
			Clusters.add(SubConteudo);
		}

		return Clusters;
	}

}
