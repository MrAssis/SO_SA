package sis_arq;



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

	public void criaDiretorio(String nome) {
		// Verifica se ainda a espaço para criar o diretorio
		if (principal.getTamanhoUtilizado() + 2 > principal.getTamanhoTotal()) {
			System.err.println("Não há espaço suficiente para criar um diretório.");
			return;
		}
	}
	
	
}
