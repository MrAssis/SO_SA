package sis_arq;

import java.util.Scanner;


public class cmd {

	public static void main(String[] args) {
		Manager manager = Manager.getInstance();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String nome;
		String key;
		int tamanho;
		System.out.println("\t\t\tBem-vindo!");
		System.out.print("Dê um nome a sua partição: ");
		nome = sc.nextLine();
		System.out.print("Informe o tamanho da sua partição: ");
		tamanho = sc.nextInt();
		sc.nextLine();
		Particao principal = new Particao(nome, tamanho);
		manager.setPrincipal(principal);
		System.out.println("Partição criada com sucesso!");
		
		String caminho;
		String ext;
		while (true) {
			System.out.print(manager.getCaminho() + ">");
			key = sc.nextLine();
			
			
			switch (key) {
			
			case "exit":
				System.out.println("FIM DO PROGRAMA");
				return;
			
			case "out":
				manager.out();
				break;
				
			case "criaArq":
				System.out.print("Nome do arquivo: ");
				nome = sc.nextLine();
				System.out.print("extensao: ");
				ext = sc.nextLine();
				System.out.print("Tamanho do arquivo: ");
				tamanho = sc.nextInt();
				sc.nextLine();
				manager.criaArquivo(nome, tamanho,ext);
				break;
			
			case "removeArq":
				System.out.print("Nome do arquivo: ");
				nome = sc.nextLine();
				System.out.print("extensao: ");
				ext = sc.nextLine();
				manager.removeArquivo(nome,ext);
				break;
			case "navega":
				System.out.print("Caminho: ");
				caminho = sc.nextLine();
				manager.navega(caminho);
				break;
				
			case "partInfo":
				manager.infoPart();
				break;
			
			// COMANDOS REFERENTES A DIRETÓRIO
			case "criarDir":
				System.out.print("Nome do diretório: ");
				nome = sc.nextLine();
				manager.criaDiretorio(nome);
				break;
			
			// TODO ERRO NO TOSTRING
			case "infoDir":
				manager.infoDir();
				break; 
			
			case "removeDir":
				System.out.print("Nome do diretório: ");
				nome = sc.nextLine();
				manager.removeDiretorio(nome);
				break;
			
			// COMANDOS REFERENTES A ARQUIVOS
		
			default:
				System.err.println("\nComando não reconhecido pelo sistema.");
				break;
			}
			
			}
		}

}


