package implementation;

import kernel.AbstractRecord;
import kernel.RecordAdapter;
import records.Order;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderImplementation extends RecordAdapter{
    @Override
    public int insertint(AbstractRecord record) {
        Order order = (Order) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int generatedKey = 0;
        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Zamowienia (ID,IDzamawiajacego,dataZamowienia,statusZamowienia)" +
                    "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getID());
            preparedStatement.setString(2, order.getOrderID());
            preparedStatement.setString(3, order.getDate());
            preparedStatement.setString(4, order.getStatus());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                generatedKey = resultSet.getInt(1);
            }

            System.out.println("INSERT INTO Zamowienia (ID,IDzamawiajacego,dataZamowienia,`statusZamówienia`)" +
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
        return generatedKey;
    }

    @Override
    public AbstractRecord selectById(String id) {
        Order order = new Order();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Zamowienia WHERE ID = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                order.setID(resultSet.getInt(1));
                order.setOrderID(resultSet.getString(2));
                order.setDate(resultSet.getString(3));
                order.setStatus(resultSet.getString(4));
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

        return order;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> orders = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Zamowienia");

            while (resultSet.next()) {
                Order order = new Order();
                order.setID(resultSet.getInt(1));
                order.setOrderID(resultSet.getString(2));
                order.setDate(resultSet.getString(3));
                order.setStatus(resultSet.getString(4));
                orders.add(order);
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

        return orders;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Zamowienia WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Zamówienia WHERE ID = ?");

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
        Order order = (Order) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Zamowienia SET " +
                    "IDzamawiajacego = ?, dataZamowienia = ?, statusZamowienia = ?  WHERE ID = ?");


            preparedStatement.setString(1, order.getOrderID());
            preparedStatement.setString(2, order.getDate());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setInt(4, ID);

            preparedStatement.executeUpdate();

            System.out.println("UPDATE Dostawy SET " +
                    "IDzamawiajacego = ?, dataDostawy = ?, status = ?  WHERE ID = ?");

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
