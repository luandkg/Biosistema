package Biosistema;

import java.util.ArrayList;
import java.util.Random;

public class Consumidor extends Organismo {

	private int mMover_Dir = -1;
	private int mMover_Contagem = -1;
	private int mMover_Maximo = -1;
	private int mMover_Quantidade = -1;

	private int mVelocidade = 0;

	private ArrayList<String> mAlimentacao;
	private int mCapacidadeDeAlimentacao;
	private int mAlimentando;
	private int mAlimentandoAndandoMaximo;
	private int mAlimentandoAndando;

	public Consumidor(int eX, int eY, String eEspecie) {
		super(eX, eY, OrganismoTipo.HETEROTROFICO, eEspecie);

		mMover_Quantidade = 10;
		mVelocidade = 0;

		mAlimentacao = new ArrayList<String>();
		mCapacidadeDeAlimentacao = 0;
		mAlimentando = 0;
		mAlimentandoAndandoMaximo=0;
		mAlimentandoAndando=0;
	}

	public int getVelocidade() {
		return mVelocidade;
	}

	public void setVelocidade(int eVelocidade) {
		mVelocidade = eVelocidade;
	}

	public void setAlimentar(String eEspecie) {
		if (!mAlimentacao.contains(eEspecie)) {
			mAlimentacao.add(eEspecie);
		}
	}

	public void setNaoAlimentar(String eEspecie) {
		if (mAlimentacao.contains(eEspecie)) {
			mAlimentacao.remove(eEspecie);
		}
	}

	public ArrayList<String> getAlimentacao() {
		return mAlimentacao;
	}

	public void setCapacidadeDeAlimentacao(int eCapacidadeDeAlimentacao,int eAlimentandoAndandoMaximo) {
		mCapacidadeDeAlimentacao = eCapacidadeDeAlimentacao;
		mAlimentandoAndandoMaximo=eAlimentandoAndandoMaximo;
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

	public String getEspecieDoLugar(ArrayList<Organismo> mOrganismos, int X, int Y, int mLX, int mLY) {

		String ret = "";

		if (X >= 0 && Y >= 0 && X < mLX && Y < mLY) {

			for (Organismo OrganismoC : mOrganismos) {

				if (OrganismoC.getPos().getX() == X && OrganismoC.getPos().getY() == Y) {
					ret = OrganismoC.getEspecie();
					break;
				}

			}

		}

		return ret;
	}

	public Organismo getOrganismoDoLugar(ArrayList<Organismo> mOrganismos, int X, int Y, int mLX, int mLY) {

		Organismo ret = null;

		if (X >= 0 && Y >= 0 && X < mLX && Y < mLY) {

			for (Organismo OrganismoC : mOrganismos) {

				if (OrganismoC.getPos().getX() == X && OrganismoC.getPos().getY() == Y) {
					ret = OrganismoC;
					break;
				}

			}

		}

		return ret;
	}

	public int getMover_Quantidade() {
		return mMover_Quantidade;
	}

	public void setMover_Quantidade(int eMover_Quantidade) {
		mMover_Quantidade = eMover_Quantidade;
	}

	public void Mover(ArrayList<Organismo> mOrganismos, int mLX, int mLY) {

		if (mMover_Dir == -1 || mMover_Contagem >= mMover_Maximo) {

			Random Ale = new Random();

			mMover_Dir = Ale.nextInt(4);
			mMover_Maximo = Ale.nextInt(5) + mMover_Quantidade;
			mMover_Contagem = 0;

		}

		if (mVelocidade > 0) {

			for (int eV = 0; eV < this.getVelocidade(); eV++) {
				int X = this.getPos().getX();
				int Y = this.getPos().getY();

				if (mMover_Dir == 0) {
					X += 1;
				}

				if (mMover_Dir == 1) {
					X -= 1;
				}

				if (mMover_Dir == 2) {
					Y += 1;
				}

				if (mMover_Dir == 3) {
					Y -= 1;
				}

				mMover_Contagem += 1;

				if (this.LugarVazio(mOrganismos, X, Y, mLX, mLY)) {

					this.getPos().setPos(X, Y);

				} else {

					Organismo Habitante = this.getOrganismoDoLugar(mOrganismos, X, Y, mLX, mLY);
					if (Habitante != null) {

						if (Habitante.EstaVivo()) {
							// System.out.println("Tipo no lugar : " +Habitante.getEspecie() );

							if (this.getEspecie().contentEquals(Habitante.getEspecie())) {
								
								if (this.getFase()==Fases.ADULTO && Habitante.getFase()==Fases.ADULTO) {
									System.out.println(this.getEspecie() + " : Acasalando !");
								}

							}
							
							Alimentar(Habitante);

						}

					}
					
					if (mAlimentando < mCapacidadeDeAlimentacao) {
						mMover_Dir = -1;
					}

				}
			}
		}
	}

	private void Alimentar(Organismo OrganismoAlimento) {

		if (mAlimentando < mCapacidadeDeAlimentacao) {
			
			if (mAlimentacao.contains(OrganismoAlimento.getEspecie())) {

				System.out.println(this.getEspecie() + " : Alimentando-se de " + OrganismoAlimento.getEspecie());

				OrganismoAlimento.Morrer();
				this.getPos().setPos(OrganismoAlimento.getPos().getX(), OrganismoAlimento.getPos().getY());

				mAlimentando += 1;

			}
			
		} else {
			
			if (mAlimentandoAndando==0) {
				System.out.println(this.getEspecie() + " está alimentado !");
			}
			
			mAlimentandoAndando+=1;
			
			if (mAlimentandoAndando>mAlimentandoAndandoMaximo) {
				mAlimentando=0;
				mAlimentandoAndando=0;
				System.out.println(this.getEspecie() + " : Estou com fome !!!");

			}
		}

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

					Consumidor Novo = new Consumidor(PX, PY, this.getEspecie());
					Novo.setReproducao(Reproducao_Intervalo(), Reproducao_Ciclo(), Reproducao_Duracao());
					Novo.setCor(this.getCor());
					Novo.LimitarVida(this.LimiteDeVida());
					Novo.setVelocidade(this.getVelocidade());
					Novo.setPodeMover(this.getPodeMover());
					
					Novos.add(Novo);
					// System.out.println("Reproduzindo-se");

				} else {

					System.out.println("Abortando reprodução ... ");

				}

			}

		}
		DescentesGerados_Zerar();

		return Novos;
	}
}
