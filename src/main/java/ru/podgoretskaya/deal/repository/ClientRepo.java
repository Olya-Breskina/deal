package ru.podgoretskaya.deal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.podgoretskaya.deal.entity.ClientEntity;

public interface ClientRepo extends CrudRepository<ClientEntity, Long> {
}
