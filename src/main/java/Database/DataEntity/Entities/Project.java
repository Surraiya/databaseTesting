package Database.DataEntity.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private long id;

    private String name;

    public Project(String name) {
        this.name = name;
    }
}
