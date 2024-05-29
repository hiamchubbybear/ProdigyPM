package com.rs.employer.service;

import java.util.ArrayList;

import com.rs.employer.model.Merchandise;

public interface MerchandiseService {
    public Boolean addMerchandise(Merchandise merchandise);

    public Merchandise listMerchandiseById(Long ID);

    public Boolean deleteMerchandiseById(Long ID);

    public ArrayList<Merchandise> listAllMerchandise(Merchandise merchandise);

    public String changeMerchandise(Long ID, Merchandise merchandise);

}
