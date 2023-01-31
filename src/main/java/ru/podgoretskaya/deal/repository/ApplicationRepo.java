package ru.podgoretskaya.deal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.ClientEntity;

public interface ApplicationRepo extends CrudRepository<ApplicationEntity, Long> {
}
