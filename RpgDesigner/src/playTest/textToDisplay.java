/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

/**
 *
 * @author Fran
 */
public class textToDisplay {
    private String text;
    private int timeLeft;

    public textToDisplay(String text, int timeLeft) {
        this.text = text;
        this.timeLeft = timeLeft;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
    
    public void decreaseTimeLeft() {
        this.timeLeft-=1;
    }
    
}
