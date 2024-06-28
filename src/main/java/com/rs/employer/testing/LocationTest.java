package com.rs.employer.testing;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class LocationTest {
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        LocationTest test = new LocationTest();
        test.data();
    }

    static void data() throws IOException, GeoIp2Exception {
        String ip = "117.2.254.194";
        String dblocation = "/Users/chessy/Library/Mobile Documents/com~apple~CloudDocs/Codespace/Employer/src/main/java/com/rs/employer/testing/GeoLite2-ASN.mmdb"; // Corrected
        File file = new File(dblocation);
        DatabaseReader dbr = new DatabaseReader.Builder(file).build();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbr.city(ipAddress);

        String countryName = response.getCountry().getName();
        String cityName = response.getCity().getName();
        String postal = response.getPostal().getCode();

        System.out.println("Country Name: " + countryName);
        System.out.println("City Name: " + cityName);
        System.out.println("Postal Code: " + postal);

    }
}
