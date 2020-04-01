package Biosistema;

import AlphaTester.AlphaTester;

public class Biosistema_Testes extends AlphaTester {

	public Biosistema_Testes() {

		
		TEST("Ecossistema", "Global");
		ASSERT_EQ("O2", "O3");
		ASSERT_EQ("O3", "O3");
		ASSERT_TRUE(true);

		System.out.println("DELTA : " + getDelta());
		
		
		TEST("Ecossistema", "Organismo");
		ASSERT_EQ("O3", "O3");

		System.out.println("DELTA : " + getDelta());

		TEST("Ecossistema", "Produtor");
		ASSERT_EQ("O3", "O3");
		ASSERT_EQ("O8", "O3");
		ASSERT_EQ("O3", "O3");

		System.out.println("DELTA : " + getDelta());

		TEST("Numeros", "Comparabilidade");
		ASSERT_BIGGER(8, 3);
		ASSERT_SMALLER(3, 8);
		ASSERT_NE(8, 3);
		ASSERT_NE(3, 8);
		ASSERT_EQ(8, 8);
		ASSERT_EQ(3, 3);

		System.out.println("DELTA : " + getDelta());

		TEST("Ecossistema", "Produtor");
		ASSERT_EQ("O3", "O3");

		System.out.println("DELTA : " + getDelta());

		ANALISE();
		
		RESUME();
		
		TESTES();

		ESTATISTICAS();
		
	}
}
