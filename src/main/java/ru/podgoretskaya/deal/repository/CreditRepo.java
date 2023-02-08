package ru.podgoretskaya.deal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.podgoretskaya.deal.entity.CreditEntity;

public interface CreditRepo extends CrudRepository<CreditEntity, Long> {
}
