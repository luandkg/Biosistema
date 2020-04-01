package Biosistema;

public enum Fases {

	JOVEM(1), ADULTO(2), VELHO(3);

	private int mValor;

	Fases(int eValor) {
		mValor = eValor;
	}

	public int getValor() {
		return mValor;
	}

	@Override
	public String toString() {
		if (mValor == 1) {
			return "JOVEM";
		} else if (mValor == 2) {
			return "ADULTO";
		} else if (mValor == 3) {
			return "VELHO";
		} else {
			return "DESCONHECIDO";
		}
	}
}
