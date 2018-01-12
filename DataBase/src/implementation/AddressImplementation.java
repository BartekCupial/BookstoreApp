package implementation;

import kernel.AbstractRecord;
import kernel.RecordAdapter;
import records.Address;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressImplementation extends RecordAdapter{
    @Override
    public int insertint(AbstractRecord record) {
        Address address = (Address) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int generatedKey = 0;
        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Adresy (ID, ulica, numerLokalu, kodPocztowy, miejscowosc, wojewodztwo, kraj)" +
                    "VALUES (?, ?, ?, ?, ? ,?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, address.getID());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getBuildingNumber());
            preparedStatement.setString(4, address.getPostalCode());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getProvince());
            preparedStatement.setString(7, address.getCountry());


            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                generatedKey = resultSet.getInt(1);
            }
            System.out.println("INSERT INTO Adresy (ID, ulica, numerLokalu, kodPocztowy, miejscowosc, wojewodztwo, kraj)" +
                    "VALUES (?, ?, ?, ?, ? ,?, ?)");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return generatedKey;
    }

    @Override
    public AbstractRecord selectById(int id) {
        Address address = new Address();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Adresy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                address.setID(resultSet.getInt("ID"));
                address.setStreet(resultSet.getString("street"));
                address.setBuildingNumber(resultSet.getString("buildingNumber"));
                address.setPostalCode(resultSet.getString("postalCode"));
                address.setCity(resultSet.getString("city"));
                address.setProvince(resultSet.getString("province"));
                address.setCountry(resultSet.getString("country"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return address;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> addresses = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Adresy");

            while (resultSet.next()) {
                Address address = new Address();
                address.setID(resultSet.getInt("ID"));
                address.setStreet(resultSet.getString("street"));
                address.setBuildingNumber(resultSet.getString("buildingNumber"));
                address.setPostalCode(resultSet.getString("postalCode"));
                address.setCity(resultSet.getString("city"));
                address.setProvince(resultSet.getString("province"));
                address.setCountry(resultSet.getString("country"));

                addresses.add(address);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return addresses;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Adresy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Adresy WHERE ID = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(AbstractRecord record, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Address address = (Address) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Adresy SET " +
                    "ulica = ?, numerLokalu = ?, kodPocztowy = ?, miejscowosc = ?, wojewodztwo = ?, kraj = ? WHERE ID = ?");
            preparedStatement.setInt(7, id);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getBuildingNumber());
            preparedStatement.setString(3, address.getPostalCode());
            preparedStatement.setString(4, address.getCity());
            preparedStatement.setString(5, address.getProvince());
            preparedStatement.setString(6, address.getCountry());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE Adresy SET " +
                    "ulica = ?, numerLokalu = ?, kodPocztowy = ?, miejscowosc = ?, wojewodztwo = ?, kraj = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
