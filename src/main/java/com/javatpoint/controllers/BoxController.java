package com.javatpoint.controllers;

import java.util.List;
import java.util.Optional;
// import java.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javatpoint.models.Alarm;
import com.javatpoint.models.Box;
import com.javatpoint.models.Message;
import com.javatpoint.models.ParcelComplete_a2a;
import com.javatpoint.models.Telemetry;
import com.javatpoint.models.TimestampAlarm;
import com.javatpoint.models.TimestampTelemetry;
import com.javatpoint.repositories.AlarmRepository;
import com.javatpoint.repositories.BoxRepository;
import com.javatpoint.repositories.ParcelCompleteRepository;
import com.javatpoint.repositories.TelemetryRepository;
import com.javatpoint.repositories.TimestampAlarmRepository;
import com.javatpoint.repositories.TimestampTelemetryRepository;
//import org.springframework.web.bind.annotation.PutMapping;
import com.javatpoint.services.ParcelComplete_a2aService;
  

@RestController  
public class BoxController {
    private final BoxRepository boxRepository;
    private final MongoTemplate mongoTemplate;
    private final ParcelComplete_a2aService cCa2aService;
    private final ParcelCompleteRepository parcelCompleteRepository;
    private final TimestampTelemetryRepository tsTelemetryRepository;
    private final AlarmRepository alarmRepository;
    private final TimestampAlarmRepository tsAlarmRepository;

    private final Message default_message; //TODO delete

    public BoxController(BoxRepository boxRepository, 
                         MongoTemplate mongoTemplate,
                         ParcelComplete_a2aService cCa2aService,
                         ParcelCompleteRepository parcelCompleteRepository,
                         TimestampTelemetryRepository tsTelemetryRepository,
                         AlarmRepository alarmRepository,
                         TimestampAlarmRepository tsAlarmRepository) {
        this.boxRepository = boxRepository;
        this.mongoTemplate = mongoTemplate;
        this.cCa2aService = cCa2aService;
        this.parcelCompleteRepository = parcelCompleteRepository;
        this.tsTelemetryRepository = tsTelemetryRepository;
        this.alarmRepository = alarmRepository;
        this.tsAlarmRepository = tsAlarmRepository;
        
        default_message = new Message(false, false); //TODO delete
        default_message.setAck();
    }

    /* Returns all Box data */
    @GetMapping(value = "/boxes")
    public List<Box> getboxes()
    {
        return boxRepository.findAll();
    }

    /* Get a particular Box */
    @GetMapping(value = "/boxes/{mac}")
    public Box getBox(@PathVariable String mac)
    {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("mac").is(mac));
        
        return mongoTemplate.findOne(query, Box.class);
    }

    /* Post new Box */
    @PostMapping("/boxes")
    public Box createBox(@RequestBody Box newBox) {
        return boxRepository.save(newBox);
    }

    /* Get the requested operation */
    @GetMapping("/boxes/{mac}/idle")
    public ResponseEntity idleBox(@PathVariable String mac) {
        Box box = boxRepository.findOne(mac);
        if(box.getParcelComplete() != null) {
            ParcelComplete_a2a cCa2a = parcelCompleteRepository.findOneParcelComplete_a2a(box.getParcelComplete().getId().toString());
            Message systemMessage = cCa2aService.getMessage(cCa2a);
            return new ResponseEntity<Message>(systemMessage, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(default_message, null, HttpStatus.OK);
        }
    }

    /* Put response to the requested operation */
    @PostMapping("/boxes/{mac}/answer")
    public ResponseEntity idleBox(@PathVariable String mac, @RequestBody Message message) {
        Box box = boxRepository.findOne(mac);

        if(box != null && box.getParcelComplete() != null) {
            ParcelComplete_a2a cCa2a = parcelCompleteRepository.findOneParcelComplete_a2a(box.getParcelComplete().getId().toString());
            Message systemMessage = cCa2aService.setMessage(cCa2a, message);
            return new ResponseEntity<Message>(systemMessage, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(default_message, null, HttpStatus.OK);
        }
    }

     /* Post new telemetry */
     @PostMapping("/boxes/{mac}/telemetries")
     public ResponseEntity createTelemetryBox(@PathVariable String mac, @RequestBody Telemetry newTelemetry) {
        Box box = boxRepository.findOne(mac);

        if(box != null && box.getParcelComplete() != null) {
            /* Add new telemetry to the box and a parcel complete*///TODO change a2a to universal - ParcelComplete
            ParcelComplete_a2a cCa2a = parcelCompleteRepository.findOneParcelComplete_a2a(box.getParcelComplete().getId().toString());
            TimestampTelemetry tsTel = tsTelemetryRepository.save(new TimestampTelemetry(newTelemetry));
            parcelCompleteRepository.addTelemetry(box.getParcelComplete().getId().toString(), tsTel);
            boxRepository.addTelemetry(box.getMac().toString(), tsTel);

            Message systemMessage = cCa2aService.getMessage(cCa2a);
            return new ResponseEntity<Message>(systemMessage, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(default_message, null, HttpStatus.OK);
        }
     }

     /* Post new alarm */
     @PostMapping("/boxes/{mac}/alarms")
     public ResponseEntity createAlarmBox(@PathVariable String mac, @RequestBody Alarm newAlarm) {
        Box box = boxRepository.findOne(mac);

        if(box != null && box.getParcelComplete() != null) {
            /* Add new telemetry to the box and a parcel complete*///TODO change a2a to universal - ParcelComplete
            ParcelComplete_a2a pCa2a = parcelCompleteRepository.findOneParcelComplete_a2a(box.getParcelComplete().getId().toString());
            
            /* find alarm with code x */
            Alarm alarm = alarmRepository.find(newAlarm.getCode());
            
            TimestampAlarm tsAlarm = tsAlarmRepository.save(new TimestampAlarm(alarm));
            parcelCompleteRepository.addAlarm(box.getParcelComplete().getId().toString(), tsAlarm);
            boxRepository.addAlarm(box.getMac().toString(), tsAlarm);

            Message systemMessage = cCa2aService.getMessage(pCa2a);
            return new ResponseEntity<Message>(systemMessage, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(default_message, null, HttpStatus.OK);
        }
     }



    // @GetMapping("/boxes")
    // public Box createBox(@RequestBody Box newBox) {
    //     return boxRepository.save(newBox);
    // }
}