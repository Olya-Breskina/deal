package ru.podgoretskaya.deal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.podgoretskaya.deal.service.ApplicationServiceImpl;
import ru.podgoretskaya.deal.service.CalculateScoringDataServiceImpl;
import ru.podgoretskaya.deal.service.OfferServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(APIController.class)
class APIControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ApplicationServiceImpl applicationServiceImpl;
    @MockBean
    OfferServiceImpl offerServiceImpl;
    @MockBean
    CalculateScoringDataServiceImpl calculateScoringDataServiceImpl;

    @Test
    void getOffersPagesGoodOne() throws Exception {
        mockMvc.perform(post("/deal/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                            {
                                                "firstName":"qwe",
                                                "middleName":"qwe",
                                                "lastName":"qwe",
                                                "birthdate":"1994-12-13",
                                                "passportSeries":"1234",
                                                "passportNumber":"123456",
                                                "email":"a@mail.ru",
                                                "amount":100000,
                                                "term":12
                                            }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void getOffersPagesBadOne() throws Exception {
        when( applicationServiceImpl.calculateConditions(any())).thenThrow(new IllegalArgumentException());
        mockMvc.perform(post("/deal/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
   @Test
    void updateStatusHistoryGoodOne() throws Exception {
        mockMvc.perform(put("/deal/offer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                            "applicationId": 1,
                                            "requestedAmount": 100000,
                                            "totalAmount": 107115.48,
                                            "term": 6,
                                            "monthlyPayment": 17852.58,
                                            "rate": 24,
                                            "isInsuranceEnabled": false,
                                            "isSalaryClient": false
                                        }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void updateStatusGoodOne() throws Exception {
        mockMvc.perform(put("/deal/calculate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                            "gender": "FEMALE",
                                           "maritalStatus": "DIVORCED",
                                           "dependentAmount": "10",
                                            "passportIssueDate": "2015-01-14",
                                            "passportIssueBranch": "qazwsx",
                                            "employment": {
                                              "employmentStatus": "BUSINESS_OWNER",
                                              "employerINN": "123-456-789",
                                              "salary": 6000,
                                              "position": "MID_MANAGER",
                                              "workExperienceTotal": 7,
                                              "workExperienceCurrent": 6
                                            },
                                            "account": "qaz"
                                          }
                                """))
                .andExpect(status().isOk());
    }
    @Test
    void SedInvalidBody() throws Exception {
        mockMvc.perform(put("/deal/calculate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                            "gender": "123",
                                           "maritalStatus": "DIVORCED",
                                           "dependentAmount": "10",
                                            "passportIssueDate": "2015-01-14",
                                            "passportIssueBranch": "qazwsx",
                                            "employment": {
                                              "employmentStatus": "BUSINESS_OWNER",
                                              "employerINN": "123-456-789",
                                              "salary": 6000,
                                              "position": "MID_MANAGER",
                                              "workExperienceTotal": 7,
                                              "workExperienceCurrent": 6
                                            },
                                            "account": "qaz"
                                          }
                                """))
                .andExpect(status().isBadRequest());
    }

}