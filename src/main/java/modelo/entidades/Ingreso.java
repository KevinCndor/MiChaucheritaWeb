package modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ingreso extends Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "categoria")
	private Categoria categoria;
	
	public Ingreso() {
		super();
	}
	
	public Ingreso(String descripcion, String tipoMov,Date date, double valor,Cuenta cuenta, Categoria categoria) {
		super(descripcion, tipoMov,date, valor, cuenta);
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return categoria + "";
	}
	
}
