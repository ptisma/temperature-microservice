package com.rasus.lab.temperaturemicroservice.rest;

import com.rasus.lab.temperaturemicroservice.model.Measurement;
import com.rasus.lab.temperaturemicroservice.repo.MeasurementRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@RestController
@RefreshScope
public class MeasurementController {

    @Autowired
    MeasurementRepository repository;


    @GetMapping("/current-reading")
    public @ResponseBody ResponseEntity<Integer> getReadings(){
        DateTime dt = new DateTime();
        int id = 4*dt.getHourOfDay() + dt.getMinuteOfHour()/15;
        Optional<Measurement> m  = repository.findById(id);
        return new ResponseEntity<>(m.get().getValue(), HttpStatus.OK);
    }



    public void insertReadings() throws IOException {
        BufferedReader fr = new BufferedReader(new FileReader("src/main/resources/mjerenja[4][1].csv"));
        String line;
        int humidity;
        fr.readLine();
        int idCounter = 0;
        while((line=fr.readLine())!=null){
            String part = line.split(",")[0];
            humidity = part.equals("") ? 0 : Integer.valueOf(part);
            repository.save(new Measurement(idCounter++,humidity));
        }
    }
}