package sis_arq;

import sis_arq.Diretorio;

public class Manager {
	private Particao principal;
	private String caminho;
	private Diretorio dirAtual;
	private static Manager instance;
	
	private Manager() {
		this.caminho = "";
		this.dirAtual = null;
	}
	
	public static Manager getInstance() {
		if (instance == null)
			instance = new Manager();
		return instance;
	}

	public Particao getPrincipal() {
		return principal;
	}

	public void setPrincipal(Particao principal) {
		this.principal = principal;
		this.caminho = principal.getNome() + ":\\";
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Diretorio getDirAtual() {
		return dirAtual;
	}

	public void setDirAtual(Diretorio dirAtual) {
		this.dirAtual = dirAtual;
	}

	public static void setInstance(Manager instance) {
		Manager.instance = instance;
	}

	
	public void infoPart() {
		System.out.print(principal.toString());
	}	
	
	@Override
	public String toString() {
		return "Manager [principal=" + principal + ", caminho=" + caminho + ", dirAtual=" + dirAtual + "]";
	}

	// NAVEGA
	public void navega(String caminho) {
		int navega = buscaDiretorio(caminho);
		if (navega != -1) {
			if (navega == 1) {
				this.caminho = caminho;
				this.dirAtual = null;
			}
		} else {
			System.err.println("O sistema n�o pode encontrar o caminho especificado.");
		}
	}
	
	// RECUAR NA SUBARVORE
	public void out() {
		if (dirAtual == null) {
			System.err.println("N�o � poss�vel sair de uma parti��o.");
		} else {
			if (dirAtual.getPai() == null) {
				this.dirAtual = null;
				this.caminho = this.principal.getNome() + ":\\";
			} else {
				this.dirAtual = this.dirAtual.getPai();
				this.caminho = this.dirAtual.getCaminho();
			}
		}
	}
	
	
	// PARA DIRETORIOS
	
	private int buscaDiretorio(String caminho) {
		Diretorio auxiliar = dirAtual;
		dirAtual = null;
		int profundidade = 0;
		boolean valido = false;
		for (int i = 0; i < caminho.length(); i++) {
			if (caminho.charAt(i) == '\\') {
				profundidade++;
				valido = true;
			} else {
				valido = false;
			}
		}
		if (!valido) {
			dirAtual = auxiliar;
			return -1;
		}
		if (profundidade == 1)
			return profundidade;
		String [] busca = new String[profundidade];
		String aux = "";
		int j = 0;
		for (int i = 0; i < caminho.length(); i++) {
			if (caminho.charAt(i) != '\\') {
				aux += caminho.charAt(i);
			} else {
				busca[j++] = aux;
				aux = "";
			}
		}
		for (j = 0; j < principal.getDiretorios().size(); j++) {
			Diretorio dir = principal.getDiretorios().get(j);
			if (dir.getNome().equals(busca[1])) {
				dirAtual = dir;
				break;
			}
		}
		if (dirAtual == null) {
			dirAtual = auxiliar;
			return -1;
		}
		if (profundidade == 2) {
			this.caminho = dirAtual.getCaminho();
			return profundidade;
		}
		boolean found = false;
		for (int i = 2; i < busca.length; i++) {
			for (j = 0; j < dirAtual.getDiretorios().size(); j++) {
				if (dirAtual.getDiretorios().get(j).getNome().equals(busca[i])) {
					dirAtual = dirAtual.getDiretorios().get(j);
					found = true;
					break;
				}
			}
			if (!found) {
				dirAtual = auxiliar;
				return -1;
			} else {
				found = false;
			}
		}
		this.caminho = dirAtual.getCaminho();
		return profundidade;
	}

	
	public void criaDiretorio(String nome) {
		// Verifica se ainda a espa�o para criar o diretorio
		if (principal.getTamanhoUtilizado() + 2 > principal.getTamanhoTotal()) {
			System.err.println("N�o h� espa�o suficiente para criar um diret�rio.");
			return;
		}
		// ISTANCIANDO NOVO DIRETORIO
		String caminho_novo = this.caminho;
		caminho_novo += nome + "\\";
		Diretorio novo_dir = new Diretorio(nome, caminho_novo, this.dirAtual);
		
		// VERIFICANDO SE EXISTE DOIS DIRETORIOS COM O MESMO NOME (NESSA PARTI��O)
		for (int i = 0; i < principal.getDiretorios().size(); i++) {
			if (principal.getDiretorios().get(i).getCaminho().equals(novo_dir.getCaminho())) {
				System.err.println("N�o � poss�vel criar diret�rios com mesmo nome.");
				return;
			}
		}
					
		if (dirAtual == null) {
			// TENTA ADCIONAR UM DIRETORIO
			boolean add = principal.adicionaDiretorio(novo_dir);
			
			// N�O CONSEGUE ADDCIONAR
			if(!add) {
				System.err.println("Necessita compacta��o para a cria��o do diret�rio.");
				return;
			}
		}else {
			boolean add = principal.adicionaBloco(novo_dir);
			
			if (!add) {
				System.err.println("Necessita compacta��o para a cria��o do diret�rio.");
				return;
			}
			dirAtual.adicionaDiretorio(novo_dir);
			principal.atualizaTamanho();
		}
		this.caminho = caminho_novo;
		this.dirAtual = novo_dir;
		System.out.println("Diret�rio adicionado com sucesso.");
	}
	
	public void infoDir() {
		if (dirAtual == null) {
			System.err.println("O sistema n�o encontra-se em um diret�rio.");
		} else {
			System.out.println(dirAtual.toString());
		}
	}
	
	public void removeDiretorio(String nome) {
		Diretorio dir = null;
		// ACHA E PEGA O DIRETORIO PELO NOME
		for (int i = 0; i < principal.getDiretorios().size(); i++) {
			if (principal.getDiretorios().get(i).getNome().equals(nome)) {
				dir = principal.getDiretorios().get(i);
				break;
			}
		}
		// VERIFICA SE O DIRETORIO EXISTE
		if (dir == null) {
			System.err.println("O sistema n�o pode encontrar o diret�rio especificado.");
			return;
		}
		
		if (dirAtual == null) {
			removeBlocoDiretorio(dir);
			principal.getDiretorios().remove(dir);
			principal.atualizaTamanho();
			System.out.println("Diret�rio removido com sucesso.");
		}else {
			removeBlocoDiretorio(dir);
			dirAtual.removeDiretorio(dir);
			principal.atualizaTamanho();
			System.out.println("Diret�rio removido com sucesso.");
			
		}
	}
	
	private void removeBlocoDir(Diretorio dir) {
		for (int i = 0; i < principal.getBlocos().length; i++) {
			if (principal.getBlocos()[i] != null) {
				if (principal.getBlocos()[i].getDir() != null) {
					if (principal.getBlocos()[i].getDir().equals(dir))
						principal.getBlocos()[i] = null;
				}
			}
		}
	}
	
	private void removeBlocoDiretorio(Diretorio dir) {
		
		// REMOVE TODOS OS ARQUIVOS NESSE DIRETORIO
		for (int i = 0; i < dir.getArquivos().size(); i++)
				removeBlocoArq(dir.getArquivos().get(i));
		
		// REMOVE TODOS OS DIRETORIOS NESSE DIRETORIO
		for (int i = 0; i < dir.getDiretorios().size(); i++)
			removeBlocoDir(dir.getDiretorios().get(i));
		removeBlocoDir(dir);
	}
	
	
	
	// PARA ARQUIVO
	private void removeBlocoArq(Arquivo arq) {
		for (int i = 0; i < principal.getBlocos().length; i++) {
			if (principal.getBlocos()[i] != null) {
				if (principal.getBlocos()[i].getArq() != null) {
					if (principal.getBlocos()[i].getArq().equals(arq))
						principal.getBlocos()[i] = null;
				}
			}
		}
	}
	
	public void criaArquivo(String nome, int tamanho,String extensao) {
		if (principal.getTamanhoUtilizado() + tamanho > principal.getTamanhoTotal()) {
			System.err.println("N�o h� espa�o suficiente para criar um arquivo.");
			return;
		}
		if (dirAtual == null) {
			System.err.println("N�o � poss�vel criar arquivos em uma parti��o.");
			return;
		}
		for (int i = 0; i < dirAtual.getArquivos().size(); i++) {
			if (dirAtual.getArquivos().get(i).getNomeExtensao().equals(nome+extensao)) {
				System.err.println("N�o � poss�vel criar arquivos com mesmo nome.");
				return;
			}
		}
		Arquivo arq = new Arquivo(nome, tamanho, this.caminho, dirAtual,extensao);
		if(!principal.adicionaBloco(arq)) {
			System.err.println("Necessita compacta��o para a cria��o do arquivo.");
			return;
		}
		dirAtual.adicionaArquivo(arq);
		principal.atualizaTamanho();
		System.out.println("Arquivo adicionado com sucesso.");
	}
	
	public void removeArquivo(String nome,String extensao) {
		Arquivo arq = null;
		for (int i = 0; i < dirAtual.getArquivos().size(); i++) {
			if (dirAtual.getArquivos().get(i).getNomeExtensao().equals(nome+extensao)) {
				arq = dirAtual.getArquivos().get(i);
				break;
			}
		}
		if (arq == null) {
			System.err.println("O sistema n�o pode encontrar o arquivo especificado.");
		} else {
			removeBlocoArq(arq);
			dirAtual.removeArquivo(arq);
			principal.atualizaTamanho();
			System.out.println("Arquivo removido com sucesso.");
		}
	}
	
}
