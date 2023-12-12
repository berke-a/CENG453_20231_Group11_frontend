package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.TileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tile {
    private TileType tileType;
    private Integer numberToken;

    @Override
    public String toString() {
        return String.format("Tile with type %s and number token %d", tileType, numberToken);
    }
}
