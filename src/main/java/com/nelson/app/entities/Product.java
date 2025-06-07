package com.nelson.app.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_product")
@Schema(description = "Entidade que representa um produto disponível para venda.")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único do produto", example = "1")
	private Long id;

	@Schema(description = "Nome do produto", example = "Smartphone XYZ")
	private String name;

	@Schema(description = "Descrição detalhada do produto", example = "Smartphone com tela de 6.5 polegadas e 128GB de armazenamento")
	private String description;

	@Schema(description = "Preço do produto", example = "599.99")
	private Double price;

	@Schema(description = "URL da imagem do produto", example = "http://example.com/images/product1.png")
	private String imgUrl;

	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@Schema(description = "Categorias às quais o produto pertence")
	private Set<Category> categories = new HashSet<>();

	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	@JsonIgnore
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
