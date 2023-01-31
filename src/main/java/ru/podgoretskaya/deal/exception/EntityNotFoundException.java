package ru.podgoretskaya.deal.exception;

public class EntityNotFoundException extends RuntimeException{
    private final Long id;


    public EntityNotFoundException(Long id) {
        this.id = id;
    }
    @Override
    public String getMessage() {
        return "Entity with id = " + id + " not found";
    }
}
