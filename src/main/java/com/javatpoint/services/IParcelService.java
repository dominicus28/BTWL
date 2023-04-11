package com.javatpoint.services;
import java.util.List;

import com.javatpoint.models.Parcel;

public interface IParcelService {
    List<Parcel> findAll();
}
