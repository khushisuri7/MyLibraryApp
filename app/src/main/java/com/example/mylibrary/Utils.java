package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String ALL_BOOKS_KEY="all_books";
    private static final String ALREADY_READ_BOOKS="already_read_books";
    private static final String WANT_TO_READ_BOOKS="want_to_read_books";
    private static final String FAVOURITE_BOOKS="favourite_books";
    private static final String CURRENTLY_READING_BOOKS="currently_reading_books";

    private static Utils instance;

    private SharedPreferences sharedPreferences;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> favouriteBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> wantToReadBooks;

    private Utils(Context context) {

        sharedPreferences= context.getSharedPreferences("alternate db",Context.MODE_PRIVATE);
        if(null==getAllBooks()){
            allBooks=new ArrayList<>();
            initData();
        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        if(null==getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null==getFavouriteBooks()){
            editor.putString(FAVOURITE_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null==getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null==getWantToReadBooks()){
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        //TODO: add initial data

        ArrayList<Book> books=new ArrayList<>();
        books.add(new Book(1,"1Q84","Haruki Murakami",1350,"https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84.jpg",
                "A work of maddening brilliance","A young woman named Aomame follows a taxi driver’s enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 “Q is for ‘question mark.’ A world that bears a question.” Meanwhile, an aspiring writer named Tengo takes on a suspect ghostwriting project. \" +\n" +
                "                \"He becomes so wrapped up with the work and its unusual author that, soon, his previously placid life begins to come unraveled.\" "));
       books.add(new Book(2,"Wings of Fire","APJ Abdul Kalam",180,"https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1588286863l/634583._SY475_.jpg",
                "A work of maddening brilliance","The Wings of Fire is an autobiography of former Indian President APJ Abdul Kalam. ... The book covers his life before he became the President of India and commanded the armed forces, his schooling and formative years, what led to his interest in space and research as well as his biggest influences in life."));
        books.add(new Book(3,"A Perfect Murder","Ruskin Bond",175,"https://images-na.ssl-images-amazon.com/images/I/61Gzy4y2gLL.jpg",
                "A work of maddening brilliance","The story is told in the first person by a married man who has been having an affair with beautiful, 32-year-old Pimlico secretary Carla Moorland. After he sees another man leaving her flat, he assumes it's her lover and the two quarrel, ending in him accidentally striking her dead."));
        books.add(new Book(4,"The Adventures of Huckleberry Finn","Mark Twain.",366,"https://kbimages1-a.akamaihd.net/972c8403-917d-4a94-9806-64f96aa29b44/1200/1200/False/adventures-of-huckleberry-finn-136.jpg",
                "A work of maddening brilliance","Adventures of Huckleberry Finn is one of Mark Twain's best-known and most important novels. The novel tells the story of Huckleberry Finn's escape from his alcoholic and abusive father and Huck's adventurous journey down the Mississippi River together with the runaway slave Jim."));
        books.add(new Book(5,"Madame Bovary"," Gustave Flaubert",368,"https://images-na.ssl-images-amazon.com/images/I/815EBrS05mL.jpg",
                "A work of maddening brilliance","Madame Bovary, novel by Gustave Flaubert, serialized in the Revue de Paris in 1856 and then published in two volumes the following year. Flaubert transformed a commonplace story of adultery into an enduring work of profound humanity. Madame Bovary is considered Flaubert’s masterpiece, and, according to some, it ushered in a new age of realism in literature"));
        books.add(new Book(6,"Middlemarch"," George Eliot",750,"https://m.media-amazon.com/images/I/61EioOixctL.jpg",
                "A work of maddening brilliance","Set in Middlemarch, a fictional English Midland town, in 1829 to 1832, it follows distinct, intersecting stories with many characters. Issues include the status of women, the nature of marriage, idealism, self-interest, religion, hypocrisy, political reform, and education."));

        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();

    }

    public static Utils getInstance(Context context) {
        if(null!=instance)
        return instance;
        else
        {
            instance=new Utils(context);
            return instance;
        }
    }

    public  ArrayList<Book> getAllBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getFavouriteBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getWantToReadBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }
    public Book getBookById(int id){
        ArrayList<Book> books=getAllBooks();
        if(null!=books){
            for(Book b:books){
                if(b.getId()==id){
                    return b;
                }
            }
        }

         return null;
    }
    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToFavourite(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if(books.remove(b)){
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(ALREADY_READ_BOOKS);
                    editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
    }
        return false;
    }
    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromFavourite(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
