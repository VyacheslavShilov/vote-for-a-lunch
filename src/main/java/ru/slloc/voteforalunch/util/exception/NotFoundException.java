package ru.slloc.voteforalunch.util.exception;


import org.springframework.lang.NonNull;

public class NotFoundException extends RuntimeException {
    public NotFoundException(@NonNull String message) {
        super(message);
    }
}