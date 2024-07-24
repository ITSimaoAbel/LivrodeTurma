package com.jcompany.livrodeturma;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterSinais extends RecyclerView.Adapter<AdapterSinais.ViewHolder> {

    List<String> nome;
    List<String> data;
    List<String> sumario;
    List<String> referencia;

    LayoutInflater inflater;

    public AdapterSinais(Context ctx, List<String> nome, List<String> data, List<String> sumario, List<String> referencia){
        this.nome = nome;
        this.data = data;
        this.sumario = sumario;
        this.referencia = referencia;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.itemaula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nome.setText(nome.get(position));
    }

    @Override
    public int getItemCount() {
        return nome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView data;
        TextView sumario;
        TextView referencia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tnome);
            data = itemView.findViewById(R.id.tdata);
            sumario = itemView.findViewById(R.id.tsumario);
            referencia = itemView.findViewById(R.id.treferencia);

        }
    }
}


