package sis_arq;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;

import sis_arq.Diretorio;
import sis_arq.Bloco;



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
		String retorno = "----------------------------------------------------------------\n";
		retorno += "Partição " + this.getNome() + "\n";
		retorno += "\tTamanho total: " + this.tamanhoTotal + "\n";
		retorno += "\tTamanho utilizado: " + this.tamanhoUtilizado + "\n";
		retorno += "\tData de criação: " + this.dataDeCriacao + "\n";
		if (diretorios.size() != 0) {
			retorno += "\tDiretórios dentro dessa partição:\n";
			Diretorio dir;
			for (int i = 0; i < diretorios.size(); i++) {
				dir = diretorios.get(i);
				retorno += "\t*****************************************\n";
				retorno += "\t\tDiretório " + dir.getNome() + "\n";
				retorno += "\t\t\tTamanho: " + dir.getTamanho() + "\n";
				retorno += "\t\t\tData de criação: " + dir.getDataDeCriacao() + "\n";
				retorno += "\t*****************************************\n";
			}
		}
		retorno += "----------------------------------------------------------------\n";
		return retorno;
	}
	// Retorna o INDEX do Espaço livre
	// Retorna -1 se não houver espaço
	private int verificaEspaco(int tamanho) {
		int idx = -1;
		int counter = 0;
		boolean found  = false;
		for (int i = 0; i < this.tamanhoTotal/Bloco.getTamanho(); i++) {
			if (blocos[i] == null) {
				if (!found) {
					found = true;
					idx = i;
					counter++;
				} else {
					counter++;
				}
				if (counter == tamanho)
					break;
			} else {
				found = false;
				counter = 0;
			}
		}
		if (counter < tamanho)
			idx = -1;
		return idx;
	}
	
	public void atualizaTamanho() {
		this.tamanhoUtilizado = 0;
		for (int i = 0; i < diretorios.size(); i++)
			this.tamanhoUtilizado += diretorios.get(i).getTamanho();
	}
	public boolean adicionaDiretorio(Diretorio dir) {
		// TODO Auto-generated method stub
		int k = verificaEspaco(dir.getTamanho()/Bloco.getTamanho());
		boolean resultado = false;
		// Não existe espaço
		if (k == -1)
			return resultado;
		// adicionando um novo diretorio na partição
		this.diretorios.add(dir);
		// atualizando o tamanho utilizado
		this.tamanhoUtilizado += dir.getTamanho();
		resultado = true;
		//adcionando os novos blocos nas posições corretas
		for (int i = k; i < dir.getTamanho()/Bloco.getTamanho(); i++)
			blocos[i] = new Bloco(dir);
		return resultado;
	
	}
	
	public boolean adicionaBloco(Diretorio dir) {
		int k = verificaEspaco(dir.getTamanho()/Bloco.getTamanho());
		boolean resultado = false;
		if (k == -1)
			return resultado;
		resultado = true;
		for (int i = 0; i < dir.getTamanho()/Bloco.getTamanho(); i++)
			blocos[k++] = new Bloco(dir);
		return resultado;
	}
	
	public boolean adicionaBloco(Arquivo arq) {
		int k = verificaEspaco(arq.getTamanho()/Bloco.getTamanho());
		boolean resultado = false;
		if (k == -1)
			return resultado;
		resultado = true;
		for (int i = 0; i < arq.getTamanho()/Bloco.getTamanho(); i++)
			blocos[k++] = new Bloco(arq);
		return resultado;
	}
	
	
	
}
