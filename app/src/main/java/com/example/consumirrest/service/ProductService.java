package com.example.consumirrest.service;

import com.example.consumirrest.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("/api/products")
    public abstract Call<List<Product>> listaProducto();
}
