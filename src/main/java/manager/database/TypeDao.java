package manager.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manager.models.TypeModel;
import manager.utils.DatabaseUtils;

import java.sql.*;

public class TypeDao {

    private Dao dao = new Dao();

    private ObservableList<TypeModel> typeModelObservableList = FXCollections.observableArrayList();

    public void insertTypeOnly(String type) throws ClassNotFoundException {
        dao.dataManipulation("INSERT INTO type VALUES (NULL,'" + type + "',NULL);");
    }

    public void insert(String type, String information) throws ClassNotFoundException {
        dao.dataManipulation("INSERT INTO type VALUES (NULL,'" + type + "','" + information + "');");
    }

    public void delete(double id) throws ClassNotFoundException {
        dao.dataManipulation("DELETE FROM type WHERE id_type='" + id + "';");
    }

    public void selectAll() {

        try {
            Connection conn = DatabaseUtils.sqlConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM type");

            conn.setAutoCommit(false);

            conn.commit();

            while (rs.next()) {
                TypeModel typeModel = new TypeModel();
                typeModel.setIdType(rs.getInt("id_type"));
                typeModel.setType(rs.getString("type"));
                typeModel.setInfo(rs.getString("information"));
                this.typeModelObservableList.add(typeModel);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<TypeModel> getTypeModelObservableList() {
        return typeModelObservableList;
    }

    public void setTypeModelObservableList(ObservableList<TypeModel> typeModelObservableList) {
        this.typeModelObservableList = typeModelObservableList;
    }
}

