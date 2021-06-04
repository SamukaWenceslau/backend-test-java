package br.com.fcamara.parkingproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cnpj;
    private String tel;

}
