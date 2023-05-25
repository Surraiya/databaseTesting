package Database.DataEntity.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private long id;
    private long build_number;
    private LocalDateTime created_time;
    private String session_key;

    public Session(long build_number, LocalDateTime created_time, String session_key){
        this.build_number = build_number;
        this.created_time = created_time;
        this.session_key = session_key;
    }
}