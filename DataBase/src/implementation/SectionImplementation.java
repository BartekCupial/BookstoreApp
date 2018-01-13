package implementation;

import kernel.AbstractRecord;
import kernel.RecordAdapter;
import records.Section;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionImplementation extends RecordAdapter {
    @Override
    public void insert(AbstractRecord record) {
        Section section = (Section) record;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Dzialy (ID, nazwa)" +
                    "VALUES (?, ?)");
            preparedStatement.setInt(1, section.getID());
            preparedStatement.setString(2, section.getName());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO Dzialy (name)" +
                    "VALUES (?)");
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
        Section section = new Section();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Dzialy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                section.setID(resultSet.getInt(1));
                section.setName(resultSet.getString(2));

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

        return section;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        List<AbstractRecord> sections = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Dzialy");

            while (resultSet.next()) {
                Section section = new Section();
                section.setID(resultSet.getInt(1));
                section.setName(resultSet.getString(2));


                sections.add(section);
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

        return sections;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Dzialy WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Dzialy WHERE ID = ?");

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
        Section section = (Section) record;
        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Dzialy SET " +
                    "nazwa = ? WHERE ID = ?");

            preparedStatement.setInt(2, id);
            preparedStatement.setString(1, section.getName());

            preparedStatement.executeUpdate();

            System.out.println("UPDATE Dzialy SET " +
                    "name = ?");

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
