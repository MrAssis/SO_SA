package sis_arq;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;


public class Particao {

	private String nome;
	private int tamanhoTotal;
	private int tamanhoUtilizado;
	private Bloco [] blocos;
	private ArrayList<Diretorio> diretorios;
	private LocalDateTime dataDeCriacao;
	
	public Particao(String nome, int tamanhoTotal) {
		this.nome = nome;
		this.tamanhoTotal = tamanhoTotal;
		this.tamanhoUtilizado = 0;
		this.blocos = new Bloco[tamanhoTotal/Bloco.getTamanho()];
		this.diretorios = new ArrayList<Diretorio>();
		this.dataDeCriacao = LocalDateTime.now();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTamanhoTotal() {
		return tamanhoTotal;
	}

	public void setTamanhoTotal(int tamanhoTotal) {
		this.tamanhoTotal = tamanhoTotal;
	}

	public int getTamanhoUtilizado() {
		return tamanhoUtilizado;
	}

	public void setTamanhoUtilizado(int tamanhoUtilizado) {
		this.tamanhoUtilizado = tamanhoUtilizado;
	}

	public Bloco[] getBlocos() {
		return blocos;
	}

	public void setBlocos(Bloco[] blocos) {
		this.blocos = blocos;
	}

	public ArrayList<Diretorio> getDiretorios() {
		return diretorios;
	}

	public void setDiretorios(ArrayList<Diretorio> diretorios) {
		this.diretorios = diretorios;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	@Override
	public String toString() {
		return "Particao [nome=" + nome + ", tamanhoTotal=" + tamanhoTotal + ", tamanhoUtilizado=" + tamanhoUtilizado
				+ ", blocos=" + Arrays.toString(blocos) + ", diretorios=" + diretorios + ", dataDeCriacao="
				+ dataDeCriacao + "]";
	}
	
	
	
}
