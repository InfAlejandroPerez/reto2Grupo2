package modelo;
// Generated 14 ene 2022 15:27:48 by Hibernate Tools 5.5.7.Final

/**
 * Provincia generated by hbm2java
 */
public class Provincia implements java.io.Serializable {

	private String codProvincia;
	private String nombre;

	public Provincia() {
	}

	public Provincia(String codProvincia, String nombre) {
		this.codProvincia = codProvincia;
		this.nombre = nombre;
	}

	public String getCodProvincia() {
		return this.codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
