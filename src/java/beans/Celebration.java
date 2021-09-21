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
public class Celebration {
    private int id;
    private String name;
    private String description;
    private int price;
    private int agencyId;
    
       public Celebration() {
    }

    public Celebration(int id, String name, String description, int price, int agencyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.agencyId = agencyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    } 
}
