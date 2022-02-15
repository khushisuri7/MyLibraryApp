package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY="bookId";

    Button btnCurr,btnAddToFav,btnAlreadyRead,btnWantToRead;
    ImageView bookImg;
    TextView bkName,bkAuthor,bkDesc,bkPages,pagesInBook,authorName,nameOfBook,longDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        btnAddToFav=findViewById(R.id.btnAddToFav);
        btnCurr=findViewById(R.id.btnCurr);
        btnAlreadyRead=findViewById(R.id.btnAlreadyRead);
        btnWantToRead=findViewById(R.id.btnWantToRead);
        bookImg=findViewById(R.id.bookImg);
        bkAuthor=findViewById(R.id.bkAuthor);
        bkDesc=findViewById(R.id.bkDesc);
        bkName=findViewById(R.id.bkName);
        bkPages=findViewById(R.id.bkPages);
        pagesInBook=findViewById(R.id.pagesInBook);
        authorName=findViewById(R.id.authorName);
        nameOfBook=findViewById(R.id.nameOfBook);
        longDesc=findViewById(R.id.longDesc);
      //TODO: get data from recycler view
        String longDescription="A young woman named Aomame follows a taxi driver’s enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 “Q is for ‘question mark.’ A world that bears a question.” Meanwhile, an aspiring writer named Tengo takes on a suspect ghostwriting project. " +
                "He becomes so wrapped up with the work and its unusual author that, soon, his previously placid life begins to come unraveled." +
                "As Aomame’s and Tengo’s narratives converge over the course of this single year, we learn of the profound and tangled connections that bind them ever closer: a beautiful, dyslexic teenage girl with a unique vision; a mysterious religious cult that instigated a shoot-out with the metropolitan police; a reclusive, wealthy dowager who runs a shelter for abused women; a hideously ugly private investigator; a mild-mannered yet ruthlessly efficient bodyguard; and a peculiarly insistent television-fee collector.\n" +
                "\n" +
                "A love story, a mystery, a fantasy, a novel of self-discovery, a dystopia to rival George Orwell’s 1Q84 is Haruki Murakami’s most ambitious undertaking yet: an instant best seller in his native Japan, and a tremendous feat of imagination from one of our most revered contemporary writers.";
        Book book=new Book(1,"1Q84","Haruki Murakami",1350,"https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84.jpg",
                "A work of maddening brilliance",longDescription);

        Intent intent=getIntent();
        if(null!=intent){
            int bookId=intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId!=-1){
               Book incomingBook=Utils.getInstance(this).getBookById(bookId);
               if(null!=incomingBook)
                   setData(incomingBook);
               handleAlreadyRead(incomingBook);
               handleWantToReadBooks(incomingBook);
               handleCurrentlyReadingBook(incomingBook);
               handleFavouriteBooks(incomingBook);
            }
        }
    }

    private void handleFavouriteBooks(final Book book) {
        boolean existInFavouriteBooks=false;
        ArrayList<Book> favouriteBooks=Utils.getInstance(this).getFavouriteBooks();

        for(Book b:favouriteBooks){
            if(b.getId()==book.getId()){
                existInFavouriteBooks=true;
            }
        }
        if(existInFavouriteBooks){
            btnAddToFav.setEnabled(false);
        }
        else{
            btnAddToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFavourite(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        // navigate the user to another activity
                        Intent intent=new Intent(BookActivity.this,FavouriteBookActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened,try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBook(final Book book) {
        boolean existInCurrentlyReadingBooks=false;
        ArrayList<Book> currentlyReadingBook=Utils.getInstance(this).getCurrentlyReadingBooks();

        for(Book b:currentlyReadingBook){
            if(b.getId()==book.getId()){
                existInCurrentlyReadingBooks=true;
            }
        }
        if(existInCurrentlyReadingBooks){
            btnCurr.setEnabled(false);
        }
        else{
            btnCurr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        // navigate the user to another activity
                        Intent intent=new Intent(BookActivity.this,CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened,try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        boolean existInWantToReadBooks=false;
        ArrayList<Book> wantToReadBooks=Utils.getInstance(this).getWantToReadBooks();

        for(Book b:wantToReadBooks){
            if(b.getId()==book.getId()){
                existInWantToReadBooks=true;
            }
        }
        if(existInWantToReadBooks){
            btnWantToRead.setEnabled(false);
        }
        else{
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        // navigate the user to another activity
                        Intent intent=new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened,try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //Add the book to already added arraylist
    private void handleAlreadyRead(final Book book) {
        boolean existInAlreadyReadBooks=false;
        ArrayList<Book> alreadyReadBooks=Utils.getInstance(this).getAlreadyReadBooks();
        for(Book b:alreadyReadBooks){
            if(b.getId()==book.getId()){
                existInAlreadyReadBooks=true;
            }
        }
        if(existInAlreadyReadBooks){
            btnAlreadyRead.setEnabled(false);
        }
        else{
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                     Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                    // navigate the user to another activity
                     Intent intent=new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                     startActivity(intent);
                 }else {
                     Toast.makeText(BookActivity.this, "Something wrong happened,try again", Toast.LENGTH_SHORT).show();
                 }
                }
            });
        }
    }

    private void setData(Book book) {
        nameOfBook.setText(book.getName());
        authorName.setText(book.getAuthor());
        pagesInBook.setText(String.valueOf(book.getPages()));
        longDesc.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImgUrl()).into(bookImg);
    }
}