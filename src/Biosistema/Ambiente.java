package Biosistema;

import java.awt.Color;
import java.awt.Graphics;

import OmegaEngine.Windows;


public class Ambiente {

	private Windows mWindows;

	public Ambiente(Windows eWindows) {

		mWindows = eWindows;

	}

	public void AmbienteColor(Graphics g, Color eCor, float eLumem) {

		int alpha = (int) (255.0f * eLumem);

		if (alpha < 0) {
			alpha = 0;
		}
		if (alpha > 255) {
			alpha = 255;
		}

		Color mCor = (new Color(eCor.getRed(), eCor.getGreen(), eCor.getBlue(), alpha));
		g.setColor(mCor);

		g.fillRect(0, 0, mWindows.getLargura(), mWindows.getAltura());
	}

	public void Gradear(Graphics g, int eTamanho) {

		g.setColor(Color.BLACK);

		int eColunas = ((mWindows.getLargura()) / eTamanho) + 1;
		int eLinhas = ((mWindows.getAltura()) / eTamanho) + 1;

		for (int c = 0; c < eColunas; c++) {
			g.drawLine((c * eTamanho), 0, (c * eTamanho), mWindows.getAltura());
		}

		for (int l = 0; l < eLinhas; l++) {
			g.drawLine(0, (l * eTamanho), mWindows.getLargura(), (l * eTamanho));
		}

	}

}
