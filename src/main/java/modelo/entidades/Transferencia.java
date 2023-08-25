package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;

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
	@JoinColumn(name = "cuentaDestino")
	private Cuenta cuentaDestino;
	
	public Transferencia() {
		super();
	}
	
	public Transferencia(int id, String descripcion, LocalDate date, double valor,Cuenta cuenta, Cuenta cuentaDestino) {
		super(id, descripcion, date, valor, cuenta);
		this.cuentaDestino = cuentaDestino;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	
}
