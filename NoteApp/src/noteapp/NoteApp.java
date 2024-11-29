/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package noteapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */

public class NoteApp extends JFrame {
    private NoteManager noteManager;
    private JList<note> noteList;
    private JTextArea noteContent;
    private JTextField noteTitle;

    public NoteApp() {
        noteManager = new NoteManager();

        setTitle("Daily Notes");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for Note List
        JPanel listPanel = new JPanel(new BorderLayout());
        noteList = new JList<>();
        noteList.addListSelectionListener(e -> loadSelectedNote());
        JScrollPane listScrollPane = new JScrollPane(noteList);
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Note");
        addButton.addActionListener(e -> addNewNote());
        JButton deleteButton = new JButton("Delete Note");
        deleteButton.addActionListener(e -> deleteSelectedNote());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        listPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel for Note Editor
        JPanel editorPanel = new JPanel(new BorderLayout());
        noteTitle = new JTextField();
        noteContent = new JTextArea();
        noteContent.setLineWrap(true);
        noteContent.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(noteContent);
        JButton saveButton = new JButton("Save Note");
        saveButton.addActionListener(e -> saveNote());

        editorPanel.add(noteTitle, BorderLayout.NORTH);
        editorPanel.add(contentScrollPane, BorderLayout.CENTER);
        editorPanel.add(saveButton, BorderLayout.SOUTH);

        // Add panels to frame
        add(listPanel, BorderLayout.WEST);
        add(editorPanel, BorderLayout.CENTER);

        refreshNoteList();
    }

    private void addNewNote() {
        String title = JOptionPane.showInputDialog(this, "Enter Note Title:");
        if (title != null && !title.trim().isEmpty()) {
            noteManager.addNote(new note(title, ""));
            refreshNoteList();
        }
    }

    private void deleteSelectedNote() {
        int selectedIndex = noteList.getSelectedIndex();
        if (selectedIndex != -1) {
            noteManager.deleteNote(selectedIndex);
            refreshNoteList();
        }
    }

    private void loadSelectedNote() {
        int selectedIndex = noteList.getSelectedIndex();
        if (selectedIndex != -1) {
            note selectedNote = noteManager.getNotes().get(selectedIndex);
            noteTitle.setText(selectedNote.getTitle());
            noteContent.setText(selectedNote.getContent());
        }
    }

    private void saveNote() {
        int selectedIndex = noteList.getSelectedIndex();
        if (selectedIndex != -1) {
            note updatedNote = new note(noteTitle.getText(), noteContent.getText());
            noteManager.updateNote(selectedIndex, updatedNote);
            refreshNoteList();
        }
    }

    private void refreshNoteList() {
        DefaultListModel<note> listModel = new DefaultListModel<>();
        for (note note : noteManager.getNotes()) {
            listModel.addElement(note);
        }
        noteList.setModel(listModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NoteApp app = new NoteApp();
            app.setVisible(true);
        });
    }
}

