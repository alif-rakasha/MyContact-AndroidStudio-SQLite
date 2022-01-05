package pam.latihan.uas_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbhelper;
    Button buttonsave;
    EditText nama, telp, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        dbhelper = new DBHelper(this);
        nama = findViewById(R.id.namakontak);
        telp = findViewById(R.id.notelp);
        email = findViewById(R.id.email);
        buttonsave = findViewById(R.id.buttonsave);
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.execSQL("INSERT INTO kontak(nama, telp, email) values('" +
                        nama.getText().toString() + "','" +
                        telp.getText().toString() + "','" +
                        email.getText().toString() + "')");
                Toast.makeText(CreateActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivity.mainact.RefreshList();
                finish();
            }
        });
    }
}
