package com.javatpoint.models;

import java.util.ArrayList;
//import java.time.LocalDateTime;
//import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
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
        this.telemetry = new ArrayList<TimestampTelemetry>();
        this.alarm = new ArrayList<TimestampAlarm>();
        this.status = new ArrayList<TimestampStatus>();
        a2a_code3_open_courier_agree = false;
        a2a_code3_protect_box_opened_closed = false;
        a2a_code3_protect_sender_agree = false;
        a2a_code5_idle_open_receiver_agree = false;
        a2a_code5_idle_open_courier_agree = false;
        a2a_code5_end_box_opened_closed = false;
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

    /* code 3 */
    /* open */
    public boolean a2a_code3_open_courier_agree; /* 0 - do nothing,
                                 * 1 - open box                        
                                 */
    /* protect */
    public boolean a2a_code3_protect_box_opened_closed;
    public boolean a2a_code3_protect_sender_agree;

    /* code 5 */
    /* go idle and open */
    public boolean a2a_code5_idle_open_receiver_agree;
    public boolean a2a_code5_idle_open_courier_agree;
    /* end */
    public boolean a2a_code5_end_box_opened_closed;
}
