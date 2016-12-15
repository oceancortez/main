package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Adds the or remove e comercial of segurado or corretor.
	 *
	 * @param file the file
	 * @param operator the operator
	 * @return the file
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static File addOrRemoveEComercialOfSeguradoOrCorretor(final File file, final String operator) throws FileNotFoundException,
			IOException {
		String line = null;
		final StringBuilder stringBuilder = new StringBuilder();				
		final FileReader fileReader = new FileReader(file);
		final BufferedReader bufferedReader = new BufferedReader(fileReader);
		String linhaAnterior = StringUtils.EMPTY;
		
		do{
			if(linhaAnterior.contains("<SEGURADO>") || linhaAnterior.contains("<SUSEP>")){ 
				if(line.contains("&")){
					if(operator.equalsIgnoreCase("ESCAPE")){						
						line = line.replace("&", "&amp;");
						linhaAnterior = StringUtils.EMPTY;
						
					}else if(operator.equalsIgnoreCase("UNESCAPE")){
						line = line.replace("&amp;", "&");
					 	linhaAnterior = StringUtils.EMPTY;
					}	
				}
			}		
			 if (StringUtils.isNotEmpty(line) &&  line.contains("<SEGURADO>")){		        	
				 	linhaAnterior = line;		     
		       }else if (StringUtils.isNotEmpty(line) && line.contains("<SUSEP>")){
		    	   linhaAnterior = line;			
		    }
			 if(StringUtils.isNotEmpty(line)){
				 stringBuilder.append(line).append("\n");
			 }
		}
		
		 while ((line = bufferedReader.readLine()) != null);		       
		
		 fileReader.close();
		 bufferedReader.close();
		 FileUtils.writeStringToFile(file, stringBuilder.toString());
		return file;
	}

}
