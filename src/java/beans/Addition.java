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
public class Addition {
    
    private int id;
    private String naziv;
    private int cena;

    public Addition() {
    }

    public Addition(int id, String naziv, int cena) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return naziv;
    }

    public void setName(String name) {
        this.naziv = name;
    }

    public int getPrice() {
        return cena;
    }

    public void setPrice(int price) {
        this.cena = price;
    }
    
}
