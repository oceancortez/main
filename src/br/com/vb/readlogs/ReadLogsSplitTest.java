package br.com.vb.readlogs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ReadLogsSplitTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Long inicio = System.currentTimeMillis();		
		
		String currentLine;			
		final String MES_ARQUIVO= "06";
		final String DIA_ARQUIVO = "07";
		List<String> nameFiles = new ArrayList<>();
		
		File[] files = new File("C:\\Users\\ocean.cortez\\works\\logs\\teste\\preparaArquivo\\").listFiles();
		for(File file : files ){
			if(file.isFile()){				
				nameFiles.add(file.getName());
			}
		}

		for(int i = 0; i < nameFiles.size(); i++){
			
			/**
			 * Colocar o arquivo de log neste diretorio
			 */
			String path = "C:\\Users\\ocean.cortez\\works\\logs\\teste\\preparaArquivo\\"+ nameFiles.get(i);
			File destinPath = new File("C:\\Users\\ocean.cortez\\works\\logs\\teste\\ok\\"+ nameFiles.get(i));
			BufferedReader br = null;
			try {				
				br = new BufferedReader(new FileReader(path));
				List<String> sucesso = new ArrayList<>();
				List<String> falha = new ArrayList<>();
				List<String> asterisk = new ArrayList<>();
				
				System.out.println("Iniciando leitura do arquivo = " + nameFiles.get(i));			
				while((currentLine  = br.readLine()) != null){
					
					if(currentLine.contains("[FALHA]")){
						String currline = currentLine.replaceAll("\\[", ";").replaceAll("\\=", ";").replaceAll("\\]", ";");
						falha.add(currline);					
					}if(currentLine.contains("[SUCESSO]")){
						String currline = currentLine.replaceAll("\\[", ";").replaceAll("\\=", ";").replaceAll("\\]", ";");
						sucesso.add(currline);					
					}if(currentLine.contains("[Asterisk]")){
						String currline = currentLine.replaceAll("\\[", ";").replaceAll("\\=", ";").replaceAll("\\]", ";");
						asterisk.add(currline);
					}							
				}
				System.out.println("Fim da leitura dos arquivo = " + nameFiles.get(i));
				br.close();					

				BufferedWriter writer = null;			
				if(falha.size() > 0){
				System.out.println("Escrevendo no arquivo falha.txt");
				writer = new BufferedWriter(new FileWriter(destinPath + "_falha.txt"));
				for (int x  = 0; x < falha.size(); x++){				
						writer.append(falha.get(x) + "\n");				
				}
				writer.close();
				}
				
				
				if(sucesso.size() > 0){
				System.out.println("Agora escrevendo no arquivo sucesso.txt");
				writer = new BufferedWriter(new FileWriter(destinPath + "_sucesso.txt"));
				for (int x  = 0; x < sucesso.size(); x++){									
				    	writer.append(sucesso.get(x) + "\n");
				}			
				writer.close();
				}
				
				if(asterisk.size() > 0){
				System.out.println("Agora escrevendo no arquivo asterisk.txt");
				writer = new BufferedWriter(new FileWriter(destinPath + "_asterisk.txt"));
				for (int x  = 0; x < asterisk.size(); x++){				
				  writer.append(asterisk.get(x) + "\n");
				}
				writer.close();
				}
								
				
				System.out.println("Fim da operação do elemento = " + nameFiles.get(i));
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		Long fim = System.currentTimeMillis();
		Long total =  fim - inicio; 
		
		System.out.println("Tempo total de execução = " + total);
			
		}
			
	
		
}
