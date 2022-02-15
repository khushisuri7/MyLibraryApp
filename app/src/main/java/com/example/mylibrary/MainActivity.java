package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAllBooks,btnCurBooks,btnReadBooks,btnWishlist,btnFav,about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AllBooksActivity.class);
                startActivity(intent);

            }
        });
        btnReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AlreadyReadBookActivity.class);
                startActivity(intent);
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FavouriteBookActivity.class);
                startActivity(intent);
            }
        });
        btnCurBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CurrentlyReadingBookActivity.class);
                startActivity(intent);
            }
        });
        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WantToReadActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and developed with love by Khushi\n"+"For more information visit the website:");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     Intent intent=new Intent(MainActivity.this,WebsiteActivity.class);
                     intent.putExtra("url","https://google.com/");
                     startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
        Utils.getInstance(this);//to avoide null pointer exceptions
    }
    private void initViews(){
        btnAllBooks=findViewById(R.id.btnAllBooks);
        btnCurBooks=findViewById(R.id.btnCurBooks);
        btnReadBooks=findViewById(R.id.btnReadBooks);
        btnWishlist=findViewById(R.id.btnWishlist);
        btnFav=findViewById(R.id.btnFav);
        about=findViewById(R.id.about);
    }
}