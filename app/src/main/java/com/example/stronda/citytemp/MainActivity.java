package com.example.stronda.citytemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Cidade> cidadeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CidadeAdapter mAdapter;
    private Cidade cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);

        mAdapter = new CidadeAdapter(cidadeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Cidade cidade = cidadeList.get(position);

                /*if (cidade.getId()==4){
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }*/
                    Intent intent = new Intent(getApplicationContext(), CidadesActivity.class);
                    intent.putExtra("id", cidade.getId());
                    startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Cidade cidade = cidadeList.get(position);
                Toast.makeText(getApplicationContext(),cidade.getNome() + " ", Toast.LENGTH_SHORT).show();
            }
        }));

        insertData();
    }

    private void insertData() {
        cidade = new Cidade("CURITIBA",1);
        cidadeList.add(cidade);

        cidade = new Cidade("SÃO PAULO",2);
        cidadeList.add(cidade);

        cidade = new Cidade("FLORIANÓPOLIS",3);
        cidadeList.add(cidade);

        cidade = new Cidade("Maps",4);
        cidadeList.add(cidade);

        mAdapter.notifyDataSetChanged();

    }
}
