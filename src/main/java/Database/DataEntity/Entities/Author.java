package Database.DataEntity.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private long id;
    private String name;
    private String login;
    private String email;

    public Author(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }
}
