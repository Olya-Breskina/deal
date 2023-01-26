package ru.podgoretskaya.deal.exception;

public class EntityeNotFoundException extends RuntimeException{
    private final Long id;


    public EntityeNotFoundException(Long id) {
        this.id = id;
    }
    @Override
    public String getMessage() {
        return "Entity with id = " + id + " not found";
    }
}
