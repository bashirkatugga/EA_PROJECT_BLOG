package com.blog.userservice.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "First name is required! ")
    private String firstName;
    @NotBlank(message = "Last name is required! ")
    private String lastName;
    @NotBlank(message = "username is required! ")
    private String username;
    @NotBlank(message = "Email is required! ")
    @Email(message= "Invalid email")
    private String email;
    @Past
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    /**
     * Returns granted Role as set of SimpleGrantedAuthority for the user.
     * granted authorities are used by spring for authentication.
     *
     * @return Set<SimpleGrantedAuthority>
     */
    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> permissions = new HashSet<>();
        permissions.add(new SimpleGrantedAuthority("ROLE_" + getRole().toString()));
        return permissions;
    }

}
