package com.example.ceng453_20231_group11_frontend;

import javafx.scene.Parent;

import java.util.Stack;

public class NavigationHistoryManager {
    private static final Stack<Parent> navigationStack = new Stack<>();

    public static void push(Parent currentPage) {
        navigationStack.push(currentPage);
    }

    public static Parent pop() {
        return navigationStack.empty() ? null : navigationStack.pop();
    }

    public static void clear() {
        navigationStack.clear();
    }
}
