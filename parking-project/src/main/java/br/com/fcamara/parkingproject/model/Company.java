package br.com.fcamara.parkingproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "company")
    private List<Address> addresses = new ArrayList<>();

    public Company(String name, String cnpj, String tel) {
        this.name = name;
        this.cnpj = cnpj;
        this.tel = tel;
    }

}
