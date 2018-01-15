package back.implementation;

import back.kernel.RecordAdapter;
import back.kernel.AbstractRecord;
import back.records.Author;
import back.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorImplementation extends RecordAdapter{
    @Override
    public void insert(AbstractRecord record) {
        Author author = (Author) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Autorzy (ID, imie, nazwisko)" +
                    "VALUES (?, ?, ?)");
            preparedStatement.setInt(1, author.getID());
            preparedStatement.setString(2, author.getFirstName());
            preparedStatement.setString(3, author.getLastName());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO Authors (imie, nazwisko)" +
                    "VALUES (?,?)");
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
        Author author = new Author();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Autorzy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                author.setID(resultSet.getInt(1));
                author.setFirstName(resultSet.getString(2));
                author.setLastName((resultSet.getString(3)));

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

        return author;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> authors = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Autorzy");

            while (resultSet.next()) {
                Author author = new Author();
                author.setID(resultSet.getInt(1));
                author.setFirstName(resultSet.getString(2));
                author.setLastName(resultSet.getString(3));

                authors.add(author);
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

        return authors;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Autorzy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Authors WHERE ID = ?");

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
        Author author = (Author) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Autorzy SET " +
                    "imie = ?, nazwisko = ? WHERE ID = ?");

            preparedStatement.setInt(3, id);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());

            preparedStatement.executeUpdate();

            System.out.println("UPDATE Authors SET " +
                    "firstName = ?, lastName = ?");

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
