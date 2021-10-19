/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;


/**
 *
 * @author Lenovo
 */
public class Reservation {
    
    private int id;
    private int finalPrice;
    private Date start;
    private Date end;
    private boolean canceled = false;
    private int celebrationId;
    private int userId;

    public Reservation() {
    }

    public Reservation(int id, int finalPrice, Date start, Date end, boolean canceled, int celebrationId, int userId) {
        this.id = id;
        this.finalPrice = finalPrice;
        this.start = start;
        this.end = end;
        this.canceled = canceled;
        this.celebrationId = celebrationId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public int getCelebrationId() {
        return celebrationId;
    }

    public void setCelebrationId(int celebrationId) {
        this.celebrationId = celebrationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
