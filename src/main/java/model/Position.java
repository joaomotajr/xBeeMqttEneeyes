package model;

public class Position {
	String key;
	String nome;
	String tipo;
	Integer id;
	Double value;
	
	public Position() {		
	}
	
	public Position(String key, String nome, String tipo, int id, double value) {
		
		this.key = key;
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
}
