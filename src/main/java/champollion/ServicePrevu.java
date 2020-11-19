package champollion;

public class ServicePrevu {
    
    // Attributs
    private int volumeCM;
    private int volumeTD;
    private int volumeTP;
    
    
    // Constructeur
    public ServicePrevu(int cm, int td, int tp) {
        volumeCM = cm;
        volumeTD = td;
        volumeTP = tp;
    }
    
    
    // Getters
    public int getVolumeCM() {
        return volumeCM;
    }
    
    public int getVolumeTD() {
        return volumeTD;
    }
    
    public int getVolumeTP() {
        return volumeTP;
    }
    
    
    // Setters
    public void addVolumeCM(int cm) {
        this.volumeCM = volumeCM + cm;
    }
    
    public void addVolumeTD(int td) {
        this.volumeTD = volumeTD + td;
    }
    
    public void addVolumeTP(int tp) {
        this.volumeTP = volumeTP + tp;
    }
    
}
