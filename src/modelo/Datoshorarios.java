package modelo;
// Generated 28 ene 2022 16:46:24 by Hibernate Tools 5.5.7.Final

import java.math.BigDecimal;

/**
 * Datoshorarios generated by hbm2java
 */
public class Datoshorarios implements java.io.Serializable {

	private Integer idDatoshorarios;
	private String codEstacion;
	private String fecha;
	private String hora;
	private BigDecimal comgm3;
	private BigDecimal co8hmgm3;
	private BigDecimal nogm3;
	private BigDecimal no2gm3;
	private BigDecimal noxgm3;
	private BigDecimal pm10gm3;
	private BigDecimal pm25gm3;
	private BigDecimal so2gm3;

	public Datoshorarios() {
	}

	public Datoshorarios(String codEstacion, String fecha, String hora, BigDecimal comgm3, BigDecimal co8hmgm3,
			BigDecimal nogm3, BigDecimal no2gm3, BigDecimal noxgm3, BigDecimal pm10gm3, BigDecimal pm25gm3,
			BigDecimal so2gm3) {
		this.codEstacion = codEstacion;
		this.fecha = fecha;
		this.hora = hora;
		this.comgm3 = comgm3;
		this.co8hmgm3 = co8hmgm3;
		this.nogm3 = nogm3;
		this.no2gm3 = no2gm3;
		this.noxgm3 = noxgm3;
		this.pm10gm3 = pm10gm3;
		this.pm25gm3 = pm25gm3;
		this.so2gm3 = so2gm3;
	}

	public Integer getIdDatoshorarios() {
		return this.idDatoshorarios;
	}

	public void setIdDatoshorarios(Integer idDatoshorarios) {
		this.idDatoshorarios = idDatoshorarios;
	}

	public String getCodEstacion() {
		return this.codEstacion;
	}

	public void setCodEstacion(String codEstacion) {
		this.codEstacion = codEstacion;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public BigDecimal getComgm3() {
		return this.comgm3;
	}

	public void setComgm3(BigDecimal comgm3) {
		this.comgm3 = comgm3;
	}

	public BigDecimal getCo8hmgm3() {
		return this.co8hmgm3;
	}

	public void setCo8hmgm3(BigDecimal co8hmgm3) {
		this.co8hmgm3 = co8hmgm3;
	}

	public BigDecimal getNogm3() {
		return this.nogm3;
	}

	public void setNogm3(BigDecimal nogm3) {
		this.nogm3 = nogm3;
	}

	public BigDecimal getNo2gm3() {
		return this.no2gm3;
	}

	public void setNo2gm3(BigDecimal no2gm3) {
		this.no2gm3 = no2gm3;
	}

	public BigDecimal getNoxgm3() {
		return this.noxgm3;
	}

	public void setNoxgm3(BigDecimal noxgm3) {
		this.noxgm3 = noxgm3;
	}

	public BigDecimal getPm10gm3() {
		return this.pm10gm3;
	}

	public void setPm10gm3(BigDecimal pm10gm3) {
		this.pm10gm3 = pm10gm3;
	}

	public BigDecimal getPm25gm3() {
		return this.pm25gm3;
	}

	public void setPm25gm3(BigDecimal pm25gm3) {
		this.pm25gm3 = pm25gm3;
	}

	public BigDecimal getSo2gm3() {
		return this.so2gm3;
	}

	public void setSo2gm3(BigDecimal so2gm3) {
		this.so2gm3 = so2gm3;
	}

}
