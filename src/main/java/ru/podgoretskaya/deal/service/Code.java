package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.entity.ApplicationEntity;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class Code {
    public void sesCode(ApplicationEntity applicationEntity){
        int [] sesCodeArray=new int[4];
        for (int i=0; i<4; i++){
            Random random=new Random();
            int x= random.nextInt(9);
            sesCodeArray[i]=x;
        }
        String sesCode=""+sesCodeArray[0]+sesCodeArray[1]+sesCodeArray[2]+sesCodeArray[3];
        applicationEntity.setSesCode(sesCode);
    }
}
