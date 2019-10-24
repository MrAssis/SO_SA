package sis_arq;

import java.util.Scanner;


public class cmd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Cria o Gerenciador de Arquivos
		Manager manager = Manager.getInstance();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Sistema Aberto...d� o nome a sua parti��o: ");
		String nome = sc.nextLine();
		
		System.out.println("Informe o tamanho da sua Parti��o: ");
		int tam = sc.nextInt();
		
		//Cria a Parti��o principal e Seta ela no manager
		Particao principal = new Particao(nome, tam);
		manager.setPrincipal(principal);
		System.out.println("Parti��o criada com sucesso!");
		
		
		// Prompt de Comando
		String com; // Comandos
		while (true) {
			System.out.println(manager.getCaminho() + ">");
			com = sc.nextLine();
			
			switch (com) {
			case "exit":
				System.out.println("FIM DO PROGRAMA");
				return;
			
			case "partInfo":
				manager.infoPart();
				break;
			
			case "criarDir":
				System.out.print("Nome do diret�rio: ");
				nome = sc.nextLine();
				manager.criaDiretorio(nome);
				break;
				
			default:
				System.err.println("\nComando n�o reconhecido pelo sistema.");
				break;
			}
			}
		}

}


