package modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Cuenta")
public class Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String numeroCuenta;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "saldo")
	private double saldo;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "propietario")
	private Usuario propietario;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta")
	private List<Movimiento> movimientos;
	
	public Cuenta() {
	}
	
	public Cuenta(String numeroCuenta, String nombre, double saldo) {
		this.numeroCuenta = numeroCuenta;
		this.nombre = nombre;
		this.saldo = saldo;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return numeroCuenta + nombre + saldo;
	}
	
}
