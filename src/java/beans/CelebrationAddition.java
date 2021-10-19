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
public class CelebrationAddition {
    private int celebrationId;
    private int additionId;

    public CelebrationAddition() {
    }

    public CelebrationAddition(int celebrationId, int additionId) {
        this.celebrationId = celebrationId;
        this.additionId = additionId;
    }

    public int getCelebrationId() {
        return celebrationId;
    }

    public void setCelebrationId(int celebrationId) {
        this.celebrationId = celebrationId;
    }

    public int getAdditionId() {
        return additionId;
    }

    public void setAdditionId(int additionId) {
        this.additionId = additionId;
    }
}
