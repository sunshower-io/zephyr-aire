package io.zephyr.aire;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous puser";
        } else {
            return "Hello " + name + "How are you?";
        }
    }

}
