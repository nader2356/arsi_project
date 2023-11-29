package com.example.entity;

import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
import com.example.util.enumData.Post;
import com.example.util.enumData.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;



@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
@Where(clause = "deleted = false ")
public class User implements UserDetails {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID id;
private String firstName;
private String lastName;
private String userName;
private String email;
private String password;
@Enumerated(EnumType.STRING)
private Gender gender;
private Date dateOfBirth;
private String phoneNumber;
private String region;
private String job;
private String universityOrCompany;
@Enumerated(EnumType.STRING)
private Post post;
@Enumerated(EnumType.STRING)
private Office office;
private String image;
private String cv;
@CreationTimestamp
private Instant expiresAt;
@CreationTimestamp
private Instant createdAt;
@UpdateTimestamp
private Instant updatedAt;
@Enumerated(EnumType.STRING)
private Role role;
private String otp;
@OneToMany(mappedBy = "user")
private List<Contact> contacts;
private boolean status = Boolean.FALSE;
private boolean firstLogin = Boolean.FALSE;
private boolean isPaid = Boolean.FALSE;
private boolean deleted = Boolean.FALSE;

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
}
@Override
public String getPassword() {
    return password;
}
@Override
public String getUsername() {
    return userName;
}
@Override
public boolean isAccountNonExpired() {
    if (role == Role.MEMBER) {
        return (status);
    }
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