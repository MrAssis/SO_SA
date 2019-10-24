package sis_arq;

import java.util.Scanner;


public class cmd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Cria o Gerenciador de Arquivos
		Manager manager = Manager.getInstance();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Sistema Aberto...dê o nome a sua partição: ");
		String nome = sc.nextLine();
		
		System.out.println("Informe o tamanho da sua Partição: ");
		int tam = sc.nextInt();
		
		//Cria a Partição principal e Seta ela no manager
		Particao principal = new Particao(nome, tam);
		manager.setPrincipal(principal);
		System.out.println("Partição criada com sucesso!");
		
		
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
				System.out.print("Nome do diretório: ");
				nome = sc.nextLine();
				manager.criaDiretorio(nome);
				break;
				
			default:
				System.err.println("\nComando não reconhecido pelo sistema.");
				break;
			}
			}
		}

}


