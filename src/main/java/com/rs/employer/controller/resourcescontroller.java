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

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Resources;
import com.rs.employer.serviceimplements.ResourcesImplements;

@RestController
@RequestMapping(path = "/api/resouces")
@CrossOrigin
public class resourcescontroller {

    @Autowired
    public ResourcesImplements rsservice;

    @GetMapping(path = "/getall")
    public ApiRespone<List> resources() {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(rsservice.getAllProductByResourcesID());
        return apiRespone;
    }

    @PostMapping(path = "/add")
    public ApiRespone<Resources> addresource(@RequestBody Resources product) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(rsservice.addProductResources(product));
        return apiRespone;
    }

    @PutMapping(path = "/update/{id}")
    public ApiRespone<Resources> updateresouces(@RequestBody Resources product) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(rsservice.updateProductResources(product.getResourceid(), product));
        return apiRespone;
    }

    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<Boolean> deleteresouces(@PathVariable(name = "id") Long ID) {
        if (rsservice.deleteProductResources(ID)) {
            ApiRespone apiRespone = new ApiRespone<>();
            apiRespone.setData(true);
            return apiRespone;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Resources> getProductByResourcesID(@PathVariable(name = "id") Long id) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(rsservice.getProductResourcesByID(id));
        return apiRespone;
    }

}
