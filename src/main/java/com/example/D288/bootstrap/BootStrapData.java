package com.example.D288.bootstrap;

import com.example.D288.dao.CustomerRepository;
import com.example.D288.dao.DivisionRepository;
import com.example.D288.entity.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository){
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Customer jordan = new Customer();
        jordan.setFirstName("Jordan");
        jordan.setLastName("Haugen");
        jordan.setAddress("123 whitehouse lane");
        jordan.setPostal_code("12039");
        jordan.setPhone("2149834923");

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Smith");
        michael.setAddress("304 check lane");
        michael.setPostal_code("43768");
        michael.setPhone("2144737923");

        Customer steve = new Customer();
        steve.setFirstName("Steve");
        steve.setLastName("Doe");
        steve.setAddress("test address");
        steve.setPostal_code("51517");
        steve.setPhone("2149834923");


        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Shmoe");
        joe.setAddress("12345 Candice ave");
        joe.setPostal_code("29481");
        joe.setPhone("268456162");

        Customer nick = new Customer();
        nick.setFirstName("Nick");
        nick.setLastName("Ferry");
        nick.setAddress("South Pole");
        nick.setPostal_code("23853");
        nick.setPhone("151616516");

        customerRepository.save(jordan);
        customerRepository.save(michael);
        customerRepository.save(steve);
        customerRepository.save(joe);
        customerRepository.save(nick);
    }

}
