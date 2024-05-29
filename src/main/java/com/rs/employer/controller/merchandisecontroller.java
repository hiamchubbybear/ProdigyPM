package com.rs.employer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.model.Merchandise;
import com.rs.employer.service.MerchandiseImplementService;

@RestController
@RequestMapping(path = "/v1/m1")
public class merchandisecontroller {
    @Autowired
    private MerchandiseImplementService merchandiseImplementService;

    @GetMapping(path = "/getmerid")
    public Merchandise getUserById(@RequestParam(name = "ID") Long ID) {
        return merchandiseImplementService.listMerchandiseById(ID);
    }

    @GetMapping(path = "/getallmer")
    public List<Merchandise> getAllMerchandise(Merchandise merchandise) {
        List<Merchandise> list = merchandiseImplementService.listAllMerchandise(merchandise);
        return list;
    }

    @PostMapping(path = "/addmer")
    public Boolean addMerchandise(@RequestBody Merchandise merchandise) {
        return merchandiseImplementService.addMerchandise(merchandise);
    }

    @DeleteMapping(path = "/deletemer")
    public String deleteMerchandise(@RequestParam(name = "ID") Long ID) {
        if (merchandiseImplementService.deleteMerchandiseById(ID))
            return "User deleted ";
        else
            return "User can't delete";
    }

    @PutMapping(path = "/change")
    public String changeMerchandise(@RequestParam(name = "ID", required = true) Long ID,
            @RequestBody Merchandise merchandise) {
        return merchandiseImplementService.changeMerchandise(ID, merchandise);
    }

}
