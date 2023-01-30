package br.com.correios.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    private String zipcode;
    private String street;
    private String district;
    private String city;
    private String state;
}
