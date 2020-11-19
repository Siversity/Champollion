/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

import java.util.Date;

/**
 *
 * @author Stéphane
 */
public class Intervention {
    
    // Attributs
    private TypeIntervention type;
    private Salle salle;
    private UE ue;
    private Enseignant prof;
    private Date date;
    private int duree;
    private Boolean annulee;
    
    
    // Constructeur
    public Intervention(TypeIntervention type, Salle salle, UE ue, Enseignant prof, Date date, int duree) {
        this.type = type;
        this.salle = salle;
        this.ue = ue;
        this.prof = prof;
        this.date= date;
        this.duree = duree;
        this.annulee = false;
    }
    
    
    // Getters
    public TypeIntervention getType() {
        return type;
    }
    
    public int getDuree() {
        return duree;
    }
    
    public Boolean getStatut() {
        return annulee;
    }
  
}
