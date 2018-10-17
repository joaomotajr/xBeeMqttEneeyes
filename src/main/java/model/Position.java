package model;

import java.math.BigDecimal;

public class Position {
	
	private String key;
	private String nome;
	private String tipo;
	private String unidade;
	private String detail;
	private Integer id;	
	private Double minValue;
	private Double maxValue;
	private Double alarm1;
	private Double alarm2;
	private BigDecimal value;
	private BigDecimal milliTime;	 
	
	public Position() {		
	}
	
	public Position(String key, 
			String nome, 
			String tipo, 
			String unidade,
			String detail,
			int id,			 
			Double minValue, 
			Double maxValue,
			Double alarm1,
			Double alarm2,
			BigDecimal value,
			BigDecimal milliTime			
			) {
		
		this.key = key;
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
		this.detail = detail;
		this.unidade = unidade;		
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.alarm1 = alarm1;
		this.alarm2 = alarm2;
		this.value = value;
		this.milliTime = milliTime;
		
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Position other = (Position) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
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
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public String getUnidade() {
		return unidade;
	}	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public BigDecimal getMilliTime() {
		return milliTime;
	}
	public void setMilliTime(BigDecimal milliTime) {
		this.milliTime = milliTime;
	}
	public Double getAlarm1() {
		return alarm1;
	}
	public void setAlarm1(Double alarm1) {
		this.alarm1 = alarm1;
	}
	public Double getAlarm2() {
		return alarm2;
	}
	public void setAlarm2(Double alarm2) {
		this.alarm2 = alarm2;
	}	
}
