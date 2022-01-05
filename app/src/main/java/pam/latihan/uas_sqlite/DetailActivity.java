package pam.latihan.uas_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbhelper;
    TextView nama, telp, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbhelper = new DBHelper(this);
        nama = findViewById(R.id.namakontak);
        telp = findViewById(R.id.notelp);
        email = findViewById(R.id.email);

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kontak WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            telp.setText(cursor.getString(1).toString());
            email.setText(cursor.getString(2).toString());
        }
    }
}

