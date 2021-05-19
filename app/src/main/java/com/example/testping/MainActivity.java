package com.example.testping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton imgbtnip,imgbtne;
    ListView listviewping;
    EditText editip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializecomponents();
    }

    private void initializecomponents(){
        listviewping=findViewById(R.id.listviewping);
        editip=findViewById(R.id.editip);
        imgbtnip=findViewById(R.id.imgbtnip);
        imgbtnip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fExcecutarPing();
            }
        });
        imgbtne=findViewById(R.id.imgbtne);
        imgbtne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fExcecutarPing(){
        Editable host=editip.getText();
        List<String>listaResponstaPing=new ArrayList<String>();
        ArrayAdapter<String> adapterLista=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                listaResponstaPing);
        try{
            String cmdPing="ping -c 4 "+host;
            Runtime r=Runtime.getRuntime();
            Process p=r.exec(cmdPing);
            BufferedReader in =new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLinhe;
            while((inputLinhe=in.readLine())!=null){
                listaResponstaPing.add(inputLinhe);
                listviewping.setAdapter(adapterLista);
            }
            Toast.makeText(this,"The command was successfully completed!",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }finally {
            editip.setText("");
        }
    }
}
