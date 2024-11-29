/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noteapp;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class NoteManager {
    private ArrayList<note> notes;

    public NoteManager() {
        this.notes = new ArrayList<>();
    }

    public void addNote(note note) {
        notes.add(note);
    }

    public void updateNote(int index, note note) {
        if (index >= 0 && index < notes.size()) {
            notes.set(index, note);
        }
    }

    public void deleteNote(int index) {
        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
        }
    }

    public ArrayList<note> getNotes() {
        return notes;
    }
}

