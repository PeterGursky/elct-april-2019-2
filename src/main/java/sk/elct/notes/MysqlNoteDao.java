package sk.elct.notes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlNoteDao implements NoteDao {

    JdbcTemplate jdbcTemplate;

    public MysqlNoteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Note> getAll() {
        String sql = "SELECT note.id, note.title, note.text, "
                + "note.done, note.deadline, note.id_type, "
                + "note_type.type "
                + "FROM note "
                + "JOIN note_type "
                + "ON note.id_type = note_type.id";
        return jdbcTemplate.query(sql, new RowMapper<Note>() {
            @Override
            public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
                Note note = new Note();
                note.setId(rs.getLong("id"));
                note.setTitle(rs.getString("title"));
                note.setText(rs.getString("text"));
                note.setDone(rs.getBoolean("done"));
                Timestamp timestamp = rs.getTimestamp("deadline");
                if (timestamp != null) {
                    note.setDeadline(
                            timestamp.toLocalDateTime().toLocalDate());
                }
                NoteType type = new NoteType();
                type.setId(rs.getLong("id_type"));
                type.setType(rs.getString("type"));
                note.setType(type);
                return note;
            }
        });
    }

    @Override
    public void save(Note note) {
        if (note.getId() == null) {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("note");
            insert.usingGeneratedKeyColumns("id");
            insert.usingColumns("title","text",
                "done", "deadline", "id_type");

            Map<String, Object> values = new HashMap<>();
            values.put("title", note.getTitle());
            values.put("text", note.getText());
            values.put("done", note.isDone());
            values.put("deadline", note.getDeadline());
            if (note.getType() == null) {
                values.put("id_type", 1);
            } else {
                values.put("id_type", note.getType().getId());
            }

            Long id = insert.executeAndReturnKey(values).longValue();
            note.setId(id);
            
        } else {
            String sql = "UPDATE note SET title=?, text=?, "
                    + "done=?, deadline=?, id_type=? WHERE id=?";
            jdbcTemplate.update(sql, note.getTitle(), note.getText(),
                    note.isDone(), note.getDeadline(),
                    note.getType().getId(), note.getId());
        }
    }
}
