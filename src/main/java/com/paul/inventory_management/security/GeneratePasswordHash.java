package com.paul.inventory_management.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePasswordHash {
    public static void main(String[] args) {
        System.out.println(
            new BCryptPasswordEncoder().encode("1234")
        );
    }
}
