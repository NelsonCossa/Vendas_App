package com.nelson.app.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_category")
@Schema(description = "Entidade que representa uma categoria de produtos.")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único da categoria", example = "1", required = true)
	private Long id;

	@Schema(description = "Nome da categoria", example = "Eletrônicos", required = true)
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	@Schema(description = "Lista de produtos associados a esta categoria (relação ManyToMany)", accessMode = Schema.AccessMode.READ_ONLY)
	private Set<Product> products = new HashSet<>();
	
	public Category() {}

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Set<Product> getProducts() { return products; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Category other = (Category) obj;
		return id != null && id.equals(other.id);
	}
}
