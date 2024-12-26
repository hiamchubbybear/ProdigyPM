package com.rs.employer.enums;

import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.CrossOrigin;
@PersistenceContext
public class CrossOriginVariable {
    public static String crossOriginHeader = "Access-Control-Allow-Origin";
    public static String uri = "http://localhost:3000";
}
