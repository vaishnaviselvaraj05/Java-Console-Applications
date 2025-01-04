package ListOfNotes;
import Notes.Notes;
// A subclass of Notes that represents the 100 denomination currency note.
public class Note100 extends Notes {
    // Constructor to initialize a Note100 object.
    // @param noteName The name or denomination of the note (e.g., "100").
    // @param count The initial count of 100 denomination notes.
    public Note100(String noteName, int count) {
        super(noteName, count);// Call the constructor of the superclass (Notes) to set noteName and count.
    }
}