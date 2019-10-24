package sis_arq;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Diretorio {

	private String nome;
	private LocalDateTime dataDeCriacao;
	private int tamanho;
	private ArrayList<Arquivo> arquivos;
	private ArrayList<Diretorio> diretorios;
	private String caminho;
	private Diretorio pai;
	
	public Diretorio(String nome, String caminho, Diretorio pai) {
		this.setNome(nome);
		this.dataDeCriacao = LocalDateTime.now();
		this.tamanho = 2;
		this.arquivos = new ArrayList<Arquivo>();
		this.diretorios = new ArrayList<Diretorio>();
		this.caminho = caminho;
		this.pai = pai;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public ArrayList<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(ArrayList<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public ArrayList<Diretorio> getDiretorios() {
		return diretorios;
	}

	public void setDiretorios(ArrayList<Diretorio> diretorios) {
		this.diretorios = diretorios;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Diretorio getPai() {
		return pai;
	}

	public void setPai(Diretorio pai) {
		this.pai = pai;
	}

	@Override
	public String toString() {
		return "Diretorio [nome=" + nome + ", dataDeCriacao=" + dataDeCriacao + ", tamanho=" + tamanho + ", arquivos="
				+ arquivos + ", diretorios=" + diretorios + ", caminho=" + caminho + ", pai=" + pai + "]";
	}

}
