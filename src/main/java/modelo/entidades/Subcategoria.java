package modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Subcategoria")
public class Subcategoria extends Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaPadre")
	private Categoria categoriaPadre;

	public Subcategoria() {
		super();
	}
	
	public Subcategoria(int id, String nombre, Categoria categoriaPadre) {
		super(id, nombre);
		this.categoriaPadre = categoriaPadre;
	}

	public Categoria getCategoriaPadre() {
		return categoriaPadre;
	}

	public void setCategoriaPadre(Categoria categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}

}
