package formation.info;

public class Local {

    /**
     * identifiant unique du local
     */
    private int idlocal;

    /**
     * sigle du local
     */
    private String sigle;

    /**
     * nombre de places du local
     */
    private int places;

    /**
     * description du local
     */
    private String description;

    /**
     * Constructeur par défaut
     */
    public Local() {

    }

    /**
     * constructeur paramétré
     *
     * @param idlocal identifiant du local
     * @param sigle sigle du local
     * @param places nombre de places du local
     * @param description description du local
     *
     */
    public Local(int idlocal, String sigle, int places, String description) {
        this.idlocal = idlocal;
        this.sigle = sigle;
        this.places = places;
        this.description = description;
    }

    /**
     * getter idlocal
     *
     * @return identifiant du local
     */
    public int getIdlocal() {
        return idlocal;
    }

    /**
     * setter idlocal
     *
     * @param idlocal identifiant du local
     */
    public void setIdlocal(int idlocal) {
        this.idlocal = idlocal;
    }

    /**
     * getter sigle
     *
     * @return sigle du local
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * setter sigle
     *
     * @param sigle sigle du local
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * getter places
     *
     * @return nombre de places du local
     */
    public int getPlaces() {
        return places;
    }

    /**
     * setter places
     *
     * @param places nombre de places du local
     */
    public void setPlaces(int places) {
        this.places = places;
    }

    /**
     * getter description
     *
     * @return description du local
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter description
     *
     * @param description du local
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    

}
