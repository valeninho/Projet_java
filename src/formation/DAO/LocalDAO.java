package formation.DAO;

import formation.info.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import formation.info.Local;

public class LocalDAO extends DAO<Local> {

    /**
     * création d'un local sur base des valeurs de son objet formation
     *
     * @throws SQLException erreur de création
     * @param obj local à créer
     * @return local créé
     */
    @Override
    public Local create(Local obj) throws SQLException {

        String req1 = "insert into local(sigle, places, description) values(?,?,?)";
        String req2 = "select idlocal from local where sigle=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getSigle());
            pstm1.setInt(2, obj.getPlaces());
            pstm1.setString(3, obj.getDescription());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation local, aucune ligne créée");
            }
            pstm2.setString(1, obj.getSigle());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idlocal = rs.getInt(1);
                    obj.setIdlocal(idlocal);
                    return read(idlocal);
                } else {
                    throw new SQLException("erreur de création local, record introuvable");
                }
            }
        }
    }

    /**
     * récupération des données d'un local sur base de son ID
     *
     * @throws SQLException local inconnu
     * @param idlocal identifiant du local
     * @return local trouvé
     */
    public Local read(int idlocal) throws SQLException {

        String req = "select * from local where idlocal = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, idlocal);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String sigle = rs.getNString("SIGLE");
                    int places = rs.getInt("PLACES");
                    String description = rs.getString("DESCRIPTION");
                    return new Local(idlocal, sigle, places, description);

                } else {
                    throw new SQLException("Local inconnu");
                }

            }
        }
    }

    /**
     * mise à jour des données d'un local sur base de son ID
     *
     * @return Local
     * @param obj local à mettre à jour
     * @throws SQLException erreur de mise à jour
     */
    @Override
    public Local update(Local obj) throws SQLException {
        String req = "update local set sigle=?, places=?, description=? where idlocal = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(4, obj.getIdlocal());
            pstm.setString(1, obj.getSigle());
            pstm.setInt(2, obj.getPlaces());
            pstm.setString(3, obj.getDescription());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne local mise à jour");
            }
            return read(obj.getIdlocal());
        }
    }

    /**
     * effacement du local sur base de son ID
     *
     * @throws SQLException erreur d'effacement
     * @param obj local à effacer
     */
    @Override
    public void delete(Local obj) throws SQLException {

        String req = "delete from local where idlocal = ? ";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdlocal());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne local effacée");
            }

        }
    }

    /**
     * méthode permettant de récupérer tous les locaux selon une recherche
     * partielle sur leur description
     *
     * @param descr description recherché
     * @return liste de locaux
     * @throws SQLException description inconnu
     */
    public List<Local> rechDesc(String desc) throws SQLException {
        List<Local> plusieurs = new ArrayList<>();
        String req = "select * from local where description like ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, "%" + desc + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idlocal = rs.getInt("IDCLIENT");
                    String sigle = rs.getString("SIGLE");
                    int places = rs.getInt("PLACES");
                    String description = rs.getString("DESCRIPTION");

                    plusieurs.add(new Local(idlocal, sigle, places, description));

                }

                if (!trouve) {
                    throw new SQLException("Description Inconnue");
                } else {
                    return plusieurs;

                }
            }
        }

    }

}
