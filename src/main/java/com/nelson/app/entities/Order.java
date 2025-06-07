package com.nelson.app.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nelson.app.entities.enums.OrderStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_order")
@Schema(description = "Entidade que representa um pedido (Order) feito por um cliente.")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único do pedido", example = "1001")
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Schema(description = "Momento em que o pedido foi realizado", example = "2025-06-06T13:45:00Z")
	private Instant moment;

	@Schema(description = "Código numérico que representa o status do pedido", example = "1")
	private Integer orderStatus;

	@ManyToOne
	@JoinColumn(name = "client_id")
	@Schema(description = "Usuário que fez o pedido")
	private User client;

	@OneToMany(mappedBy = "id.order")
	@Schema(description = "Itens do pedido (relacionamento OneToMany)", accessMode = Schema.AccessMode.READ_ONLY)
	private Set<OrderItem> items = new HashSet<>();

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	@Schema(description = "Pagamento associado ao pedido", accessMode = Schema.AccessMode.READ_ONLY)
	private Payment payment;

	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Instant getMoment() { return moment; }
	public void setMoment(Instant moment) { this.moment = moment; }

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() { return client; }
	public void setClient(User client) { this.client = client; }

	public Payment getPayment() { return payment; }
	public void setPayment(Payment payment) { this.payment = payment; }

	public Set<OrderItem> getItems() { return items; }

	public Double getTotal() {
		double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return id != null && id.equals(other.id);
	}
}
