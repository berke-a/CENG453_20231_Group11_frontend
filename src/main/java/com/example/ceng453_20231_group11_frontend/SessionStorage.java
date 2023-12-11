package com.example.ceng453_20231_group11_frontend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionStorage {
    private String username;
    private String token;

    private static SessionStorage instance;

    public static SessionStorage getInstance() {
        if (instance == null) {
            instance = new SessionStorage(null, null);
        }
        return instance;
    }

    public void clear() {
        this.username = null;
        this.token = null;
    }
}
