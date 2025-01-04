package ListOfNotes;
import Notes.Notes;
// A subclass of Notes that represents the 200 denomination currency note.
public class Note200 extends Notes {
    // Constructor to initialize a Note200 object.
    // @param noteName The name or denomination of the note (e.g., "200").
    // @param count The initial count of 200 denomination notes.
    public Note200(String noteName, int count) {
        super(noteName, count);// Call the constructor of the superclass (Notes) to set noteName and count.
    }
}