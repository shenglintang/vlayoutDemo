package recyclerview.lin.com.vlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.List;

/**
 * Created by lin on 2017/6/22.
 */

public class MyAdapter extends VirtualLayoutAdapter{
private Context mContext;

    public MyAdapter(@NonNull VirtualLayoutManager layoutManager,Context context) {
        super(layoutManager);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new TextView(mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 300);
        holder.itemView.setLayoutParams(layoutParams);

        ((TextView) holder.itemView).setText(Integer.toString(position));

        if (position == 7) {
            layoutParams.height = 60;
            layoutParams.width = 60;
        } else if (position > 35) {
            layoutParams.height = 200 + (position - 30) * 100;
        }

        if (position > 35) {
            holder.itemView.setBackgroundColor(0x66cc0000 + (position - 30) * 128);
        } else if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa00ff00);
        } else {
            holder.itemView.setBackgroundColor(0xccff00ff);
        }
    }

    @Override
    public int getItemCount() {
        List<LayoutHelper> helpers = getLayoutHelpers();
        if (helpers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0, size = helpers.size(); i < size; i++) {
            count += helpers.get(i).getItemCount();
        }
        return count;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
