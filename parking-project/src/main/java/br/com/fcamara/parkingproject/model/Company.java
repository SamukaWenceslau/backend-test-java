package br.com.fcamara.parkingproject.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String cnpj;
    private String tel;

    public Company(String name, String cnpj, String tel) {
        this.name = name;
        this.cnpj = cnpj;
        this.tel = tel;
    }

}
