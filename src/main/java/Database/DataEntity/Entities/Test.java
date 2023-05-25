package Database.DataEntity.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private long id;
    private String name;
    private long author_id;
    private long project_id;
    private long session_id;
    private String env;
    private String browser;
    private String method_name;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int status_id;

    public Test(String name,
                long author_id,
                long project_id,
                long session_id,
                String env,
                String browser,
                String method_name,
                LocalDateTime start_time,
                LocalDateTime end_time,
                int status_id) {
        this.name = name;
        this.author_id = author_id;
        this.project_id = project_id;
        this.session_id = session_id;
        this.env = env;
        this.browser = browser;
        this.method_name = method_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status_id = status_id;
    }
}
