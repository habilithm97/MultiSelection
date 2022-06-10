package com.example.multiselection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;
    static ArrayList<Model> items;

    public ItemAdapter(Context context, ArrayList<Model> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Model item = items.get(position);
        holder.tv.setText(item.getStr());

        if(item.isSelected()) { // false
            holder.itemView.setBackgroundColor(Color.parseColor("#FF6200EE")); // 진보라
        } else { // true
            holder.itemView.setBackgroundColor(Color.parseColor("#FFBB86FC")); // 연보라
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            tv = itemView.findViewById(R.id.tv);

            // 레이아웃을 클릭할 때마다 아이템의 선택 상태가 바뀌는 메소드 호출
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMultiSelection(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();

                    // 대화상자 생성
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("삭제하기");
                    builder.setMessage("선택한 아이템을 삭제하시겠습니까? ");
                    builder.setIcon(R.drawable.delete);
                    builder.setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete(position);
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                    return true;
                }
            });
        }
    }

    public void delete(int position) {
        items.remove(position);
        notifyItemRemoved(position); // 특정 아이템 한 개를 삭제
        // 여러 개의 아이템을 변경
        notifyItemRangeChanged(position, items.size()); // (변경된 첫 번째 아이템의 위치, 변경된 아이템의 갯수)
    }

    private void setMultiSelection(int adapterPosition) {
        if(items.get(adapterPosition).isSelected()) { // false
            items.get(adapterPosition).setSelected(false);
        } else { // true
            items.get(adapterPosition).setSelected(true);
        }
        notifyDataSetChanged();
    }
}
