package main;

import java.util.Arrays;

public class TesteLogica {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] marvin = new String[]{"*--", "---", "***"};
		//printFizzBuzz();		
		//countCreepyMoments();		
		countStars(marvin);

	}
	//Imprime mutipolos de 3&&5 || 3 || 5	
	 static void  printFizzBuzz(){
		for(int i=1; i<=100; i++) {
			if(i%3 == 0 && i%5 == 0){
				System.out.println("FizzBuzz");
				}else if (i%3 == 0){
					System.out.println("Fizz");
				} else if (i%5 == 0){
					System.out.println("Buzz");
				}else if(i%3 != 0){
					System.out.println(i);
				}
			}
	  }
	 
	//Recebe um array de String, e retornar soma total de acordo com q contagem de strelas
	 static int countStars(String[] result){		
		 int retorno = 0;
		 
		 for (String teste : result) {
			 
			 if(Arrays.asList(teste).contains("---")){
				 retorno = retorno + 0;
			 }else if(Arrays.asList(teste).contains("*--")){
				 retorno = retorno + 1;			
			 }else if(Arrays.asList(teste).contains("**-")){
				 retorno = retorno + 2;
			 }else if(Arrays.asList(teste).contains("***")){
				 retorno = retorno + 3;
			 }		 
			
		}	
		
		 return retorno;
	 }
	 
	 /*
	  * Você recebe um String [] momentos, onde cada elemento representa um único momento de tempo. 
	  * Retornar quantas vezes*  o relógio soou. Por exemplo, para uma entrada {"11:00", "13:13", "10:00"}
	  * 
	  * */
	static int countCreepyMoments(String[] moments){		
		int retorno = 0;
		for(String s : moments){
			char[] caractere = s.toCharArray();
			
			if(caractere[0]==caractere[1] && caractere[3]==caractere[4]){
				//AABB
				++retorno;
			
			}else if(caractere[0]==caractere[3] && caractere[1]==caractere[4]){
				//ABAB
				++retorno;
			}else if(caractere[0]==caractere[4] && caractere[1]==caractere[3]){
				//ABBA
				++retorno;
		}else if(caractere[0]==caractere[1] && caractere[1]==caractere[3] && caractere[3]==caractere[4])
				//AAAA
				++retorno;
		}
		return retorno;
		 
	 }
	 
	 

}
