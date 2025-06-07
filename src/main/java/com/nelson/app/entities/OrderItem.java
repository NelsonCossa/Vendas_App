package com.nelson.app.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelson.app.entities.pk.OrderItemPK;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_order_item")
@Schema(description = "Entidade que representa um item de um pedido (OrderItem), associando um produto a uma encomenda com preço e quantidade.")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Schema(description = "Chave primária composta que associa um pedido a um produto.")
	private OrderItemPK id = new OrderItemPK();

	@Schema(description = "Quantidade de produtos neste item de pedido", example = "2")
	private Integer quantity;

	@Schema(description = "Preço unitário do produto no momento do pedido", example = "39.90")
	private Double price;

	public OrderItem() {
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	@JsonIgnore
	@Schema(description = "Pedido associado a este item", accessMode = Schema.AccessMode.READ_ONLY)
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	@Schema(description = "Produto associado a este item", accessMode = Schema.AccessMode.READ_ONLY)
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Schema(description = "Subtotal calculado (preço x quantidade)", example = "79.80", accessMode = Schema.AccessMode.READ_ONLY)
	public Double getSubTotal() {
		return price * quantity;
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
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		OrderItem other = (OrderItem) obj;
		return id != null && id.equals(other.id);
	}
}
