package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.ApplicationDTO;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.ClientEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.mapper.ClientMapper;
import ru.podgoretskaya.deal.mapper.CreditMapper;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.repository.ClientRepo;
import ru.podgoretskaya.deal.repository.CreditRepo;
import ru.podgoretskaya.deal.util.HistiryManagerUtil;

import java.util.List;

import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.PREAPPROVAL;
import static ru.podgoretskaya.deal.mapper.ApplicationMapper.mapEntityToDTO;


@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    private final ClientMapper clientMapper;
    private final ClientRepo clientRepo;
    private final CreditMapper creditMapper;
    private final CreditRepo creditRepo;
    private final ApplicationRepo applicationRepo;
    private final ConveyorClient conveyorClient;

    @Override
    public List<LoanOfferDTO> calculateConditions(LoanApplicationRequestDTO model) {
        log.info("метод calculateConditions. Параметры: \"" + model.toString());
        saveApplicationToDB(model);
        return sentRequestToConveyorService(model);
    }

    private void saveApplicationToDB(LoanApplicationRequestDTO model) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        ClientEntity clientEntity = clientMapper.loanApplicationRequestDTOMapToEntity(model);
        clientRepo.save(clientEntity);
        CreditEntity creditEntity = creditMapper.loanApplicationRequestDTOMapToEntity(model);
        creditRepo.save(creditEntity);
        applicationEntity.setClient(clientEntity);
        applicationEntity.setCredit(creditEntity);
        applicationEntity.setStatus(PREAPPROVAL);//ApplicationStatus
        HistiryManagerUtil.updateStatus(applicationEntity, applicationEntity.getStatus());
        applicationRepo.save(applicationEntity);
    }

    private List<LoanOfferDTO> sentRequestToConveyorService(LoanApplicationRequestDTO model) {
        return conveyorClient.getOffersPages(model);
    }

    @Override
    public ApplicationDTO findApplicationById(Long applicationId) {
        ApplicationEntity applicationEntity = applicationRepo.findById(applicationId).orElseThrow(IllegalArgumentException::new);
        return mapEntityToDTO(applicationEntity);
    }
}
