package ru.kata.spring.boot_security.demo.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String eMail;
    @Column(name = "isAccountNonExpired")
    private boolean isAccountNonExpired;
    @Column(name = "isAccountNonLocked")
    private boolean isAccountNonLocked;
    @Column(name = "isCredentialsNonExpired")
    private boolean isCredentialsNonExpired;
    @Column(name = "isEnabled")
    private boolean isEnabled;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) roles;
    }

    @Override
    public String getUsername() {
        return eMail;
    }

    public User(String eMail, String password, String firstName,String lastName,int age) {
        this.eMail = eMail;
        this.password = password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.isAccountNonLocked=true;
        this.isAccountNonExpired=true;
        this.isEnabled=true;
        this.isCredentialsNonExpired=true;
    }


    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setisAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setisAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setisCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setisEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
