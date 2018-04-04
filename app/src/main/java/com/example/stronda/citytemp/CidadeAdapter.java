package com.example.stronda.citytemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CidadeAdapter extends RecyclerView.Adapter<CidadeAdapter.MyViewHolder> {
    private List<Cidade> cidadeList;

    public CidadeAdapter(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cidade_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cidade cidade = cidadeList.get(position);
        holder.nome_cidade.setText(cidade.getNome());

    }

    @Override
    public int getItemCount() {
        return cidadeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nome_cidade;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome_cidade = itemView.findViewById(R.id.nome_cidade);
        }
    }
}
