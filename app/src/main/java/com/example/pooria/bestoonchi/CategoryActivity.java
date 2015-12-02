package com.example.pooria.bestoonchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {
String [] Category={"Electronic","Clothes","HomeAplication","Personal","Sport","Gift","Supermarket"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        CategorySelect();

        //change activity
        Button btnSubmit=(Button)findViewById(R.id.button8);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
protected void CategorySelect(){

    GridView gridview=(GridView)findViewById(R.id.gridView);

    ArrayAdapter<String>categoryAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Category);

    gridview.setAdapter(categoryAdapter);

    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(CategoryActivity.this, "You selected:"+Category[position], Toast.LENGTH_SHORT).show();

        }
    });

}


}
