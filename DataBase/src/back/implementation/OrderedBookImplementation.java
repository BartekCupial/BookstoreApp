package back.implementation;

import back.kernel.AbstractRecord;
import back.kernel.RecordAdapter;
import back.records.OrderedBook;
import back.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderedBookImplementation extends RecordAdapter{
    @Override
    public void insert(AbstractRecord record) {
        OrderedBook orderedBook = (OrderedBook) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO ZamowioneKsiazki (ID,ISBN,IDzamowienia,liczba)" +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, orderedBook.getID());
            preparedStatement.setString(2, orderedBook.getISBN());
            preparedStatement.setInt(3, orderedBook.getOrderID());
            preparedStatement.setInt(4, orderedBook.getNumber());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO ZamowioneKsiazki (ID,ISBN,IDzamowienia,liczba)" +
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
        OrderedBook orderedBook = new OrderedBook();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM ZamowioneKsiazki WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orderedBook.setID(resultSet.getInt(1));
                orderedBook.setISBN(resultSet.getString(2));
                orderedBook.setOrderID(resultSet.getInt(3));
                orderedBook.setNumber(resultSet.getInt(4));
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

        return orderedBook;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> orderedBooks = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ZamowioneKsiazki");

            while (resultSet.next()) {
                OrderedBook orderedBook = new OrderedBook();
                orderedBook.setID(resultSet.getInt(1));
                orderedBook.setISBN(resultSet.getString(2));
                orderedBook.setOrderID(resultSet.getInt(3));
                orderedBook.setNumber(resultSet.getInt(4));
                orderedBooks.add(orderedBook);
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

        return orderedBooks;
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
        OrderedBook orderedBook = (OrderedBook) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE ZamowioneKsiazki SET " +
                    "ISBN = ?, IDzamowienia = ?, liczba = ?  WHERE ID = ?");


            preparedStatement.setString(1, orderedBook.getISBN());
            preparedStatement.setInt(2, orderedBook.getOrderID());
            preparedStatement.setInt(3, orderedBook.getNumber());
            preparedStatement.setInt(4, ID);

            preparedStatement.executeUpdate();

            System.out.println("UPDATE ZamowioneKsiazki SET " +
                    "ISBN = ?, IDzamowienia = ?, liczba = ?  WHERE ID = ?");

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
