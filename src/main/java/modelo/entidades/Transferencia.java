package modelo.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transferencia")
public class Transferencia extends Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cuentaOrigen")
	private Cuenta cuentaOrigen;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cuentaDestino")
	private Cuenta cuentaDestino;
	
	public Transferencia() {
		super();
	}
	
	public Transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		super();
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	
}
