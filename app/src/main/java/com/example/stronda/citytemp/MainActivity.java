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
                //Cidade cidade = cidadeList.get(position);
                //Toast.makeText(getApplicationContext(),cidade.getNome() + " ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), CidadesActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Cidade cidade = cidadeList.get(position);
                Toast.makeText(getApplicationContext(),cidade.getNome() + " ", Toast.LENGTH_LONG).show();
            }
        }));


        inserirDadosLista();

    }

    private void inserirDadosLista() {
        cidade = new Cidade("CURITIBA");
        cidadeList.add(cidade);

        cidade = new Cidade("SÃO PAULO");
        cidadeList.add(cidade);

        cidade = new Cidade("FLORIANÓPOLIS");
        cidadeList.add(cidade);


        mAdapter.notifyDataSetChanged();

    }
}
