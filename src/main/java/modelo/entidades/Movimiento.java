package modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Movimiento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_movimiento", discriminatorType = DiscriminatorType.STRING)
public class Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "valor")
	private double valor;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cuenta")
	private Cuenta cuenta;
	
	public Movimiento() {}
	
	public Movimiento(String descripcion, String tipoMov ,Date fecha, double valor, Cuenta cuenta) {
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.valor = valor;
		this.cuenta = cuenta;
		this.tipoMovimiento = tipoMov;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	@Override
	public String toString() {
		return id + tipoMovimiento + descripcion + fecha + valor + cuenta;
	}
	
}
