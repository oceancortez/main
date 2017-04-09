package omc.test.in.loco;
/**
 * 
 */

/**
 * @author ocean
 *
 */
public class TestUolFaria {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	

	//	 escada();
//		int[] num = { 1, 2, 3, 4 };
//		summation(num);
		int i = 6, m = 1, k = 9;
		TestUolFaria.faca(i, m, k);
		System.out.println(k);
		
	}

	@SuppressWarnings("unused")
	private static void escada() {
		int n = 6;
		 String rashtag = "#";
		 String start = "#";
		 for (int i = 0; i < n ; i++) {
		 System.out.println(start);
		 start = start.concat(rashtag);
		 }
	}

	static int summation(int[] numbers) {
		int soma = 0;
		if (numbers != null) {

			for (int i = 0; i < numbers.length; i++) {
				soma = soma + numbers[i];
				System.out.println(soma);
			}
		}

		return soma;

	}
	
	
	public static void  faca(int x, int y, int m){
		if(x == 5){
			m = y;
		 System.out.println(x);
		}else{
			m = x;
			 System.out.println(x);
		}
	}
	
	

}
