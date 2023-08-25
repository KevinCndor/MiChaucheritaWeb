package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Egreso")
@DiscriminatorValue("Egreso")
public class Egreso extends Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "categoria")
	private Categoria categoria;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "subcategoria")
	private Subcategoria subcategoria;
	
	public Egreso() {
		super();
	}
	
	public Egreso(int id, String descripcion, LocalDate date, double valor,Cuenta cuenta, Categoria categoria) {
		super(id, descripcion, date, valor, cuenta);
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
