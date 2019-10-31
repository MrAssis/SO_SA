package sis_arq;

import java.time.LocalDateTime;




public class Arquivo {
	private String nome;
	private String extensao;
	private LocalDateTime dataDeCriacao;
	private int tamanho;
	private String caminho;
	private Diretorio pai;
	
	public Arquivo(String nome, int tamanho, String caminho, Diretorio pai, String extensao) {
		this.setNome(nome);
		this.setTamanho(tamanho);
		this.setDataDeCriacao(LocalDateTime.now());
		this.setCaminho(caminho);
		this.setCaminho(this.getCaminho() + nome);
		this.setPai(pai);
		this.setExtensao(extensao);
	}

	public Diretorio getPai() {
		return pai;
	}

	public void setPai(Diretorio pai) {
		this.pai = pai;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String toString() {
		String retorno = "\t*****************************************\n";
		retorno += "\t\tArquivo " + this.getNome() + "\n";
		retorno += "\t\t\tTamanho: " + this.getTamanho() + "\n";
		retorno += "\t\t\tExtensão: " + this.getExtensao() + "\n";
		retorno += "\t\t\tData de criação: " + this.getDataDeCriacao() + "\n";
		retorno += "\t*****************************************\n";
		return retorno;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	

	public String getNomeExtensao() {
		return this.nome + this.extensao;
	}
}
