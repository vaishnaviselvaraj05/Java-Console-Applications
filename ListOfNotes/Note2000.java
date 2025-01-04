package ListOfNotes;
import Notes.Notes;
// A subclass of Notes that represents the 2000 denomination currency note.
public class Note2000 extends Notes {
    // Constructor to initialize a Note2000 object.
    // @param noteName The name or denomination of the note (e.g., "2000").
    // @param count The initial count of 2000 denomination notes.
    public Note2000(String noteName, int count) {
        super(noteName, count);// Call the constructor of the superclass (Notes) to set noteName and count.
    }
}