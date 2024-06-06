package com.shopics.ui.catalog;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shopics.R;
import com.shopics.ui.shopper.Cart;

import java.util.ArrayList;
import java.util.List;

public class CatalogFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private DatabaseReference databaseReference;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerView = view.findViewById(R.id.listofproducts);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(productAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    productList.add(product);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error handling
            }
        });

        // Set the item click listener
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                showProductDialog(product);
            }
        });


        return view;
    }

    private void showProductDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.product_fragment, null);

        // Customize the dialog view with product details
        TextView productName = dialogView.findViewById(R.id.name_txt);
        TextView productPrice = dialogView.findViewById(R.id.price_txt);
        TextView productDescriptiom = dialogView.findViewById(R.id.description_txt);
        ImageView productPicture = dialogView.findViewById(R.id.imageView3);
        Button addButton = dialogView.findViewById(R.id.addButton);

        productName.setText(product.getName());
        productDescriptiom.setText(product.getDescription());
        productPrice.setText(product.getPrice());

        switch (product.getName()) {
            case "Apple":
                productPicture.setImageResource(R.drawable.yabloko_foreground);
                break;
            case "Blackberry":
                productPicture.setImageResource(R.drawable.blackberry);
                break;
            case "banana":
                productPicture.setImageResource(R.drawable.banana);
                break;
            case "Капуста":
                productPicture.setImageResource(R.drawable.kapusta);
                break;
            case "cucumber":
                productPicture.setImageResource(R.drawable.cucumber);
                break;
            case "watermelon":
                productPicture.setImageResource(R.drawable.watermelon);
                break;
            default:
                // Set a default image or handle the case where the product name doesn't match any case
                break;
        }
        // Load the image using Glide
        /*Glide.with(this)
                .load(Uri.parse("gs://shopics-b1f2d.appspot.com/yabloko_foreground.png"))
                .apply(new RequestOptions().override(200, 200))
                .into(productPicture);*/

        // Add more views and set product details as needed
        addButton.setOnClickListener(v -> {
            Cart.getInstance().addProduct(product);
            Toast.makeText(getContext(), product.getName() + " добавлен в корзину", Toast.LENGTH_SHORT).show();
        });

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}