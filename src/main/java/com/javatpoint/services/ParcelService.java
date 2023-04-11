package com.javatpoint.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.javatpoint.models.Parcel;

@Service
public class ParcelService implements IParcelService {
    @Override
    public List<Parcel> findAll()
    {
    //creating an object of ArrayList
    ArrayList<Parcel> parcels = new ArrayList<Parcel>();
    //adding parcels to the List
    parcels.add(new Parcel(1, 'A', 2, 3, 1));
    parcels.add(new Parcel(2, 'B', 2, 3, 4));
    parcels.add(new Parcel(3, 'A', 2, 3, 4));
    parcels.add(new Parcel(4, 'C', 2, 3, 1));
    //returns a list of parcels
    return parcels;
    }
}