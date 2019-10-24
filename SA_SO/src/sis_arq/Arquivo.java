package sis_arq;

import java.time.LocalDateTime;



public class Arquivo {
	private String nome;
	private LocalDateTime dataDeCriacao;
	private int tamanho;
	private String caminho;
	private Diretorio pai;
	
	public Arquivo(String nome, int tamanho, String caminho, Diretorio pai) {
		this.setNome(nome);
		this.setTamanho(tamanho);
		this.setDataDeCriacao(LocalDateTime.now());
		this.setCaminho(caminho);
		this.setCaminho(this.getCaminho() + nome);
		this.setPai(pai);
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
	
	
	@Override
	public String toString() {
		return "Arquivo [nome=" + this.nome + ", dataDeCriacao=" + this.dataDeCriacao + ", tamanho=" + this.tamanho + ", caminho="
				+ this.caminho + ", pai=" + this.pai + "]";
	}

}
