package implementation;

import kernel.AbstractRecord;
import kernel.RecordAdapter;
import records.People;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleImplementation extends RecordAdapter {
    @Override
    public void insert(AbstractRecord record) {
        People person = (People) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Ludzie (imie,nazwisko,adres,telefon,mail,login,haslo,stanowisko)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getAddress());
            preparedStatement.setString(4, person.getPhone());
            preparedStatement.setString(5, person.getMail());
            preparedStatement.setString(6, person.getLogin());
            preparedStatement.setString(7, person.getPassword());
            preparedStatement.setString(8, person.getPosition());

            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO Ludzie (imie,nazwisko,adres,telefon,mail,login,haslo,stanowisko)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
    public AbstractRecord selectById(String id) {
        People person = new People();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Ludzie WHERE login = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setAddress(resultSet.getInt("address"));
                person.setPhone(resultSet.getString("phone"));
                person.setMail(resultSet.getString("mail"));
                person.setLogin(resultSet.getString("login"));
                person.setPassword(resultSet.getString("password"));
                person.setPosition(resultSet.getString("position"));
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

        return person;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> people = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Ludzie");

            while (resultSet.next()) {
                People person = new People();
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setAddress(resultSet.getInt("address"));
                person.setPhone(resultSet.getString("phone"));
                person.setMail(resultSet.getString("mail"));
                person.setLogin(resultSet.getString("login"));
                person.setPassword(resultSet.getString("password"));
                person.setPosition(resultSet.getString("position"));

                people.add(person);
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

        return people;
    }


    @Override
    public void delete(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Ludzie WHERE login = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Ludzie WHERE login = ?");

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
    public void update(AbstractRecord record, String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        People person = (People) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Ludzie SET " +
                    "imie = ?, nazwisko = ?, adres = ?, telefon = ?, mail = ?, haslo = ?, stanowisko = ?  WHERE login = ?");

            preparedStatement.setString(8, id);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getAddress());
            preparedStatement.setString(4, person.getPhone());
            preparedStatement.setString(5, person.getMail());
            preparedStatement.setString(6, person.getPassword());
            preparedStatement.setString(7, person.getPosition());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE Ludzie SET " +
                    "imie = ?, nazwisko = ?, adres = ?, telefon = ?, mail = ?, haslo = ?, stanowisko = ?  WHERE login = ?");

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
