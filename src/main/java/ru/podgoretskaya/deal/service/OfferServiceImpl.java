package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.mapper.CreditMapper;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.repository.CreditRepo;

import static ru.podgoretskaya.deal.entityEnum.ApplicationStatus.PREAPPROVAL;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final CreditMapper creditMapper;
    private final CreditRepo creditRepo;
    private final ApplicationRepo applicationRepo;
    private final ConveyorClient conveyorClient;

    @Override
    public void calculateConditions(LoanOfferDTO model) {
        ApplicationEntity applicationEntity = applicationRepo.findById(String.valueOf(model.getApplicationId())).get();//что делать если нет id дописать
        applicationEntity.setStatus(PREAPPROVAL);
     //история
      //  applicationEntity.setAppliedOffer(model);
        applicationRepo.save(applicationEntity);

    }

}
