package model;

public class Config {
		
	private Integer id;
	private String detail;
	private String tipo;
	private String unidade;
	private Double minValue;
	private Double maxValue;
	private Double alarm1;
	private Double alarm2;	
	
	public Config() {		
	}
	
	public Config(
			int id,			 
			String tipo,
			String unidade,
			Double minValue, 
			Double maxValue,
			Double alarm1,
			Double alarm2,
			String detail						
			) {		
		
		this.id = id;		
		this.tipo = tipo;
		this.unidade = unidade;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.alarm1 = alarm1;
		this.alarm2 = alarm2;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		
		Config other = (Config) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}	
}
