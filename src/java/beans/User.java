/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Lenovo
 */
public class User {
    
    private int id;
    private String name;
    private String surname;
    private String email;
    private String passwordHash;
    private String salt;
    private int ovlascenjeId;



    public User() {
    }

    public User(int id, String name, String surname, String email, String passwordHash, String salt, int ovlascenjeId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.ovlascenjeId = ovlascenjeId;
    }
    public int getOvlascenjeId() {
        return ovlascenjeId;
    }

    public void setOvlascenjeId(int ovlascenjeId) {
        this.ovlascenjeId = ovlascenjeId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
}
