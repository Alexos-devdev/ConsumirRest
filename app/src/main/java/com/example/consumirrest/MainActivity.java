package com.example.consumirrest;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.consumirrest.entity.Product;
import com.example.consumirrest.service.ProductService;
import com.example.consumirrest.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Al crear el spinner se crean tres objectos
    Spinner   spnProductos;
    ArrayAdapter<String> adaptadorProductos;
    ArrayList<String> listaProducto = new ArrayList<String>();

    Button btnFiltrar;
    TextView txtResultado;

    //Servicio de usuario de retrofit
    ProductService productService;

    //Aqui estaran toda la data de usuarios
    List<Product> lstSalida ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adaptadorProductos = new ArrayAdapter<String>(
                this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listaProducto);
        spnProductos = findViewById(R.id.spnProductos);
        spnProductos.setAdapter(adaptadorProductos);

        btnFiltrar = findViewById(R.id.btnFiltrar);
        txtResultado = findViewById(R.id.txtResultado);

        productService = ConnectionRest.getConnecion().create(ProductService.class);

        cargaProducto();

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idProducto = (int) spnProductos.getSelectedItemId();
                Product objProduct = lstSalida.get(idProducto);

                String salida = "<h2><b>Información del Producto:</b></h2>";
                salida += "<b>Precio:</b> $" + objProduct.getPrice() + "<br>";
                salida += "<b>Descripción:</b> " + objProduct.getDescription() + "<br>";
                salida += "<b>ID de la Categoría:</b> " + objProduct.getCategory().getId() + "<br>";
                salida += "<b>Nombre de la Categoría:</b> " + objProduct.getCategory().getName() + "<br>";
                salida += "<b>Tipo de la Categoría:</b> " + objProduct.getCategory().getTypeImg() + "<br>";

                txtResultado.setText(Html.fromHtml(salida, Html.FROM_HTML_MODE_LEGACY));
            }
        });



    }

    void cargaProducto(){
        Call<List<Product>> call =  productService.listaProducto();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                // La respuesta es exitosa del servicio Rest

                if (response.isSuccessful()) {
                    lstSalida = response.body();
                    for (Product obj : lstSalida) {
                        // Concatenar el id y el title
                        String combined = "ID: " + obj.getId() + " - Nombre del producto: " + obj.getTitle();
                        listaProducto.add(combined);
                    }
                    adaptadorProductos.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                //No existe respuesta del servicio Rest
            }
        });
    }
}