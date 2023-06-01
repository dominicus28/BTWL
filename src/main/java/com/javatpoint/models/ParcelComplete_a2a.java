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
        // a2a_code3_idle_open_box_ack = false;
        // a2a_code3_protect_close_box_opened_closed = false;
        a2a_code3_protect_close_sender_agree = false;
        // a2a_code3_protect_close_box_ack = false;
        a2a_code5_idle_open_receiver_agree = false;
        a2a_code5_idle_open_courier_agree = false;
        // a2a_code5_idle_open_box_ack = false;
        a2a_code5_end_open_receiver_agree = false;
        // a2a_code5_end_box_ack = false;
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
    public Message a2a_code1_idle_close_box_ack = new Message(false, false);

    /* code 3 */
    /* open */
    @JsonIgnore
    public boolean a2a_code3_idle_open_courier_agree; 
    // @DocumentReference
    @JsonIgnore
    public Message a2a_code3_idle_open_box_ack = new Message(false, true);
    /* close and protect */
    // @DocumentReference
    // @JsonIgnore
    // public boolean a2a_code3_protect_close_box_opened_closed;
    @JsonIgnore
    public boolean a2a_code3_protect_close_sender_agree;
    // @DocumentReference
    @JsonIgnore
    public Message a2a_code3_protect_close_box_ack = new Message(true, false);

    /* code 5 */
    /* go idle and open */
    @JsonIgnore
    public boolean a2a_code5_idle_open_receiver_agree;
    @JsonIgnore
    public boolean a2a_code5_idle_open_courier_agree;
    // @DocumentReference
    @JsonIgnore
    public Message a2a_code5_idle_open_box_ack = new Message(false, true);
    /* end */
    @JsonIgnore
    public boolean a2a_code5_end_open_receiver_agree;
    // @DocumentReference
    @JsonIgnore
    public Message a2a_code5_end_box_ack = new Message(false, false);

    /* Returns a message that has to be sent to the box */
    @Override
    public Message getMessage() {
        int code = getLastStatus().getStatus().getCode();
        
        switch(code) {
            case 1: {
                return a2a_code1_idle_close_box_ack;
            }
            case 3: {
                /* Send not acknowledged messages */
                if(a2a_code3_idle_open_courier_agree == true && a2a_code3_idle_open_box_ack.getAck() == false)
                    return a2a_code3_idle_open_box_ack;
                else if(a2a_code3_protect_close_sender_agree == true && a2a_code3_protect_close_box_ack.getAck() == false)
                    return a2a_code3_protect_close_box_ack;
                /* Send acknowledged messages to tell the box that it doesn't have to respond */
                else if(a2a_code3_protect_close_sender_agree == true && a2a_code3_protect_close_box_ack.getAck() == true)
                    return a2a_code3_protect_close_box_ack;
                else if(a2a_code3_idle_open_courier_agree == true && a2a_code3_idle_open_box_ack.getAck() == true)
                    return a2a_code3_idle_open_box_ack;
                /* else return message from previous stage */
                else 
                    return a2a_code1_idle_close_box_ack;
            }
            case 5: {
                /* Send not acknowledged messages */
                if(a2a_code5_idle_open_receiver_agree == true && a2a_code5_idle_open_courier_agree == true &&
                    a2a_code5_idle_open_box_ack.getAck() == false)
                    return a2a_code5_idle_open_box_ack;
                else if(a2a_code5_end_open_receiver_agree == true && a2a_code5_end_box_ack.getAck() == false)
                    return a2a_code5_end_box_ack;
                /* Send acknowledged messages to tell the box that it doesn't have to respond */
                else if(a2a_code5_end_open_receiver_agree == true && a2a_code5_end_box_ack.getAck() == true)
                    return a2a_code5_end_box_ack;
                if(a2a_code5_idle_open_receiver_agree == true && a2a_code5_idle_open_courier_agree == true &&
                    a2a_code5_idle_open_box_ack.getAck() == true)
                    return a2a_code5_idle_open_box_ack;
                /* else return message from previous stage */
                else 
                    return a2a_code3_protect_close_box_ack;
            }
        }

        /* ERROR */
        return null;
    }

//TODO set next code
    @Override
    public Message setMessage(Message message) {
        int code = getLastStatus().getStatus().getCode();
        
        switch(code) {
            case 1: {
                a2a_code1_idle_close_box_ack.setAck(message.getAck());
                return a2a_code1_idle_close_box_ack;
            }
            case 3: {
                /* Send not acknowledged messages */
                if(a2a_code3_idle_open_courier_agree == true && a2a_code3_idle_open_box_ack.getAck() == false) {
                    a2a_code3_idle_open_box_ack.setAck(message.getAck());
                    return a2a_code3_idle_open_box_ack;
                }
                else if(a2a_code3_protect_close_sender_agree == true && a2a_code3_protect_close_box_ack.getAck() == false) {
                    a2a_code3_protect_close_box_ack.setAck(message.getAck());
                    // if(message.getAck() == true)

                    return a2a_code3_protect_close_box_ack;
                }
            }
            case 5: {
                /* Send not acknowledged messages */
                if(a2a_code5_idle_open_receiver_agree == true && a2a_code5_idle_open_courier_agree == true &&
                a2a_code5_idle_open_box_ack.getAck() == false) {
                    a2a_code3_protect_close_box_ack.setAck(message.getAck());
                    return a2a_code5_idle_open_box_ack;
                }
                else if(a2a_code5_end_open_receiver_agree == true && a2a_code5_end_box_ack.getAck() == false) {
                    a2a_code5_end_box_ack.setAck(message.getAck());
                    return a2a_code5_end_box_ack;
                }
            }
        }

        /* ERROR */
        return null;
    }
}
