package com.javatpoint.services;

import org.springframework.stereotype.Service;

import com.javatpoint.models.Message;
import com.javatpoint.models.ParcelComplete_a2a;
import com.javatpoint.models.Status;
import com.javatpoint.models.TimestampAlarm;
import com.javatpoint.models.TimestampStatus;
import com.javatpoint.repositories.BoxRepository;
import com.javatpoint.repositories.ParcelCompleteRepository;
import com.javatpoint.repositories.StatusRepository;
import com.javatpoint.repositories.TimestampStatusRepository;

@Service
public class ParcelComplete_a2aService {
    private final ParcelCompleteRepository parcelCompleteRepository;
    // private final TimestampAlarm tsAlarmReository;
    private final BoxRepository boxRepository;
    private final StatusRepository statusRepository;
    private final TimestampStatusRepository tsStatusRepository;

    ParcelComplete_a2aService(ParcelCompleteRepository parcelCompleteRepository,
                            //   TimestampAlarm tsAlarmReository,
                              BoxRepository boxRepository,
                              StatusRepository statusRepository,
                              TimestampStatusRepository tsStatusRepository) {
        this.parcelCompleteRepository = parcelCompleteRepository;
        // this.tsAlarmReository = tsAlarmReository;
        this.boxRepository = boxRepository;
        this.statusRepository = statusRepository;
        this.tsStatusRepository = tsStatusRepository;
    }
    
    /* Returns a message that has to be sent to the box */
    public Message getMessage(ParcelComplete_a2a pCa2a) {
        int code = pCa2a.getLastStatus().getStatus().getCode();
        System.out.println("ParcelComplete_a2a.code:" + code);
        
        switch(code) {
            case 1: {
                return pCa2a.a2a_code1_idle_close_box_ack;
            }
            case 3: {
                /* Send not acknowledged messages */
                if(pCa2a.a2a_code3_idle_open_courier_agree == true && pCa2a.a2a_code3_idle_open_box_ack.getAck() == false)
                    return pCa2a.a2a_code3_idle_open_box_ack;
                else if(pCa2a.a2a_code3_protect_close_sender_agree == true && pCa2a.a2a_code3_protect_close_box_ack.getAck() == false)
                    return pCa2a.a2a_code3_protect_close_box_ack;
                /* Send acknowledged messages to tell the box that it doesn't have to respond */
                else if(pCa2a.a2a_code3_protect_close_sender_agree == true && pCa2a.a2a_code3_protect_close_box_ack.getAck() == true)
                    return pCa2a.a2a_code3_protect_close_box_ack;
                else if(pCa2a.a2a_code3_idle_open_courier_agree == true && pCa2a.a2a_code3_idle_open_box_ack.getAck() == true)
                    return pCa2a.a2a_code3_idle_open_box_ack;
                /* else return message from previous stage */
                else
                    return pCa2a.a2a_code1_idle_close_box_ack;
            }
            case 5: {
                /* Send not acknowledged messages */
                if(pCa2a.a2a_code5_idle_open_receiver_agree == true && pCa2a.a2a_code5_idle_open_courier_agree == true &&
                pCa2a.a2a_code5_idle_open_box_ack.getAck() == false)
                    return pCa2a.a2a_code5_idle_open_box_ack;
                else if(pCa2a.a2a_code5_end_open_receiver_agree == true && pCa2a.a2a_code5_end_box_ack.getAck() == false)
                    return pCa2a.a2a_code5_end_box_ack;
                /* Send acknowledged messages to tell the box that it doesn't have to respond */
                else if(pCa2a.a2a_code5_end_open_receiver_agree == true && pCa2a.a2a_code5_end_box_ack.getAck() == true)
                    return pCa2a.a2a_code5_end_box_ack;
                if(pCa2a.a2a_code5_idle_open_receiver_agree == true && pCa2a.a2a_code5_idle_open_courier_agree == true &&
                pCa2a.a2a_code5_idle_open_box_ack.getAck() == true)
                    return pCa2a.a2a_code5_idle_open_box_ack;
                /* else return message from previous stage */
                else 
                    return pCa2a.a2a_code3_protect_close_box_ack;
            }
        }

        /* ERROR */
        return null;
    }

// TODO set next code, TODO zmienić kolejniość tak żeby ostatnie akcje były sprawdzane jako pierwsze i pozbyć się sprawdzania ACK?
    public Message setMessage(ParcelComplete_a2a pCa2a, Message message) {
        int code = pCa2a.getLastStatus().getStatus().getCode();
        
        switch(code) {
            case 1: {
                /* Response is ok */
                if(message.getProtect() == false && message.getOpen() == false) {
                    if(message.getAck() == true) {
                        pCa2a.a2a_code1_idle_close_box_ack.setAck();
                        parcelCompleteRepository.setA2a_code1_idle_close_box_ack(pCa2a.getId().toString());
                    }

                    return pCa2a.a2a_code1_idle_close_box_ack;
                } else {
                    /* ERROR, that shouldn'h have happened */
                    return pCa2a.a2a_code1_idle_close_box_ack;
                }
            }
            case 3: {
                /* Send not acknowledged messages */
                if(pCa2a.a2a_code3_idle_open_courier_agree == true && pCa2a.a2a_code3_idle_open_box_ack.getAck() == false) {
                    if(message.getProtect() == pCa2a.a2a_code3_idle_open_box_ack.getProtect() && message.getOpen() == pCa2a.a2a_code3_idle_open_box_ack.getOpen()) {
                        if(message.getAck() == true) {
                            pCa2a.a2a_code3_idle_open_box_ack.setAck();
                            parcelCompleteRepository.setA2a_code3_idle_open_box_ack(pCa2a.getId().toString());
                        }

                        return pCa2a.a2a_code3_idle_open_box_ack;
                    } else {
                        /* ERROR, that shouldn'h have happened */
                        return pCa2a.a2a_code3_idle_open_box_ack;
                    }
                }
                else if(pCa2a.a2a_code3_protect_close_sender_agree == true && pCa2a.a2a_code3_protect_close_box_ack.getAck() == false) {
                    if(message.getProtect() == pCa2a.a2a_code3_protect_close_box_ack.getProtect() && message.getOpen() == pCa2a.a2a_code3_protect_close_box_ack.getOpen()) {
                        if(message.getAck() == true) {
                            /* Set ACK */
                            pCa2a.a2a_code3_protect_close_box_ack.setAck();
                            parcelCompleteRepository.setA2a_code3_protect_close_box_ack(pCa2a.getId().toString());
                            /* Add new status */
                            Status stat = statusRepository.find(5);
                            TimestampStatus tsStat = tsStatusRepository.save(new TimestampStatus(stat));//TODO
                            parcelCompleteRepository.addStatus(pCa2a.getId().toString(), tsStat);
                        }

                        return pCa2a.a2a_code3_protect_close_box_ack;
                    } else {
                        /* ERROR, that shouldn'h have happened */
                        return pCa2a.a2a_code3_protect_close_box_ack;
                    }
                }
                /* Check if package's message is from prevoius state */
                else if(pCa2a.a2a_code3_idle_open_courier_agree == false && pCa2a.a2a_code3_idle_open_box_ack.getAck() == false) {
                    if(message.getProtect() == pCa2a.a2a_code1_idle_close_box_ack.getProtect() && message.getOpen() == pCa2a.a2a_code1_idle_close_box_ack.getOpen()) {
                        if(message.getAck() == true) {
                            // pCa2a.a2a_code3_idle_open_box_ack.setAck();
                            // parcelCompleteRepository.setA2a_code3_protect_close_box_ack(pCa2a.getId().toString());
                        }

                        return pCa2a.a2a_code3_idle_open_box_ack;
                    } else {
                        /* ERROR, that shouldn'h have happened */
                        return pCa2a.a2a_code3_idle_open_box_ack;
                    }
                } else {
                    /* ERROR, that shouldn'h have happened */
                    // TimestampAlarm tsAlarm = new TimestampAlarm();
                }
            }
            case 5: {
                /* Send not acknowledged messages */
                if(pCa2a.a2a_code5_idle_open_receiver_agree == true && pCa2a.a2a_code5_idle_open_courier_agree == true &&
                pCa2a.a2a_code5_idle_open_box_ack.getAck() == false) {
                    if(message.getProtect() == pCa2a.a2a_code5_idle_open_box_ack.getProtect() && message.getOpen() == pCa2a.a2a_code5_idle_open_box_ack.getOpen()) {
                        if(message.getAck() == true) {
                            pCa2a.a2a_code5_idle_open_box_ack.setAck();
                            parcelCompleteRepository.setA2a_code5_idle_open_box_ack(pCa2a.getId().toString());
                        }

                        return pCa2a.a2a_code5_idle_open_box_ack;
                    } else {
                        /* ERROR, that shouldn'h have happened */
                        return pCa2a.a2a_code5_idle_open_box_ack;
                    }
                }
                else if(pCa2a.a2a_code5_end_open_receiver_agree == true && pCa2a.a2a_code5_end_box_ack.getAck() == false) {
                    if(message.getProtect() == pCa2a.a2a_code5_end_box_ack.getProtect() && message.getOpen() == pCa2a.a2a_code5_end_box_ack.getOpen()) {
                        if(message.getAck() == true) {  
                            pCa2a.a2a_code5_end_box_ack.setAck();
                            parcelCompleteRepository.setA2a_code5_end_box_ack(pCa2a.getId().toString());
                            /* Add new status, clean up */
                            Status stat = statusRepository.find(7);
                            TimestampStatus tsStat = tsStatusRepository.save(new TimestampStatus(stat));//TODO
                            parcelCompleteRepository.addStatus(pCa2a.getId().toString(), tsStat);
                            boxRepository.setParcelCompleteNull(pCa2a.getParcel().getBox().getMac());
                        }

                        return pCa2a.a2a_code5_end_box_ack;
                    } else {
                        /* ERROR, that shouldn'h have happened */
                        return pCa2a.a2a_code5_end_box_ack;
                    }
                } else if(pCa2a.a2a_code3_protect_close_sender_agree == true && pCa2a.a2a_code3_protect_close_box_ack.getAck() == false) {
                    if(message.getProtect() == pCa2a.a2a_code3_protect_close_box_ack.getProtect() && message.getOpen() == pCa2a.a2a_code3_protect_close_box_ack.getOpen()) {
                        if(message.getAck() == true) {
                            pCa2a.a2a_code3_protect_close_box_ack.setAck();
                        }
    
                        return pCa2a.a2a_code3_protect_close_box_ack;
                    } else {
                        /* ERROR, that shouldn'h have happened */
                        return pCa2a.a2a_code3_protect_close_box_ack;
                    } 
                } else {
                    /* ERROR, that shouldn'h have happened */
                    // TimestampAlarm tsAlarm = new TimestampAlarm();
                }
            }
            default: {
                if(message.getProtect() == false && message.getOpen() == false) {
                    if(message.getAck() == true) {
                        pCa2a.a2a_code1_idle_close_box_ack.setAck();
                        parcelCompleteRepository.setA2a_code1_idle_close_box_ack(pCa2a.getId().toString());
                    }

                    return pCa2a.a2a_code1_idle_close_box_ack;
                } else {
                    /* ERROR, that shouldn'h have happened */
                    return pCa2a.a2a_code1_idle_close_box_ack;
                }
            }
        }

        /* ERROR */
        // return null;
    }

}
