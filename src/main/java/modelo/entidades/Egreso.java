package modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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
	
	public Egreso(String descripcion, Date date, double valor, Cuenta cuenta, Categoria categoria, Subcategoria subcategoria) {
		super(descripcion, date, valor, cuenta);
		this.categoria = categoria;
		this.subcategoria = subcategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	@Override
	public String toString() {
		return this.categoria + "" + this.subcategoria ;
	}
	
}
