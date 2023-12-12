module com.example.ceng453_20231_group11_frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jakarta.ws.rs;
    requires static lombok;
    requires org.slf4j;
    requires spring.context;
    requires unirest.java;

    requires com.google.gson;
    requires java.sql;
    requires org.json;


    opens com.example.ceng453_20231_group11_frontend to javafx.fxml;
    exports com.example.ceng453_20231_group11_frontend;

    opens com.example.ceng453_20231_group11_frontend.controller to javafx.fxml;
    exports com.example.ceng453_20231_group11_frontend.controller;

    opens com.example.ceng453_20231_group11_frontend.service to javafx.fxml;
    exports com.example.ceng453_20231_group11_frontend.service;

    opens com.example.ceng453_20231_group11_frontend.models to com.google.gson;
    opens com.example.ceng453_20231_group11_frontend.enums to com.google.gson;

}