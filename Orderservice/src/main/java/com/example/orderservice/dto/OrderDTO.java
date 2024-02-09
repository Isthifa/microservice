package com.example.orderservice.dto;

public class OrderDTO {

        private String productName;
        private int quantity;
        private String isOrderConfirmed;

        public OrderDTO() {
        }

        public OrderDTO(String productName, int quantity, String isOrderConfirmed) {
            this.productName = productName;
            this.quantity = quantity;
            this.isOrderConfirmed = isOrderConfirmed;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getIsOrderConfirmed() {
            return isOrderConfirmed;
        }
}
