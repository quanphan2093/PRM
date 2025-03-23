package com.example.myapplication.Project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Project.Models.Cart;
import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private List<Product> list;
    private Context context;

    public ProductAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.product_itemview, parent,false);
            viewHolder = new ProductViewHolder();
            viewHolder.img=convertView.findViewById(R.id.image_view);
            viewHolder.ProdName=convertView.findViewById(R.id.tv_product_name);
            viewHolder.CateName=convertView.findViewById(R.id.tv_category);
            viewHolder.Price=convertView.findViewById(R.id.tv_price);
            viewHolder.btnDetail= convertView.findViewById(R.id.btnDetail);
            viewHolder.btnAdd= convertView.findViewById(R.id.btnAddToCart);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ProductViewHolder) convertView.getTag();
        }
        Product p = list.get(position);
        if(p != null){
            viewHolder.ProdName.setText(p.getProdName());
            viewHolder.CateName.setText(p.getCateName());
            viewHolder.Price.setText(String.valueOf(p.getPrice()));
            Picasso.get()
                    .load(p.getImage()) // URL ảnh
                    .placeholder(R.drawable.cart) // Ảnh tạm
                    .error(R.drawable.border) // Nếu lỗi
                    .into(viewHolder.img);
        }
        //---event---
        viewHolder.btnDetail.setOnClickListener(v ->{
            Product product = list.get(position);
            Intent intent = new Intent(context,ProductDetailActivity.class);
            intent.putExtra("PRODUCT",product);
            context.startActivity(intent);
        });
        viewHolder.btnAdd.setOnClickListener(v -> {
            Cart cart = Cart.getInstance();
            Product product = list.get(position);
            boolean productExists = false;
            for (Product pro : cart.getCartItems()) {
                if (pro.getProdId() == product.getProdId()) { // Kiểm tra sản phẩm đã có trong giỏ chưa
                    pro.setQuantity(pro.getQuantity() + 1); // Tăng số lượng
                    pro.setPrice(pro.getQuantity() * pro.getUnitPrice()); // Cập nhật giá
                    productExists = true;
                    break;
                }
            }
            if (!productExists) { // Nếu chưa có trong giỏ, thêm mới
                Product newProduct = new Product();
                newProduct.setProdId(product.getProdId());  // Set ID
                newProduct.setProdName(product.getProdName()); // Set tên
                newProduct.setImage(product.getImage()); // Set ảnh
                newProduct.setUnitPrice(product.getPrice()); // Đơn giá ban đầu
                newProduct.setQuantity(1);
                newProduct.setPrice(newProduct.getQuantity() * newProduct.getUnitPrice()); // Tính tổng giá

                cart.addProductToCart(newProduct);
            }

            Intent intent = new Intent(context, CartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // Không mở activity mới nếu đã tồn tại
            context.startActivity(intent);
        });
        //---edn event...
        return convertView;
    }

    public void updateData(List<Product> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    static class ProductViewHolder{
        ImageView img;
        TextView ProdName, CateName, Price;
        Button btnDetail, btnAdd;
    }
}
