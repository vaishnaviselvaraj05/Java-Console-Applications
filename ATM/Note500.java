package ATM;
 // A subclass of Notes that represents the 500 denomination currency note.
public class Note500 extends Notes {
     // Constructor to initialize a Note500 object.
     // @param noteName The name or denomination of the note (e.g., "500").
     // @param count The initial count of 500 denomination notes.
    public Note500(String noteName, int count) {
        super(noteName, count);// Call the constructor of the superclass (Notes) to set noteName and count.
    }
}
