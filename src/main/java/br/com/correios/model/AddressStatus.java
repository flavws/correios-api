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
public class AddressStatus {

    public static final int DEFAULT_ID = 1;

    @Id
    private int id;
    private Status status;
}
