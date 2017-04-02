package br.com.vb.readlogs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreparesLogsForImportExcel {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		Long inicio = System.currentTimeMillis();		
		List<String> nameFiles = readFilesInPath();
		List<String> sucessoTotalMonth = new ArrayList<>();
		List<String> falhaTotalMonth = new ArrayList<>();
		List<String> asteriskTotalMonth = new ArrayList<>();
		File destinPath = null;
		
		for(int i = 0; i < nameFiles.size(); i++){
			
			List<String> sucesso = new ArrayList<>();
			List<String> falha = new ArrayList<>();
			List<String> asterisk = new ArrayList<>();
			
			String dateFile = getDateOfFileAndConcat(nameFiles, i);			
			String path = "C:\\Users\\ocean.cortez\\works\\logs\\teste\\preparaArquivo\\"+ nameFiles.get(i);			
			BufferedReader br = null;			
			
			try {				
				br = new BufferedReader(new FileReader(path));		
				
				System.out.println("Iniciando leitura do arquivo = " + nameFiles.get(i));			
				buildReadFiles(dateFile, br, sucesso, falha, asterisk);
				if(!falha.isEmpty()){
				falhaTotalMonth.addAll(falha);
				}
				if(!sucesso.isEmpty()){
				sucessoTotalMonth.addAll(sucesso);
				}
				if(!asterisk.isEmpty()){
				asteriskTotalMonth.addAll(asterisk);
				}
				
				System.out.println("Fim da leitura dos arquivo = " + nameFiles.get(i));
				br.close();				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
			
		String nameFile = getNameFileWithoutDay(nameFiles, 0);

		destinPath = new File("C:\\Users\\ocean.cortez\\works\\logs\\teste\\arquivosPreparados\\"+ nameFile);			
		
			buildFileFalha(destinPath, falhaTotalMonth);		
			buildFileSucesso(destinPath, sucessoTotalMonth);		
			buildFileAsterisk(destinPath, asteriskTotalMonth);
		
		Long fim = System.currentTimeMillis();
		Long total =  fim - inicio;
		System.out.println("Fim - Tempo total de execução = " + total);			
		}
	

	private static List<String> readFilesInPath() {
		List<String> nameFiles = new ArrayList<>();		
		File[] files = new File("C:\\Users\\ocean.cortez\\works\\logs\\teste\\preparaArquivo\\").listFiles();
		for(File file : files ){
			if(file.isFile()){				
				nameFiles.add(file.getName());
			}
		}
		return nameFiles;
	}

	private static String getDateOfFileAndConcat(List<String> nameFiles, int i) {
		String nameFile = nameFiles.get(i);			
		int totalCaracter = nameFile.length();		
		int patternCaracterDate = 10;
		int initCaracter = totalCaracter - patternCaracterDate;
		String dateFile = nameFile.substring(initCaracter, totalCaracter);
		return dateFile;
	}
	
	private static String getNameFileWithoutDay(List<String> nameFiles, int i) {
		String nameFile = nameFiles.get(i);
		int initCaracter = 0;		
		int totalCaracter = nameFile.length();
		totalCaracter = totalCaracter - 3;
		return nameFile.substring(initCaracter, totalCaracter);
	}

	private static void buildReadFiles(String dateFile, BufferedReader br, List<String> sucesso, List<String> falha,
			List<String> asterisk) throws IOException {
		String currentLine;
		while((currentLine  = br.readLine()) != null){
			
			if(currentLine.contains("[FALHA]")){
				String currline = currentLine.replaceAll("\\[", ";").replaceAll("\\=", ";").replaceAll("\\]", ";");
				currline = dateFile.concat(" " + currline);
				falha.add(currline);					
			}if(currentLine.contains("[SUCESSO]")){
				String currline = currentLine.replaceAll("\\[", ";").replaceAll("\\=", ";").replaceAll("\\]", ";");
				currline = dateFile +" " + currline;
				sucesso.add(currline);					
			}if(currentLine.contains("[Asterisk]")){
				String currline = currentLine.replaceAll("\\[", ";").replaceAll("\\=", ";").replaceAll("\\]", ";");
				currline = dateFile + " " + currline;
				asterisk.add(currline);
			}							
		}
	}

	private static void buildFileAsterisk(File destinPath, List<String> asterisk) throws IOException {
		BufferedWriter writer;
		if(asterisk.size() > 0){
		System.out.println("Agora escrevendo no arquivo consolidado do tipo asterisk.txt");
		writer = new BufferedWriter(new FileWriter(destinPath + "_asterisk.txt"));
		for (int x  = 0; x < asterisk.size(); x++){				
		  writer.append(asterisk.get(x) + "\n");
		}
		writer.close();
		}
	}

	private static void buildFileSucesso(File destinPath, List<String> sucesso) throws IOException {
		BufferedWriter writer;
		if(sucesso.size() > 0){
		System.out.println("Agora escrevendo no arquivo consolidado do tipo sucesso.txt");
		writer = new BufferedWriter(new FileWriter(destinPath + "_sucesso.txt"));
		for (int x  = 0; x < sucesso.size(); x++){									
		    	writer.append(sucesso.get(x) + "\n");
		}			
		writer.close();
		}
	}

	private static void buildFileFalha(File destinPath, List<String> falha) throws IOException {
		BufferedWriter writer = null;			
		if(falha.size() > 0){
		System.out.println("Escrevendo no arquivo consolidado do tipo falha.txt");
		writer = new BufferedWriter(new FileWriter(destinPath + "_falha.txt"));
		for (int x  = 0; x < falha.size(); x++){				
				writer.append(falha.get(x) + "\n");				
		}
		writer.close();
		}
	}
		
}
