package sk.elct.notes;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {
    INSTANCE;
    
    private NoteDao noteDao;
    
    public NoteDao getNoteDao() {
        if (noteDao == null) {
            noteDao = new MysqlNoteDao(getJdbcTemplate());
        }
        return noteDao;
    }

    private JdbcTemplate getJdbcTemplate() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("notes");
        dataSource.setPassword("notes");
        dataSource.setUrl(
                "jdbc:mysql://localhost/notes?"
                + "serverTimezone=Europe/Bratislava");
        return new JdbcTemplate(dataSource);
    }

}
