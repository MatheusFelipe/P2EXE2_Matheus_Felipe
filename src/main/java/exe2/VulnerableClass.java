package exe2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {
	private String sTest; //String para testes
	public String getsTest(){
		return this.sTest;
	}
	
	public void vulnerableMethod(String FILENAME) throws FileNotFoundException{
			/*Caso passe nome vazio*/
			if(FILENAME == null)
				throw new IllegalArgumentException();
			/*Sanitizacao da entrada: FILENAME devera conter apenas o nome do arquivo, 
			 *sem a extensao, para nao abrir margem a caracteres especiais*/
			Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
			Matcher matcher = pattern.matcher(FILENAME);
			if(matcher.find()) {
				throw new IllegalArgumentException();
			}
			
		    Scanner console = new Scanner(System.in);
		    /*Como nao foi especificado com que tipo de arquivo irei trabalhar, padronizei .txt*/
		    System.out.print("Digite a operacao desejada para realizar no arquivo .txt <R para ler um arquivo .txt, "
		    		+ "W para escrever em um arquivo .txt> ");
			
		    String opr = console.nextLine();
			
		    if (opr.equals("R") || opr.equals("r")){
				BufferedReader br = null;
				FileReader fr = null;
				
				try {
					fr = new FileReader(FILENAME + ".txt");
					br = new BufferedReader(fr);
					sTest = "";
					String sCurrentLine;
					while ((sCurrentLine = br.readLine()) != null) {
						sTest = sTest + sCurrentLine + "\n";
					}
					System.out.print(sTest);
				} catch (IOException e) {
					e.printStackTrace();
					throw new FileNotFoundException(); //Para os testes, caso nao encontre FILENAME.txt
				} finally {/*Fechar o que foi aberto*/
					try {
						if(br!=null) br.close();
						if(fr!=null) fr.close();
						if(console != null) console.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if (opr.equals("W") || opr.equals("w")) {
				  BufferedWriter buffWrite = null;
				  try {
					buffWrite = new BufferedWriter(new FileWriter(FILENAME + ".txt"));
					String linha = "";
					System.out.println("Escreva algo: ");
				    linha = console.nextLine();
				    buffWrite.append(linha + "\n");
				    sTest = linha;
				} catch (IOException e) {
					e.printStackTrace();
				} finally {/*Fechar o que foi aberto*/
					try {
						if(buffWrite != null) buffWrite.close();
						if(console != null) console.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else{ /*Caso o usuario nao tenha digitado corretamente a opcao de acao do programa*/
				if(console != null) console.close();
				throw new IllegalArgumentException();
			}
	}
}
