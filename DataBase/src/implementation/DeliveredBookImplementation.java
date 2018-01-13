package implementation;

import kernel.AbstractRecord;
import kernel.RecordAdapter;
import records.DeliveredBook;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveredBookImplementation extends RecordAdapter{
    @Override
    public void insert(AbstractRecord record) {
        DeliveredBook deliveredBook = (DeliveredBook) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Dotowarowanie (ID,ISBN,IDdostawy,liczba)" +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, deliveredBook.getID());
            preparedStatement.setString(2, deliveredBook.getISBN());
            preparedStatement.setInt(3, deliveredBook.getDeliveryID());
            preparedStatement.setInt(4, deliveredBook.getNumber());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO ZamowioneKsiazki (ID,ISBN,IDdostawy,liczba)" +
                    "VALUES (?, ?, ?, ?)");
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
        DeliveredBook deliveredBook = new DeliveredBook();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Dotowarowanie WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                deliveredBook.setID(resultSet.getInt(1));
                deliveredBook.setISBN(resultSet.getString(2));
                deliveredBook.setDeliveryID(resultSet.getInt(3));
                deliveredBook.setNumber(resultSet.getInt(4));
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

        return deliveredBook;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> deliveredBooks = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Dotowarowanie");

            while (resultSet.next()) {
                DeliveredBook deliveredBook = new DeliveredBook();
                deliveredBook.setID(resultSet.getInt(1));
                deliveredBook.setISBN(resultSet.getString(2));
                deliveredBook.setDeliveryID(resultSet.getInt(3));
                deliveredBook.setNumber(resultSet.getInt(4));
                deliveredBooks.add(deliveredBook);
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

        return deliveredBooks;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Dotowarowanie WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Dotowarowanie WHERE ID = ?");

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
        DeliveredBook deliveredBook = (DeliveredBook) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Dotowarowanie SET " +
                    "ISBN = ?, IDdostawy = ?, liczba = ?  WHERE ID = ?");


            preparedStatement.setString(1, deliveredBook.getISBN());
            preparedStatement.setInt(2, deliveredBook.getDeliveryID());
            preparedStatement.setInt(3, deliveredBook.getNumber());
            preparedStatement.setInt(4, ID);

            preparedStatement.executeUpdate();

            System.out.println("UPDATE Dotowarowanie SET " +
                    "ISBN = ?, IDdostawy = ?, liczba = ?  WHERE ID = ?");

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
