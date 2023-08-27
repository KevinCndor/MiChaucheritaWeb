package modelo.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Subcategoria extends Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "categoriaPadre")
	private Categoria categoriaPadre;

	public Subcategoria() {
		super();
	}
	
	public Subcategoria(String nombre, Categoria categoriaPadre, String tipo) {
		super(nombre, tipo);
		this.categoriaPadre = categoriaPadre;
	}

	public Categoria getCategoriaPadre() {
		return categoriaPadre;
	}

	public void setCategoriaPadre(Categoria categoria) {
		this.categoriaPadre = categoria;
	}

	@Override
	public String toString() {
		return  this.getNombre();
	}

}
