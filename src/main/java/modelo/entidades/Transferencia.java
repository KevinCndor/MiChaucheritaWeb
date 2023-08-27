package modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transferencia extends Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cuentaDestino")
	private Cuenta cuentaDestino;
	
	public Transferencia() {
		super();
	}
	
	public Transferencia(String descripcion, String tipoMov ,Date date, double valor,Cuenta cuenta, Cuenta cuentaDestino) {
		super(descripcion, tipoMov, date, valor, cuenta);
		this.cuentaDestino = cuentaDestino;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	@Override
	public String toString() {
		return cuentaDestino + "";
	}
	
}
