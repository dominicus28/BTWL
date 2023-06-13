package com.javatpoint.models;

import java.util.ArrayList;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;

//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document("parcelComplete")
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelComplete_a2a extends ParcelComplete {

    public ParcelComplete_a2a() {}

    // public ParcelComplete_a2a(Parcel parcel, List <TimestampTelemetry> telemetry,
    //                     List <TimestampAlarm> alarm, List <TimestampStatus> status) {
    //     super();
    //     this.parcel = parcel;
    //     this.telemetry = telemetry;
    //     this.alarm = alarm;
    //     this.status = status;
    //     a2a_code3_open_courier_agree = false;
    //     a2a_code3_protect_box_opened_closed = false;
    //     a2a_code3_protect_sender_agree = false;
    //     a2a_code5_idle_open_receiver_agree = false;
    //     a2a_code5_idle_open_courier_agree = false;
    //     a2a_code5_end_box_opened_closed = false;
    // }
    
    
    public ParcelComplete_a2a(Parcel parcel) {
        super();
        this.parcel = parcel;
        this.telemetry = new ArrayList<>();
        this.alarm = new ArrayList<>();
        this.status = new ArrayList<>();
        a2a_code3_idle_open_courier_agree = false;
        a2a_code1_idle_close_box_ack = new Message(false, false);
        a2a_code3_protect_close_sender_agree = false;
        a2a_code3_idle_open_box_ack = new Message(false, true);
        a2a_code5_idle_open_receiver_agree = false;
        a2a_code5_idle_open_courier_agree = false;
        a2a_code3_protect_close_box_ack = new Message(true, false);
        a2a_code5_end_open_receiver_agree = false;
        a2a_code5_idle_open_box_ack = new Message(false, true);
        a2a_code5_end_box_ack = new Message(false, false);

    }

    /* Combined with information about the state of a parcel
     * is used to open a box.
     * Opening a box is handled by box controller that sends messages 
     * to the box (controller checks if all circumstances required to
     * open the box happend; circumstances difference depending on
     * the code of the parcel state).
     * Information about the lid are used by parcel controller to decide
     * of procedure of inserting an item to the box happened.
     */

    /* code 1 */
    @JsonIgnore
    public Message a2a_code1_idle_close_box_ack;

    /* code 3 */
    /* open */
    public boolean a2a_code3_idle_open_courier_agree; 
    public Message a2a_code3_idle_open_box_ack;
    /* close and protect */
    public boolean a2a_code3_protect_close_sender_agree;
    public Message a2a_code3_protect_close_box_ack;

    /* code 5 */
    /* go idle and open */
    public boolean a2a_code5_idle_open_receiver_agree;  
    public boolean a2a_code5_idle_open_courier_agree;   
    public Message a2a_code5_idle_open_box_ack;
    /* end */
    public boolean a2a_code5_end_open_receiver_agree;
    @JsonIgnore
    public Message a2a_code5_end_box_ack;

    
}
