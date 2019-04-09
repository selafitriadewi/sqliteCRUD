package id.ac.polinema.notesapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import id.ac.polinema.notesapp.dao.NoteDao;
import id.ac.polinema.notesapp.dao.NoteDao;
import id.ac.polinema.notesapp.db.AppDatabase;
import id.ac.polinema.notesapp.models.Note;
import id.ac.polinema.notesapp.models.Note;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        noteDao=db.noteDao();
        notes = noteDao.getAll();
    }
    public LiveData<List<Note>> getNotes() {
        return notes;
    }
    public void insert(Note note) {

        new InsertAsyncTask(noteDao).execute(note);
    }
    public void update(Note note) {
        new UpdateAsyncTask(noteDao).execute(note);
    }
    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao asyncNoteDao;

        InsertAsyncTask(NoteDao noteDao) {
            asyncNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            asyncNoteDao.insert(notes);
            return null;
        }

    }
    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao asyncNoteDao;

        UpdateAsyncTask(NoteDao dao) {
            asyncNoteDao = dao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            asyncNoteDao.insert(notes);
            return null;
        }

    }

}
