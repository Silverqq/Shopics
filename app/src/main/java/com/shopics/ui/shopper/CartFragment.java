package com.shopics.ui.shopper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopics.R;
import com.shopics.ui.catalog.Product;

import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartProductList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cartProductList = Cart.getInstance().getProducts();
        cartAdapter = new CartAdapter(getContext(), cartProductList);
        cartRecyclerView.setAdapter(cartAdapter);

        cartAdapter.setOnItemLongClickListener(product -> {
            Cart.getInstance().removeProduct(product);
            cartProductList.remove(product);
            cartAdapter.notifyDataSetChanged();
            return true;
        });

        return view;
    }
}