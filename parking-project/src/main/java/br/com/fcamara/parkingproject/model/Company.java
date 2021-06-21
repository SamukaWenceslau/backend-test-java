package br.com.fcamara.parkingproject.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class Company implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String cnpj;
    private String tel;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profiles> profiles = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Address> addresses = new ArrayList<>();

    public Company(String name, String email, String password, String cnpj, String tel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnpj = cnpj;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
