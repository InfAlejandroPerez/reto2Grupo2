package modelo;
// Generated 27 ene 2022 20:20:27 by Hibernate Tools 5.5.7.Final

/**
 * EstaEnId generated by hbm2java
 */
public class EstaEnId implements java.io.Serializable {

	private String codMunicipio;
	private String codEspacio;

	public EstaEnId() {
	}

	public EstaEnId(String codMunicipio, String codEspacio) {
		this.codMunicipio = codMunicipio;
		this.codEspacio = codEspacio;
	}

	public String getCodMunicipio() {
		return this.codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getCodEspacio() {
		return this.codEspacio;
	}

	public void setCodEspacio(String codEspacio) {
		this.codEspacio = codEspacio;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EstaEnId))
			return false;
		EstaEnId castOther = (EstaEnId) other;

		return ((this.getCodMunicipio() == castOther.getCodMunicipio()) || (this.getCodMunicipio() != null
				&& castOther.getCodMunicipio() != null && this.getCodMunicipio().equals(castOther.getCodMunicipio())))
				&& ((this.getCodEspacio() == castOther.getCodEspacio())
						|| (this.getCodEspacio() != null && castOther.getCodEspacio() != null
								&& this.getCodEspacio().equals(castOther.getCodEspacio())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodMunicipio() == null ? 0 : this.getCodMunicipio().hashCode());
		result = 37 * result + (getCodEspacio() == null ? 0 : this.getCodEspacio().hashCode());
		return result;
	}

}
