package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Author;

public interface IAuthorDao {

    int addAuthor(Author author);
    Author getAuthorByLogin(String login);
}
