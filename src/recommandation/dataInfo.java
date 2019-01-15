/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hichem Bedjaoui
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommandation;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hichem Bedjaoui
 */
public class dataInfo {

    private final SimpleStringProperty user;
    private final SimpleStringProperty sujet;
    private final     SimpleStringProperty rating;

    public dataInfo(String User, String Sujet, String Rating) {
        super();
     
        this.user = new SimpleStringProperty(User);
        this.sujet = new SimpleStringProperty(Sujet);
        this.rating = new SimpleStringProperty(Rating);
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public void setSujet(String sujet) {
        this.sujet.set(sujet);
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public String getUser() {
        return user.get();
    }

    public String getSujet() {
        return sujet.get();
    }

    public String getRating() {
        return rating.get();
    }

}

