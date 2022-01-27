package modelo;
// Generated 27 ene 2022 20:20:27 by Hibernate Tools 5.5.7.Final

import java.math.BigDecimal;

/**
 * Datosindice generated by hbm2java
 */
public class Datosindice implements java.io.Serializable {

	private Integer idDatosindice;
	private String codEstacion;
	private String date;
	private String hourGmt;
	private BigDecimal comgm3;
	private BigDecimal co8hmgm3;
	private BigDecimal nogm3;
	private BigDecimal no2;
	private String no2ica;
	private BigDecimal noxgm3;
	private BigDecimal pm10;
	private String pm10ica;
	private BigDecimal pm25;
	private String pm25ica;
	private BigDecimal so2;
	private String so2ica;
	private String icaestacion;

	public Datosindice() {
	}

	public Datosindice(String codEstacion, String date, String hourGmt, BigDecimal comgm3, BigDecimal co8hmgm3,
			BigDecimal nogm3, BigDecimal no2, String no2ica, BigDecimal noxgm3, BigDecimal pm10, String pm10ica,
			BigDecimal pm25, String pm25ica, BigDecimal so2, String so2ica, String icaestacion) {
		this.codEstacion = codEstacion;
		this.date = date;
		this.hourGmt = hourGmt;
		this.comgm3 = comgm3;
		this.co8hmgm3 = co8hmgm3;
		this.nogm3 = nogm3;
		this.no2 = no2;
		this.no2ica = no2ica;
		this.noxgm3 = noxgm3;
		this.pm10 = pm10;
		this.pm10ica = pm10ica;
		this.pm25 = pm25;
		this.pm25ica = pm25ica;
		this.so2 = so2;
		this.so2ica = so2ica;
		this.icaestacion = icaestacion;
	}

	public Integer getIdDatosindice() {
		return this.idDatosindice;
	}

	public void setIdDatosindice(Integer idDatosindice) {
		this.idDatosindice = idDatosindice;
	}

	public String getCodEstacion() {
		return this.codEstacion;
	}

	public void setCodEstacion(String codEstacion) {
		this.codEstacion = codEstacion;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHourGmt() {
		return this.hourGmt;
	}

	public void setHourGmt(String hourGmt) {
		this.hourGmt = hourGmt;
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

	public BigDecimal getNo2() {
		return this.no2;
	}

	public void setNo2(BigDecimal no2) {
		this.no2 = no2;
	}

	public String getNo2ica() {
		return this.no2ica;
	}

	public void setNo2ica(String no2ica) {
		this.no2ica = no2ica;
	}

	public BigDecimal getNoxgm3() {
		return this.noxgm3;
	}

	public void setNoxgm3(BigDecimal noxgm3) {
		this.noxgm3 = noxgm3;
	}

	public BigDecimal getPm10() {
		return this.pm10;
	}

	public void setPm10(BigDecimal pm10) {
		this.pm10 = pm10;
	}

	public String getPm10ica() {
		return this.pm10ica;
	}

	public void setPm10ica(String pm10ica) {
		this.pm10ica = pm10ica;
	}

	public BigDecimal getPm25() {
		return this.pm25;
	}

	public void setPm25(BigDecimal pm25) {
		this.pm25 = pm25;
	}

	public String getPm25ica() {
		return this.pm25ica;
	}

	public void setPm25ica(String pm25ica) {
		this.pm25ica = pm25ica;
	}

	public BigDecimal getSo2() {
		return this.so2;
	}

	public void setSo2(BigDecimal so2) {
		this.so2 = so2;
	}

	public String getSo2ica() {
		return this.so2ica;
	}

	public void setSo2ica(String so2ica) {
		this.so2ica = so2ica;
	}

	public String getIcaestacion() {
		return this.icaestacion;
	}

	public void setIcaestacion(String icaestacion) {
		this.icaestacion = icaestacion;
	}

}
