package com.shellcore.android.firebasechat.chat.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shellcore.android.firebasechat.R;
import com.shellcore.android.firebasechat.entities.ChatMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 28/06/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<ChatMessage> messages;

    public ChatAdapter(Context context, List<ChatMessage> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_chat, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage chatMessage = messages.get(position);

        String msg = chatMessage.getMessage();
        holder.txtMessage.setText(msg);

        int color = fetchColor(R.attr.colorPrimary);
        int gravity = Gravity.LEFT;

        if(!chatMessage.isSentByMe()) {
            gravity = Gravity.RIGHT;
            color = fetchColor(R.attr.colorAccent);
        }

        holder.txtMessage.setBackgroundColor(color);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
        params.gravity = gravity;
        holder.txtMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void add (ChatMessage message) {
        if (!alreadyInAdapter(message)) {
            messages.add(message);
            notifyDataSetChanged();
        }
    }

    private int fetchColor(int color) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] {color});

        int returnColor=  a.getColor(0, 0);
        a.recycle();
        return returnColor;
    }

    private boolean alreadyInAdapter(ChatMessage newMessage) {
        boolean alreadyInAdapter = false;
        for(ChatMessage message : messages) {
            if (message.getMessage().equals(newMessage.getMessage())
                    && message.getSender().equals(newMessage.getSender())) {
                alreadyInAdapter = true;
                break;
            }
        }
        return alreadyInAdapter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_message)
        TextView txtMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
