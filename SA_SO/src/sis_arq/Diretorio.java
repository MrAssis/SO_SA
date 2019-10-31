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

	public void atualizaTamanho() {
		this.tamanho = 2;
		for (int i = 0; i < arquivos.size(); i++)
			this.tamanho += arquivos.get(i).getTamanho();
		for (int i = 0; i < diretorios.size(); i++)
			this.tamanho += diretorios.get(i).getTamanho();
		if (this.pai != null)
			pai.atualizaTamanho();
	}
	
	public void adicionaDiretorio(Diretorio dir) {
		this.diretorios.add(dir);
		atualizaTamanho();
	}
	
	public void removeDiretorio(Diretorio dir) {
		this.diretorios.remove(dir);
		atualizaTamanho();
	}
	
	public void adicionaArquivo(Arquivo arq) {
		this.arquivos.add(arq);
		atualizaTamanho();
	}
	public void removeArquivo(Arquivo arq) {
		this.arquivos.remove(arq);
		atualizaTamanho();
	}
	@Override
	public String toString() {
		String retorno = "----------------------------------------------------------------\n";
		retorno += "Diret�rio " + this.nome + ":\n";
		retorno += "\tTamanho: " + this.tamanho + "\n";
		retorno += "\tData de cria��o: " + this.dataDeCriacao + "\n";
		if (arquivos.size() != 0) {
			retorno += "\tArquivos do diret�rio:\n\n";
			for (int i = 0; i < arquivos.size(); i++)
				retorno += arquivos.get(i).toString();
		}
		if (diretorios.size() != 0) {
			retorno += "\tDiret�rios dentro desse diret�rio:\n";
			Diretorio dir;
			for (int i = 0; i < diretorios.size(); i++) {
				dir = diretorios.get(i);
				retorno += "\t*****************************************\n";
				retorno += "\t\tDiret�rio " + dir.getNome() + "\n";
				retorno += "\t\t\tTamanho: " + dir.getTamanho() + "\n";
				retorno += "\t\t\tData de cria��o: " + dir.getDataDeCriacao() + "\n";
				retorno += "\t*****************************************\n";
			}
		}
		retorno += "----------------------------------------------------------------\n";
		
		return retorno;
	}

}
