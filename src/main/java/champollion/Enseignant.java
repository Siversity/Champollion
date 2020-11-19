package champollion;

import static champollion.TypeIntervention.CM;
import static champollion.TypeIntervention.TD;
import static champollion.TypeIntervention.TP;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Enseignant extends Personne {
    
    private HashMap<UE, ServicePrevu> listeServicesPrevus;
    private ArrayList<Intervention> listeInterventions;


    public Enseignant(String nom, String email) {
        super(nom, email);
        listeServicesPrevus = new HashMap<>();
        listeInterventions = new ArrayList<>();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public float heuresPrevues() {
        // Initialisation des variables
        float htCM = 0;
        float htTD = 0;
        float htTP = 0;
        for (ServicePrevu s : listeServicesPrevus.values()) {
            htCM = htCM + (float)(s.getVolumeCM() * 1.5);
            htTD = htTD + s.getVolumeTD();
            htTP = htTP + (float)(s.getVolumeTP() * 0.75);
        }
        float hTotal = htCM + htTD + htTP;
        return hTotal;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public float heuresPrevuesPourUE(UE ue) {
        // Initialisation des variables
        float hCM = 0;
        float hTD = 0;
        float hTP = 0;
        // Obtention et conversion des heures d'enseignement
        ServicePrevu s = listeServicesPrevus.get(ue);
        hCM = (float)(s.getVolumeCM() * 1.5);
        hTD = s.getVolumeTD();
        hTP = (float)(s.getVolumeTP() * 0.75);
        float hTotal = hCM + hTD + hTP;
        return hTotal;
    }
    
    public float heuresPlanifiees() {
        // Initialisation variable
        float total = 0;
        for (Intervention i : listeInterventions) {
            TypeIntervention type = i.getType();
            if ((type.equals(CM)) && (i.getStatut() == false)) {
                total = total + (float)(1.5 * i.getDuree());
            }
            if ((type.equals(TD)) && (i.getStatut() == false)) {
                total = total + i.getDuree();
            }
            if ((type.equals(TP)) && (i.getStatut() == false)) {
                total = total + (float)(0.75 * i.getDuree());
            }
        }
        return total;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        // Exception
        if ((volumeCM < 0) || (volumeTD < 0) || (volumeTP < 0)) {
            throw new IllegalArgumentException("Les heures d'enseignement ne peuvent pas être négatives");
        }
        if (listeServicesPrevus.containsKey(ue) == true) {
            // Compteurs
            int countCM = volumeCM;
            int countTD = volumeTD;
            int countTP = volumeTP;
            ServicePrevu s = listeServicesPrevus.get(ue);
            s.addVolumeCM(volumeCM);
            s.addVolumeTD(volumeTD);
            s.addVolumeTP(volumeTP);
            // Creation Interventions CM
            for (int i = 0; countCM != 0; i++) {
                this.ajouteIntervention(CM, ue, 1);
                countCM = countCM - 1;
            }
            // Creation Interventions TD
            for (int i = 0; countTD != 0; i++) {
                this.ajouteIntervention(TD, ue, 1);
                countTD = countTD - 1;
            }
            // Creation Interventions TP
            for (int i = 0; countTP != 0; i++) {
                this.ajouteIntervention(TP, ue, 1);
                countTP = countTP - 1;
            }
        }
        else {
            // Compteurs
            int countCM = volumeCM;
            int countTD = volumeTD;
            int countTP = volumeTP;
            ServicePrevu s = new ServicePrevu(volumeCM, volumeTD, volumeTP);
            listeServicesPrevus.put(ue, s);
            // Creation Interventions CM
            for (int i = 0; countCM != 0; i++) {
                this.ajouteIntervention(CM, ue, 1);
                countCM = countCM - 1;
            }
            // Creation Interventions TD
            for (int i = 0; countTD != 0; i++) {
                this.ajouteIntervention(TD, ue, 1);
                countTD = countTD - 1;
            }
            // Creation Interventions TP
            for (int i = 0; countTP != 0; i++) {
                this.ajouteIntervention(TP, ue, 1);
                countTP = countTP - 1;
            }
        }
    }
    
    public void ajouteIntervention(TypeIntervention type, UE ue, int duree) {
        Intervention i = new Intervention(type, null, ue, this, new Date(), duree);
        listeInterventions.add(i);
    }
    
    public ArrayList<Intervention> getListeInterventions() {
        return listeInterventions;
    }

}
