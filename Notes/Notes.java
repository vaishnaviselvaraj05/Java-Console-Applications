package Notes;
// Class to represent a denomination of currency and its count in the ATMAPP.ATM.
public class Notes implements Cloneable {
    private String noteName;// The name or value of the currency note (e.g., "2000", "500")
    private long count;// The number of notes of this denomination
    //Constructor to initialize the denomination and count of the notes.
    //@param noteName The value of the currency note.
    //@param count The initial count of this denomination.
    protected Notes(String noteName, long count) {
        this.noteName = noteName;// Set the name of the note.
        this.count = count;// Set the count of the notes.
    }
    // Getter method to retrieve the denomination of the note.
    public String getNoteName() {
        return noteName;// Return the value of the note.
    }
    //Getter method to retrieve the count of notes for this denomination.
    public long getCount() {
        return count;// Return the count of the notes.
    }
    // Setter method to update the count of notes for this denomination.
    //@param count The new count of notes.
    public void setCount(long count) {
        this.count = count;// Update the count of the notes.
    }
    // Setter method to update the denomination of the note.
    // @param noteName The new denomination as a string.
    public void setNoteName(String noteName) {
        this.noteName = noteName;// Update the value of the note.
    }
    // Method to create a copy of the current Notes object.
    // @return A cloned Notes object.
    // @throws CloneNotSupportedException If cloning is not supported.
    @Override
    public Notes clone() throws CloneNotSupportedException {
        return (Notes) super.clone();// Perform a shallow copy using the Object class's clone method.
    }
}