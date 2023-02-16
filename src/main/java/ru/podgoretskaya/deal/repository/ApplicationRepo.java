package ru.podgoretskaya.deal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.podgoretskaya.deal.entity.ApplicationEntity;

public interface ApplicationRepo extends CrudRepository<ApplicationEntity, Long> {
}
