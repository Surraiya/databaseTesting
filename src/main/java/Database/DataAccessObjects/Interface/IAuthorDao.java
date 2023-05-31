package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Author;

public interface IAuthorDao {

    long addAuthorAndGetId(Author author);
    long getAuthorIdByLogin(String login);
    int deleteAuthorById(long id);
}
