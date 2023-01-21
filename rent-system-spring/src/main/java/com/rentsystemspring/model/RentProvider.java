package com.rentsystemspring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RentProvider extends User implements Serializable {
    private String representative;
    private String companyName;
    //private boolean isLegal;

    /*public RentProvider(String username, String password, String name, String surname, String email, boolean isAdmin, String representative, boolean isLegal) {
        super(username, password, name, surname, email, isAdmin);
        this.representative = representative;
        this.isLegal = isLegal;
    }*/

    public RentProvider(String username, String password, String name, String surname, String email, String representative) {
        super(username, password, name, surname, email);
        this.representative = representative;
    }


    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }


    @Override
    public String toString() {
        return getUsername() + ", Company: " + getCompanyName();
    }
}
