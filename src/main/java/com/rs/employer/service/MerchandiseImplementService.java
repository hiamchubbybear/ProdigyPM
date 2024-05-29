package com.rs.employer.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.model.Merchandise;
import com.rs.employer.repository.MerchandiseRepository;

@Service
public class MerchandiseImplementService implements MerchandiseService {
    @Autowired
    public MerchandiseRepository merchandiseRepository;

    @Override
    public Boolean addMerchandise(Merchandise merchandise) {
        merchandiseRepository.save(merchandise);
        return true;
    }

    @Override
    public Merchandise listMerchandiseById(Long ID) {
        Optional<Merchandise> eOptional = merchandiseRepository.findById(ID);
        Merchandise merchandise = eOptional.get();
        return merchandise;
    }

    @Override
    public Boolean deleteMerchandiseById(Long ID) {
        Optional<Merchandise> eOptional = merchandiseRepository.findById(ID);
        Merchandise merchandise = eOptional.get();
        if (merchandise.getID().equals(null))
            return false;
        else
            return true;

    }

    @Override
    public ArrayList<Merchandise> listAllMerchandise(Merchandise merchandise) {
        return (ArrayList<Merchandise>) merchandiseRepository.findAll();
    }

    @Override
    public String changeMerchandise(Long ID, Merchandise merchandise) {
        Optional<Merchandise> eOptional = merchandiseRepository.findById(ID);
        Merchandise merchandise1 = eOptional.get();
        if (merchandise1.getID().equals(null))
            throw new NullPointerException("User is not exist ");
        else {
            merchandiseRepository.deleteById(ID);
            merchandiseRepository.save(merchandise);
            return "Your new merchandises are change to a new ID is :" + ID;
        }

    }
}