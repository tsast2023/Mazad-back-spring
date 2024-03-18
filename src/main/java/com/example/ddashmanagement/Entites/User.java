package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Ennum.StatusUser;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@TypeAlias("user")
public class User implements UserDetails {
    @Id
    private String Id ;
    private String nomFamille ;
    private String prenom ;
    private String numTel ;
    @Email
    private String email ;
    private String password ;
    @Indexed(unique = true)
    private String pseudo ;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    private RoleUser role ;

    private String notificationToken;

    private String socketId ;
    private Integer nombreEnchere ;
    private Integer nombreAchat ;

    private Solde solde ;

    private StatusUser status ;
    @DBRef
    private Favoris favoris ;
    @DBRef
    private Commandes commandes ;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public String getUsername() {
        if (pseudo != null) {
            return pseudo;
        } else {
            return numTel;
        }
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
