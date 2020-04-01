package Biosistema;

public enum OrganismoTipo {

	AUTOTROFICO(1), HETEROTROFICO(2), DESCONHECIDO(3);

	private int mValor;

	OrganismoTipo(int eValor) {
		mValor = eValor;
	}

	public int getValor() {
		return mValor;
	}

	@Override
	public String toString() {
		if (mValor == 1) {
			return "AUTOTROFICO";
		} else if (mValor == 2) {
			return "HETEROTROFICO";
		} else {
			return "DESCONHECIDO";
		}
	}
}
