package com.example.ceng453_20231_group11_frontend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    private ResourceType type;
    private int quantity = 0;

    public void add(int amount) {
        this.quantity += amount;
    }

    public void subtract(int amount) {
        this.quantity = Math.max(0, this.quantity - amount);
    }

    @Override
    public String toString() {
        return String.format("Resource with type %s", type);
    }
}
