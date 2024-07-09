package com.rs.employer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.model.Resources;
import com.rs.employer.serviceimplements.ResourcesImplements;

@RestController
@RequestMapping(path = "/api/resouces")
@CrossOrigin
public class resourcescontroller {

    @Autowired
    public ResourcesImplements rsservice;
    
    @GetMapping(path = "/getall")
    public List resources() {
        return rsservice.getAllProductByResourcesID();
    }

    @PutMapping(path = "/add")
    public void addresource(@RequestBody Resources product) {
        rsservice.addProductResources(product);
    }

    @PostMapping(path = "/update/{id}")
    public void updateresouces(@RequestBody Resources product) {
        rsservice.updateProductResources(product.getID(), product);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Boolean deleteresouces(@PathVariable(name = "id") Long ID) {
        if (rsservice.deleteProductResources(ID))
            return true;
        else
            return false;
    }

    @GetMapping(path = "/getbyid/{id}")
    public Resources getProductByResourcesID(@PathVariable(name = "id") Long id) {
        return rsservice.getProductResourcesByID(id);
    }

}
