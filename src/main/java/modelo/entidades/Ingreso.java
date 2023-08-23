package modelo.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ingreso")
public class Ingreso extends Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "categoria")
	private Categoria categoria;
	
	public Ingreso() {
		super();
	}
	
	public Ingreso(Categoria categoria) {
		super();
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
