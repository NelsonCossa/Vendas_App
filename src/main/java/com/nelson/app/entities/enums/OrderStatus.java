package com.nelson.app.entities.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estado do pedido")
public enum OrderStatus {

    @Schema(description = "Aguardando pagamento", example = "1")
    WAITING_PAYMENT(1),

    @Schema(description = "Pago", example = "2")
    PAID(2),

    @Schema(description = "Enviado", example = "3")
    SHIPPED(3),

    @Schema(description = "Entregue", example = "4")
    DELIVERED(4),

    @Schema(description = "Cancelado", example = "5")
    CANCELED(5);

    private final int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code: " + code);
    }
}
