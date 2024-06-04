package com.shopics.ui.shopper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopics.R;
import com.shopics.ui.catalog.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List<Product> cartProductList;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener {
        boolean onItemLongClick(Product product);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    public CartAdapter(Context context, List<Product> cartProductList) {
        this.context = context;
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = cartProductList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(product.getPrice());

        holder.itemView.setOnLongClickListener(v -> {
            if (onItemLongClickListener != null) {
                return onItemLongClickListener.onItemLongClick(product);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.cartItemName);

            priceTextView = itemView.findViewById(R.id.cartItemPrice);
        }
    }
}