package com.faculdade.hamburgueriaz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View; // Adicionado para os cliques
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int quantidadeint = 0;
    private EditText editQuantidade;
    private TextView valorApagar;
    private Spinner spin;
    private RadioButton bacon, queijo, onionRings;
    private Button addqtd, dimqtd, submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 1. Vincular os IDs (Dentro do onCreate)
        spin = findViewById(R.id.spinner_hamburgueres);
        bacon = findViewById(R.id.Bacon);
        queijo = findViewById(R.id.Queijo);
        onionRings = findViewById(R.id.Onionrings);
        addqtd = findViewById(R.id.addqtd);
        dimqtd = findViewById(R.id.dimqtd);
        editQuantidade = findViewById(R.id.qtd);
        valorApagar = findViewById(R.id.valorApagar);
        submit = findViewById(R.id.submit);


        addqtd.setOnClickListener(v -> {
            quantidadeint++;
            atualizartela();
        });

        dimqtd.setOnClickListener(v -> {
            if (quantidadeint > 0) {
                quantidadeint--;
                atualizartela();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeHamburguer = spin.getSelectedItem().toString();
                String temBacon = bacon.isChecked() ? "Sim" : "Não";
                String temQueijo = queijo.isChecked() ? "Sim" : "Não";
                String temOnion = onionRings.isChecked() ? "Sim" : "Não";
                double precoUnitario = 20.00;
                if (bacon.isChecked()) precoUnitario += 2.00;
                if (queijo.isChecked()) precoUnitario += 1.50;
                if (onionRings.isChecked()) precoUnitario += 3.00;
                double precoTotal = quantidadeint * precoUnitario;



                String mensagem = "Pedido: " + nomeHamburguer + "\n" +
                        "Bacon: " + temBacon + "\n" +
                        "Queijo: " + temQueijo + "\n" +
                        "Onion Rings: " + temOnion + "\n" +
                        "Quantidade: " + quantidadeint + "\n" +
                        "Preço final: R$ " + String.format("%.2f", precoTotal);

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Resumo do Pedido");
                alerta.setMessage(mensagem);
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Fecha o alerta
                    }
                });

                alerta.show();
                valorApagar.setText("R$ " + String.format("%.2f", precoTotal));

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void atualizartela() {
        editQuantidade.setText(String.valueOf(quantidadeint));
    }
}