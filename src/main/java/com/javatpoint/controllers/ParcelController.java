package com.javatpoint.controllers;

// import java.lang.reflect.Array;
// import java.time.LocalDateTime;
import java.util.ArrayList;
// import java.util.Collection;
// import java.util.HashMap;
import java.util.List;  
// import java.util.Optional;

import org.bson.types.ObjectId;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
// import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.javatpoint.models.Parcel;
import com.javatpoint.models.ParcelBrief;
import com.javatpoint.models.ParcelComplete;
import com.javatpoint.models.ParcelComplete_a2a;
import com.javatpoint.models.Region;
import com.javatpoint.models.Status;
// import com.javatpoint.models.Telemetry;
// import com.javatpoint.models.TimestampObject;
import com.javatpoint.models.TimestampStatus;
// import com.javatpoint.models.Alarm;
import com.javatpoint.models.User;
import com.javatpoint.models.Box;
// import com.javatpoint.models.Box;
// import com.javatpoint.models.DeliveryStatus;
import com.javatpoint.models.ErrorMessage;
import com.javatpoint.repositories.BoxRepository;
import com.javatpoint.repositories.ParcelBriefRepository;
import com.javatpoint.repositories.ParcelCompleteRepository;
// import com.javatpoint.services.IParcelService;  
import com.javatpoint.repositories.ParcelRepository;
import com.javatpoint.repositories.RegionRepository;
import com.javatpoint.repositories.StatusRepository;
import com.javatpoint.repositories.TimestampStatusRepository;
import com.javatpoint.repositories.UserRepository;


@RestController
public class ParcelController {
    private final MongoTemplate mongoTemplate;
    private final ParcelRepository parcelRepository;
    private final ParcelBriefRepository parcelBriefRepository;
    private final ParcelCompleteRepository parcelCompleteRepository;
    private final StatusRepository statusRepository;
    private final TimestampStatusRepository tsStatusRepository;
    private final UserRepository userRepository;
    private final BoxRepository boxRepository;
    private final RegionRepository regionRepository;
    
    public ParcelController(ParcelRepository parcelRepository, 
                            MongoTemplate mongoTemplate,
                            ParcelBriefRepository parcelBriefRepository,
                            ParcelCompleteRepository parcelCompleteRepository,
                            StatusRepository statusRepository,
                            TimestampStatusRepository tsStatusRepository,
                            UserRepository userRepository,
                            BoxRepository boxRepository,
                            RegionRepository regionRepository) {
        //super();
        this.parcelRepository = parcelRepository;
        this.mongoTemplate = mongoTemplate;
        this.parcelBriefRepository = parcelBriefRepository;
        this.parcelCompleteRepository = parcelCompleteRepository;
        this.statusRepository = statusRepository;
        this.tsStatusRepository = tsStatusRepository;
        this.userRepository = userRepository;
        this.boxRepository = boxRepository;
        this.regionRepository = regionRepository;
    }

    /* Brief data of all sender's parcels */
    @GetMapping(value = "/users/{login}/senders/parcels")
    public List<ParcelBrief> getSendersParcels(@PathVariable String login)
    {
        List<ParcelComplete> pC = parcelCompleteRepository.findSendersParcelCompletes(login);
        List<ParcelBrief> parcelBriefArr = new ArrayList<ParcelBrief>();

        for (ParcelComplete parcelComplete : pC) {
            parcelBriefArr.add(new ParcelBrief(parcelComplete.getParcel(), parcelCompleteRepository.getLastTelemetry(parcelComplete.getId().toString()),
            parcelCompleteRepository.getLastAlarm(parcelComplete.getId().toString()), parcelCompleteRepository.getLastStatus(parcelComplete.getId().toString())));
        }

        return parcelBriefArr;
    }

// TODO to co wyżej dla recevier i courier

    /* Expanded data of a parcel */
    @GetMapping(value = "/parcels/{id}")
    public ParcelComplete getParcelComplete(@PathVariable String id)
    {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("parcel").is(id));//parcel.id
        
        return mongoTemplate.findOne(query, ParcelComplete.class);
    }

    @GetMapping(value = "/parcels")
    public List<Parcel> getTelemetries()   
    {   
        List<Parcel> telemetries = parcelRepository.findAll();  
       
        return telemetries;
    } 

    // find parcels by region (city)
    // @GetMapping(value = "/regions/{cityName}/parcels")
    // public List<Parcel> getParcelsbyCity(@PathVariable String cityName)  // Wroclaw
    // {
    //     return parcelRepository.findParcelsbyCity(cityName);
    // }

    /* Create new parcel of a2a type - sender 
     * Parcel waits for a courier to take the job - code 1
    */
    @PostMapping("/parcels/a2a")
    public ResponseEntity createParcel(@RequestBody Parcel newParcel) {
        // System.out.println();
        // Parcel rec = parcelRepository.save(newParcel);

        if(newParcel.getSender() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Sender is null"), null, HttpStatus.BAD_REQUEST);
        else if(newParcel.getReceiver() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Receiver is null"), null, HttpStatus.BAD_REQUEST);

        User sender = userRepository.findOne(newParcel.getSender().getLogin());
        User receiver = userRepository.findOne(newParcel.getReceiver().getLogin());
        
        if(sender == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find sender: " + newParcel.getSender().getLogin()), null, HttpStatus.NOT_FOUND);
        else if(receiver == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find receiver: " + newParcel.getReceiver().getLogin()), null, HttpStatus.NOT_FOUND);
        else if(newParcel.getDeliverFrom() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Source wasn't specyfied"), null, HttpStatus.NOT_FOUND);
        else if(newParcel.getDeliverTo() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Destination wasn't specyfied"), null, HttpStatus.NOT_FOUND);
        else if(newParcel.deliverTo.getRegion() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Source region is null"), null, HttpStatus.BAD_REQUEST);
        else if(newParcel.deliverFrom.getRegion()  == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Destination region is null"), null, HttpStatus.BAD_REQUEST);

        Region sourceRegion = regionRepository.findOneRegionByPostalCode(newParcel.getDeliverFrom().getRegion().getPostalCode());
        Region destinationRegion = regionRepository.findOneRegionByPostalCode(newParcel.getDeliverTo().getRegion().getPostalCode());

        if(sourceRegion == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find source region"), null, HttpStatus.NOT_FOUND);
        else if(destinationRegion == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find destination region"), null, HttpStatus.NOT_FOUND);

        newParcel.setSender(sender);
        newParcel.setReceiver(receiver);
        newParcel.setCourier(null);
        newParcel.setBox(null);
        newParcel.deliverTo.setRegion(sourceRegion);
        newParcel.deliverFrom.setRegion(destinationRegion);

        Parcel rec = parcelRepository.save(newParcel);

        /* Create ParcelComplete and add status 1 */
        ParcelComplete_a2a pCa2a = parcelCompleteRepository.save(new ParcelComplete_a2a(rec));

        Status stat = statusRepository.find(1);
        TimestampStatus tsStat = tsStatusRepository.save(new TimestampStatus(stat));
        parcelCompleteRepository.addStatus(pCa2a.getId().toString(), tsStat);
        
        /* Return Parcel */
        return new ResponseEntity<ParcelComplete>(parcelCompleteRepository.findOne(pCa2a.getId().toString()), null, HttpStatus.OK);
    }

    /* Accept a job - courier 
     * Parcel waits for a courier to arrive - code 3
    */
    // @PutMapping("/parcels/a2a/{id}/courier/1/accept")
    // public ResponseEntity courierAcceptParcel(@PathVariable String id, @RequestBody User newCourier) {
    //     /* Find parcel */
    //     ParcelComplete pC = parcelCompleteRepository.findOneByParcel(id);
    //     User courier = userRepository.findOne(newCourier.getLogin());
    //     /* Get last status */
    //     TimestampStatus tsStat1 = parcelCompleteRepository.getLastStatus(id);

    //     if(pC == null)
    //         return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find parcel with id: " + id), null, HttpStatus.NOT_FOUND);
    //     else if(courier == null) 
    //         return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find courier with login: " + newCourier.getLogin()), null, HttpStatus.NOT_FOUND);
    //     else if(pC.getParcel().getCourier() != null)
    //         return new ResponseEntity<ErrorMessage>(new ErrorMessage("This parcel already has a courier"), null, HttpStatus.METHOD_NOT_ALLOWED);
    //         else if(tsStat1.getStatus().getCode() != 1)
    //     return new ResponseEntity<ErrorMessage>(new ErrorMessage("This method is not allowed while parcel has status: " + tsStat1.getStatus().getStatus()), null, HttpStatus.METHOD_NOT_ALLOWED);
        
    //         /* Get id of the status code 3 */
    //     Status stat = statusRepository.find(3);
    //     TimestampStatus tsStat = tsStatusRepository.save(new TimestampStatus(stat));//TODO
    //     parcelCompleteRepository.addStatus(pC.getId().toString(), tsStat);
    //     parcelCompleteRepository.setCourier(pC.getParcel().getId().toString(), courier);//TODO move to ParcelRepository

    //     return new ResponseEntity<ParcelComplete>(parcelCompleteRepository.findOne(pC.getId().toString()), null, HttpStatus.OK);
    // }

    /* Assign a box to the parcel - courier
     * Parcel waits for a courier to arrive - set code 3
     */
    @PutMapping("/parcels/a2a/{id}/courier/3/box/assign")
    public ResponseEntity courierAssignParcel(@PathVariable String id, @RequestBody Box newBox) {
        /* Assign a box to the parcel */
        ParcelComplete parcelComplete = parcelCompleteRepository.findOneByParcel(id);
        Box box = boxRepository.findOne(newBox.getMac());
        /* Get last status */
        TimestampStatus tsStat1 = parcelCompleteRepository.getLastStatus(id);

        if(parcelComplete == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find parcel with id: " + id), null, HttpStatus.NOT_FOUND);
        else if(box == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find box with MAC: " + newBox.getMac()), null, HttpStatus.NOT_FOUND);
        else if(parcelComplete.getParcel().getBox() != null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Some box has already been assigned"), null, HttpStatus.METHOD_NOT_ALLOWED);
        else if(tsStat1.getStatus().getCode() != 3)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("This method is not allowed while parcel has status: " + tsStat1.getStatus().getStatus()), null, HttpStatus.METHOD_NOT_ALLOWED);
        
        boxRepository.setParcelComplete(box.getMac(), parcelComplete);
        parcelRepository.setBox(id, box);
        return new ResponseEntity<Parcel>(parcelRepository.findOne(id), null, HttpStatus.OK);
    }
    
    /* Open the box that has been assigned to the parcel - courier
     * ?
     */
    @PutMapping("/parcels/a2a/{id}/courier/3/box/open")
    public ResponseEntity courierOpenParcel(@PathVariable String id) {
        /* Find parcel complete */
        ParcelComplete parcelComplete = parcelCompleteRepository.findOneByParcel(id);
        /* Get last status */
        TimestampStatus tsStat1 = parcelCompleteRepository.getLastStatus(id);

        if(parcelComplete == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Could not find parcel with id: " + id), null, HttpStatus.NOT_FOUND);
        else if(parcelComplete.getParcel().getBox() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("No box was assigned to this parcel, so it cannot be opened"), null, HttpStatus.METHOD_NOT_ALLOWED);
        else if(tsStat1.getStatus().getCode() != 3)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("This method is not allowed while parcel has status: " + tsStat1.getStatus().getStatus()), null, HttpStatus.METHOD_NOT_ALLOWED);

        /* Set courier open */
        parcelCompleteRepository.setA2a_code3_idle_open_courier_agree(parcelComplete.getId().toString());
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Message to box is being sent"), null, HttpStatus.OK);
    }

    // /* Confirm that box has been closed - sender
    //  * Parcel awaits to be delivered to receiver - set code 5
    //  * Box open status should be cleaned by now
    //  */
    // @PutMapping("/parcels/a2a/{id}/sender/3/box/protect")
    // public ParcelComplete senderCloseParcel(@PathVariable String id) {
    //     // /* Find parcel */
    //     // Query query = new Query();
    //     // query.addCriteria(Criteria.where("id").is(id));
    //     // Parcel parcel = mongoTemplate.findOne(query, Parcel.class);

    //     /* Get full parcel data */
    //     Query query1 = new Query();
    //     query1.addCriteria(Criteria.where("parcel").is(id));
    //     ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

    //     /* Get status code and the box */
    //     TimestampStatus obj = recComplete.getLastStatus();
    //     Status stat = obj.getStatus();
    //     Parcel parcel = recComplete.getParcel();

    //     /* Find box */
    //     // Query boxQuery = new Query();
    //     // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
    //     // Box box = mongoTemplate.findOne(boxQuery, Box.class);

    //     /* Check if operation is allowed; check if code == 3 and lid was opened than closed */
    //     if(stat.getCode() == 3 && recComplete.a2a_code3_protect_box_opened_closed == true) {
    //         // /* Get id of the status code 5 */
    //         // Query query0 = new Query();
    //         // query0.addCriteria(Criteria.where("code").is(5));
    //         // Status newStat = mongoTemplate.findOne(query0, Status.class);
            
    //         // /* Add new status to parcel complete */
    //         // Update update1 = new Update().addToSet("status", new TimestampStatus(newStat));
    //         // recComplete = mongoTemplate.findAndModify(query1, update1, ParcelComplete.class);

    //         /* Confirm that box has been closed && change state to protected */
    //         Update update1 = new Update().set("a2a_code3_protect_sender_agree", true); //protect delivery
    //         recComplete = mongoTemplate.findAndModify(query1, update1, ParcelComplete_a2a.class);
    //     }
        
    //     return recComplete;
    // }

//     /* Box was delivered to receiver - courier
//      * ?
//      */
//     @PutMapping("/parcels/a2a/{id}/courier/5/box/open")
//     public Box courierDeliverParcel(@PathVariable String id) {
//         // Query query = new Query();
//         // query.addCriteria(Criteria.where("id").is(id));
//         // Parcel parcel = mongoTemplate.findOne(query, Parcel.class);

//         /* Get full parcel data */
//         Query query1 = new Query();
//         query1.addCriteria(Criteria.where("parcel").is(id));
//         ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

//         /* Find box */
//         // Query boxQuery = new Query();
//         // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
//         // Box box = mongoTemplate.findOne(boxQuery, Box.class);
//         TimestampStatus obj = recComplete.getLastStatus();
//         Status stat = obj.getStatus();
//         Parcel parcel = recComplete.getParcel();
//         Box box = parcel.getBox();

//         if(stat.getCode() == 5) {
//             /* Tell box to open itself */
//             // Query boxQuery = new Query();
//             // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
//             Update update = new Update().set("a2a_code5_idle_open_courier_agree", true);
//             recComplete = mongoTemplate.findAndModify(query1, update, ParcelComplete_a2a.class);
//         }
//         return box;
//     }

//     /* Open the box after it was delivered to access the goods - receiver
//      * 
//      */
//     @PutMapping("/parcels/a2a/{id}/receiver/5/box/open")
//     public Box receiverOpenParcel(@PathVariable String id) {
//         // Query query = new Query();
//         // query.addCriteria(Criteria.where("id").is(id));
//         // Parcel parcel = mongoTemplate.findOne(query, Parcel.class);

//         // /* Find box */
//         // Query boxQuery = new Query();
//         // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
//         // Box box = mongoTemplate.findOne(boxQuery, Box.class);
//         /* Get full parcel data */
//         Query query1 = new Query();
//         query1.addCriteria(Criteria.where("parcel").is(id));
//         ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

//         /* Find box */
//         TimestampStatus obj = recComplete.getLastStatus();
//         Status stat = obj.getStatus();
//         Parcel parcel = recComplete.getParcel();
//         Box box = parcel.getBox();

//         if(stat.getCode() == 5) {
//             /* Tell box to open itself */
//             // Query boxQuery = new Query();
//             // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
//             Update update = new Update().set("a2a_code5_idle_open_receiver_agree", true);
//             recComplete = mongoTemplate.findAndModify(query1, update, ParcelComplete_a2a.class);
//         }

//         return box;
//     }

//     /* End transaction - courier
//      * ?
//      */
//     // @PutMapping("/parcels/a2a/{id}/courier/end")
//     // public Parcel courierEndParcel() {
//     //     // newParcel.setCourier(null);
//     //     // Parcel rec = parcelRepository.save(newParcel);
//     //     // parcelCompleteRepository.save(new ParcelComplete(rec, null, null, null));

//     //     return rec;
//     // }

//     /* End transaction - receiver
//      * 
//      */
//     @PutMapping("/parcels/a2a/{id}/receiver/5/end")
//     public ParcelComplete receiverEndParcel(@PathVariable String id) {
//         /* Get full parcel data */
//         Query query1 = new Query();
//         query1.addCriteria(Criteria.where("parcel").is(id));
//         ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

//         /* Find box */
//         TimestampStatus obj = recComplete.getLastStatus();
//         Status stat = obj.getStatus();
//         Parcel parcel = recComplete.getParcel();
//         Box box = parcel.getBox();

//         if(stat.getCode() == 5 && recComplete.a2a_code5_end_box_opened_closed == true) {
//             /* Get id of the status code 3 */
//             Query query0 = new Query();
//             query0.addCriteria(Criteria.where("code").is(5));
//             Status newStat = mongoTemplate.findOne(query0, Status.class);
            
//             /* Get full parcel data *//* Add new status */
//             Update update1 = new Update().addToSet("status", new TimestampStatus(newStat));
//             recComplete = mongoTemplate.findAndModify(query1, update1, ParcelComplete_a2a.class);

//             /* Confirm that box has been closed */
//             // Update updateBox = new Update().set("currentState", 0);      //??? 
//             // updateBox.set("lid", 0);
//             // box = mongoTemplate.findAndModify(boxQuery, updateBox, Box.class);
//         }
        
//         return recComplete;
//     }
}
