package dev.dren.linkplus.assignment;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WelcomeMessage {
    public String getWelcomeMessage() {
        return "Welcome to LinkPlus!";
    }
}
