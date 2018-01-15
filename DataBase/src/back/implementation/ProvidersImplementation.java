package back.implementation;

import back.kernel.AbstractRecord;
import back.kernel.RecordAdapter;
import back.records.Providers;
import back.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvidersImplementation extends RecordAdapter{
    @Override
    public void insert(AbstractRecord record) {
        Providers person = (Providers) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Dostawcy (NIP,nazwaFirmy,imieWlasciciela,nazwiskoWlasciciela,adres,telefon,mail,nrKonta)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, person.getNIP());
            preparedStatement.setString(2, person.getCompanyName());
            preparedStatement.setString(3, person.getOwnerFirstName());
            preparedStatement.setString(4, person.getOwnerLastName());
            preparedStatement.setInt(5, person.getAddress());
            preparedStatement.setString(6, person.getPhone());
            preparedStatement.setString(7, person.getMail());
            preparedStatement.setString(8, person.getAccountNumber());

            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO Dostawcy (NIP,nazwaFirmy,imieWlasciciela,nazwiskoWlasciciela,adres,telefon,mail,nrKonta)" +
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
        Providers person = new Providers();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Dostawcy WHERE login = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                person.setNIP(resultSet.getString(1));
                person.setCompanyName(resultSet.getString(2));
                person.setOwnerFirstName(resultSet.getString(3));
                person.setOwnerLastName(resultSet.getString(4));
                person.setAddress(resultSet.getInt(5));
                person.setPhone(resultSet.getString(6));
                person.setMail(resultSet.getString(7));
                person.setAccountNumber(resultSet.getString(8));
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
            resultSet = statement.executeQuery("SELECT * FROM Dostawcy");

            while (resultSet.next()) {
                Providers person = new Providers();
                person.setNIP(resultSet.getString(1));
                person.setCompanyName(resultSet.getString(2));
                person.setOwnerFirstName(resultSet.getString(3));
                person.setOwnerLastName(resultSet.getString(4));
                person.setAddress(resultSet.getInt(5));
                person.setPhone(resultSet.getString(6));
                person.setMail(resultSet.getString(7));
                person.setAccountNumber(resultSet.getString(8));

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
            preparedStatement = connection.prepareStatement("DELETE FROM Dostawcy WHERE login = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Dostawcy WHERE login = ?");

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
        Providers person = (Providers) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Dostawcy SET " +
                    "nazwaFirmy = ?, imieWlasciciela = ?, nazwiskoWlasciciela = ?, adres = ?, telefon = ?, mail = ?, nrKonta = ?  WHERE NIP = ?");

            preparedStatement.setString(8, id);
            preparedStatement.setString(1, person.getCompanyName());
            preparedStatement.setString(2, person.getOwnerFirstName());
            preparedStatement.setString(3, person.getOwnerLastName());
            preparedStatement.setInt(4, person.getAddress());
            preparedStatement.setString(5, person.getPhone());
            preparedStatement.setString(6, person.getMail());
            preparedStatement.setString(7, person.getAccountNumber());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE Dostawcy SET " +
                    "nazwaFirmy = ?, imieWlasciciela = ?, nazwiskoWlasciciela = ?, adres = ?, telefon = ?, mail = ?, nrKonta = ? WHERE NIP = ?");

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
