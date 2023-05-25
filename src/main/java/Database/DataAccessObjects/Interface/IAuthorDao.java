package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Author;

import java.sql.SQLException;
import java.util.List;

public interface IAuthorDao {

    boolean addAuthor(Author author) throws SQLException;
    List<Author> getAllAuthors() throws SQLException;
    Author getAuthorByLogin(String login) throws SQLException;
}
