package omc.test.in.loco;

public class TestInLoco {

	public static void main(String[] args) {
		
		String[] test = {"111|222|333"};
		
		testSplit(test);

	}
	
	static void testSplit(String[] result){
		String[] quebraPorSplit = null;
		for (String test : result) {
			quebraPorSplit = test.split("\\|");				
		}
		
       for (String test : quebraPorSplit) {
    	   System.out.println("test = ".concat(test));
       	}
		
	}

}
