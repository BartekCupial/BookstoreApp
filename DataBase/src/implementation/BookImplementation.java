package implementation;

import kernel.AbstractRecord;
import kernel.RecordAdapter;
import records.Book;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookImplementation extends RecordAdapter{
    @Override
    public void insert(AbstractRecord record) {
        Book book = (Book) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Ksiazki (ISBN,tytul,autor,dzial,liczba,wydawnictwo,rokWydania,cena,opis)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, book.getISBN());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getAuthor());
            preparedStatement.setInt(4, book.getSection());
            preparedStatement.setInt(5, book.getNumber());
            preparedStatement.setString(6, book.getPublished());
            preparedStatement.setInt(7, book.getYear());
            preparedStatement.setInt(8, book.getPrice());
            preparedStatement.setString(9, book.getDescription());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO Ksiazki (ISBN,tytul,autor,dzial,liczba,wydawnictwo,rokWydania,cena,opis)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
        Book book = new Book();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Ksiazki WHERE ISBN = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book.setISBN(resultSet.getString(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getInt(3));
                book.setSection(resultSet.getInt(4));
                book.setNumber(resultSet.getInt(5));
                book.setPublished(resultSet.getString(6));
                book.setYear(resultSet.getInt(7));
                book.setPrice(resultSet.getInt(8));
                book.setDescription(resultSet.getString(9));
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

        return book;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> books = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Ksiazki");

            while (resultSet.next()) {
                Book book = new Book();
                book.setISBN(resultSet.getString(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getInt(3));
                book.setSection(resultSet.getInt(4));
                book.setNumber(resultSet.getInt(5));
                book.setPublished(resultSet.getString(6));
                book.setYear(resultSet.getInt(7));
                book.setPrice(resultSet.getInt(8));
                book.setDescription(resultSet.getString(9));

                books.add(book);
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

        return books;
    }


    @Override
    public void delete(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Ksiazki WHERE ISBN = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Ksiazki WHERE ISBN = ?");

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
        Book book = (Book) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Ksiazki SET " +
                    "tytul = ?, autor = ?, dzial = ?, liczba = ?, wydawnictwo = ?, rokWydania = ?, cena = ?, opis = ?  WHERE ISBN = ?");

            preparedStatement.setString(9, id);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthor());
            preparedStatement.setInt(3, book.getSection());
            preparedStatement.setInt(4, book.getNumber());
            preparedStatement.setString(5, book.getPublished());
            preparedStatement.setInt(6, book.getYear());
            preparedStatement.setInt(7, book.getPrice());
            preparedStatement.setString(8, book.getDescription());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE Ksiazki SET " +
                    "title = ?, author = ?, section = ?, number = ?, published = ?, year = ?, price = ?, description = ? WHERE ISBN = ?");

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

