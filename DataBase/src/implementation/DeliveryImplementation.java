package implementation;

import kernel.AbstractRecord;
import kernel.RecordAdapter;
import records.Delivery;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryImplementation extends RecordAdapter {
    @Override
    public void insert(AbstractRecord record) {
        Delivery delivery = (Delivery) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Dostawy (ID,NIP,nrFaktury,dataDostawy,status)" +
                    "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, delivery.getID());
            preparedStatement.setString(2, delivery.getNIP());
            preparedStatement.setString(3, delivery.getInvoiceNumber());
            preparedStatement.setString(4, delivery.getDate());
            preparedStatement.setString(5, delivery.getStatus());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO Dostawy Dostawy (ID,NIP,nrFaktury,dataDostawy,status)" +
                    "VALUES (?, ?, ?, ?, ?)");
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
    }

    @Override
    public AbstractRecord selectById(int id) {
        Delivery delivery = new Delivery();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Dostawy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                delivery.setID(resultSet.getInt(1));
                delivery.setNIP(resultSet.getString(2));
                delivery.setInvoiceNumber(resultSet.getString(3));
                delivery.setDate(resultSet.getString(4));
                delivery.setStatus(resultSet.getString(5));
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

        return delivery;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> deliverys = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Dostawy");

            while (resultSet.next()) {
                Delivery delivery = new Delivery();
                delivery.setID(resultSet.getInt("ID"));
                delivery.setNIP(resultSet.getString("NIP"));
                delivery.setInvoiceNumber(resultSet.getString("invoiceNumber"));
                delivery.setDate(resultSet.getString("date"));
                delivery.setStatus(resultSet.getString("status"));
                deliverys.add(delivery);
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

        return deliverys;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Dostawy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Dostawy WHERE ID = ?");

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
    public void update(AbstractRecord record, int ID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Delivery delivery = (Delivery) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Dostawy SET " +
                    "NIP = ?, nrFaktury = ?, dataDostawy = ?, status = ?  WHERE ID = ?");


            preparedStatement.setString(1, delivery.getNIP());
            preparedStatement.setString(2, delivery.getInvoiceNumber());
            preparedStatement.setString(3, delivery.getDate());
            preparedStatement.setString(4, delivery.getStatus());
            preparedStatement.setInt(5, ID);

            preparedStatement.executeUpdate();

            System.out.println("UPDATE Dostawy SET " +
                    "NIP = ?, nrFaktury = ?, dataDostawy = ?, status = ?  WHERE ID = ?");

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
