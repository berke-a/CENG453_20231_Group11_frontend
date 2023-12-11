package com.example.ceng453_20231_group11_frontend.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.ceng453_20231_group11_frontend.constants.GeneralConstants.BACKEND_BASE_URL;

@Slf4j
@Service
public class UserService {

    public static Pair<Integer, String> register(String email, String username, String Password) {
            try {
            HttpResponse<JsonNode> apiResponse = Unirest.post(BACKEND_BASE_URL + "/users/register")
                    .queryString("email", email)
                    .queryString("username", username)
                    .queryString("password", Password)
                    .asJson();

            int statusCode = apiResponse.getStatus();
            String message = apiResponse.getBody().getObject().getString("message");

            return new Pair<>(statusCode, message);
        } catch (UnirestException e) {
            log.error("Error during registration: ", e);
            return new Pair<>(500, "Error registering");
        }
    }

    public static Pair<Integer, String> login(String username, String password) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post(BACKEND_BASE_URL + "/users/login")
                    .queryString("username", username)
                    .queryString("password", password)
                    .asJson();

            int statusCode = apiResponse.getStatus();
            String message = apiResponse.getBody().getObject().getString("message");

            return new Pair<>(statusCode, message);
        } catch (UnirestException e) {
            log.error("Error during login: ", e);
            return new Pair<>(500, "Error logging in");
        }
    }

    public static Pair<Integer, String> requestPasswordReset(String email) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post(BACKEND_BASE_URL + "/users/reset-password")
                    .queryString("email", email)
                    .asJson();

            int statusCode = apiResponse.getStatus();
            String message = apiResponse.getBody().getObject().getString("message");

            return new Pair<>(statusCode, message);
        } catch (UnirestException e) {
            log.error("Error during password reset request: ", e);
            return new Pair<>(500, "Error processing password reset request");
        }
    }

    public static Pair<Integer, String> changePassword(String token, String newPassword) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post(BACKEND_BASE_URL + "/users/change-password")
                    .queryString("token", token)
                    .queryString("newPassword", newPassword)
                    .asJson();

            int statusCode = apiResponse.getStatus();
            String message = apiResponse.getBody().getObject().getString("message");

            return new Pair<>(statusCode, message);
        } catch (UnirestException e) {
            log.error("Error during password change: ", e);
            return new Pair<>(500, "Error changing password");
        }
    }

}
