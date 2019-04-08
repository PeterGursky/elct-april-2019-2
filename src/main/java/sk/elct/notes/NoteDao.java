package sk.elct.notes;

import java.util.List;

public interface NoteDao {

    List<Note> getAll();
    
    void save(Note note);
}
