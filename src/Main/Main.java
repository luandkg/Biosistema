package Main;

import AssetContainer.AssetContainer;
import AssetContainer.ImagePack;
import Biosistema.Biosistema;
import OmegaEngine.Windows;
import OmegaEngine.Utils.Debugger;
import OmegaEngine.Utils.Local;
import Constantes.Constantes;

public class Main {

	public static void main(String[] args) {

		Debugger.NaoDebugar();
		Debugger.Debugar();

		Debugger.Info("Game Engine - AOmega 1.0  ::  DESENVOLVEDOR : LUAN FREITAS ");

		AssetContainer mAssetContainer = new AssetContainer();
		mAssetContainer.Montar(Local.LocalAtualCom("res/Assets"));
		mAssetContainer.AplicarPrefixo("/res/", "Res::");

		ImagePack Aplicativo = new ImagePack(mAssetContainer.getRes("Res::Aplicativo.png"));

		Windows mWindows = new Windows("AOmega 1.0", (Constantes.QUADRANTE * 50) - 2, (Constantes.QUADRANTE * 30) - 22);
		mWindows.setIconImage(Aplicativo.getImage());

	
		int EcossistemaID = mWindows.CriarCenario(new Biosistema("Ecossistema", mWindows));

		mWindows.setCenario(EcossistemaID);

		Thread mThread = new Thread(mWindows);
		mThread.start();

	
	}

}
