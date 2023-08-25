package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
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
	@Column(name = "fecha")
	private LocalDate fecha;
	@Column(name = "valor")
	private double valor;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cuenta")
	private Cuenta cuenta;
	
	public Movimiento() {}
	
	public Movimiento(int id, String descripcion, LocalDate date, double valor,Cuenta cuenta) {
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = date;
		this.valor = valor;
		this.cuenta = cuenta;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate date) {
		this.fecha = date;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return id + descripcion + fecha + valor;
	}
	
}
