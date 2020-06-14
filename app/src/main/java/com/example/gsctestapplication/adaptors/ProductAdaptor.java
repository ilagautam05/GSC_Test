package com.example.gsctestapplication.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsctestapplication.R;
import com.example.gsctestapplication.models.ProductItems;
import com.example.gsctestapplication.tools.ViewAnimation;

import java.util.ArrayList;
import java.util.List;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {
    private final List<ProductItems> mDataItems;
    private Context ctx;
    //private final View mView;

    public ProductAdaptor(Context context, List<ProductItems> mItems) {
        mDataItems = mItems;
        ctx = context;
    }

    @NonNull
    @Override
    public ProductAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdaptor.ViewHolder holder, int position) {

        ProductItems current = mDataItems.get(position);
        holder.title.setText(current.getProduct_name());

//        holder.title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tick_bold, 0);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mDataItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView title;
        private final ConstraintLayout expand_layout;
        private final AppCompatImageView title_drop_down;
        private final AppCompatImageView sub_category_dropdown;
        private final LinearLayout sub_category_expand;
        private final Context mContext;
        private final AppCompatTextView sub_category;
        private final ConstraintLayout expand_sub_product_layout;
        private final AppCompatTextView sub_category_title1, sub_category_title2, sub_category_title3, sub_category_title4, sub_category_title5;
        private final AppCompatImageView sub_product_dropdown;
        private final AppCompatTextView sub_product;
        private final LinearLayout sub_product_expand;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();

            title = (AppCompatTextView) itemView.findViewById(R.id.product_title);
            title_drop_down = (AppCompatImageView) itemView.findViewById(R.id.product_title_dropdown);
            sub_category_dropdown = (AppCompatImageView) itemView.findViewById(R.id.sub_category_dropdown);
            sub_product_dropdown = (AppCompatImageView) itemView.findViewById(R.id.sub_product_dropdown);
            expand_layout = (ConstraintLayout) itemView.findViewById(R.id.expand_layout);
            expand_sub_product_layout = (ConstraintLayout) itemView.findViewById(R.id.expand_sub_product_layout);
            sub_category = (AppCompatTextView) itemView.findViewById(R.id.sub_category);
            sub_category_expand = (LinearLayout) itemView.findViewById(R.id.sub_category_expand);

            sub_product = (AppCompatTextView) itemView.findViewById(R.id.sub_product);
            sub_product_expand = (LinearLayout) itemView.findViewById(R.id.sub_product_expand);

            sub_category_title1 = (AppCompatTextView) itemView.findViewById(R.id.sub_category_title1);
            sub_category_title2 = (AppCompatTextView) itemView.findViewById(R.id.sub_category_title2);
            sub_category_title3 = (AppCompatTextView) itemView.findViewById(R.id.sub_category_title3);
            sub_category_title4 = (AppCompatTextView) itemView.findViewById(R.id.sub_category_title4);
            sub_category_title5 = (AppCompatTextView) itemView.findViewById(R.id.sub_category_title5);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleSection(title_drop_down, expand_layout);
                }
            });

            sub_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleSubcategory(sub_category_dropdown);
                }
            });

            sub_category_title1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleSection(sub_product_dropdown, expand_sub_product_layout);
                }
            });

            sub_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleSubProduct(sub_product_dropdown);
                }
            });

        }

        private void toggleSection(View title_drop_down, View expand_layout) {
            Boolean show = toggleView(title_drop_down);
            if (show) {
                ViewAnimation.showIn(expand_layout);
            } else {
                ViewAnimation.showOut(expand_layout);
            }
        }

        private Boolean toggleView(View title_drop_down) {
            if(title_drop_down.getVisibility() == View.GONE){
                title_drop_down.setVisibility(View.VISIBLE);
                return true;
            } else {
                title_drop_down.setVisibility(View.GONE);
                return false;
            }


        }

        private void toggleSubcategory(View view) {
            boolean show = toggleArrow(view);
            if (show) {
                ViewAnimation.expand(sub_category_expand);
            } else {
                ViewAnimation.collapse(sub_category_expand);
            }
        }

        private void toggleSubProduct(View view) {
            boolean show = toggleArrow(view);
            if (show) {
                ViewAnimation.expand(sub_product_expand);
            } else {
                ViewAnimation.collapse(sub_product_expand);
            }
        }

        public boolean toggleArrow(View view) {
            if (view.getRotation() == 0) {
                view.animate().setDuration(200).rotation(180);
                return true;
            } else {
                view.animate().setDuration(200).rotation(0);
                return false;
            }
        }

    }
}
