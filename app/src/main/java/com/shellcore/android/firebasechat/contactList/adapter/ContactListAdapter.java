package com.shellcore.android.firebasechat.contactList.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shellcore.android.firebasechat.R;
import com.shellcore.android.firebasechat.contactList.ui.OnItemClickListener;
import com.shellcore.android.firebasechat.entities.User;
import com.shellcore.android.firebasechat.lib.ImageLoader;
import com.shellcore.android.firebasechat.domain.AvatarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cesar on 27/06/2017.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<User> contacts;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ContactListAdapter(List<User> contacts, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.contacts = contacts;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = contacts.get(position);
        holder.setClickListener(user, clickListener);

        String email = user.getEmail();
        boolean online = user.isOnline();
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        holder.txtUser.setText(email);
        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);

        imageLoader.load(holder.imgUser, AvatarHelper.getAvatarUrl(email));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public int getPositionByUsername(String username) {
        int position = 0;
        for (User user : contacts) {
            if (user.getEmail().equals(username)) {
                break;
            }
            position++;
        }

        return position;
    }

    private boolean alreadyInAdapter(User newUser) {
        boolean alreadyInAdapter = false;
        for (User user : contacts) {
            if (user.getEmail().equals(newUser.getEmail())) {
                alreadyInAdapter = true;
                break;
            }
        }
        return alreadyInAdapter;
    }

    public void add(User user) {
        if (!alreadyInAdapter(user)) {
            contacts.add(user);
            notifyDataSetChanged();
        }
    }

    public void update(User user) {
        int pos = getPositionByUsername(user.getEmail());
        contacts.set(pos, user);
        notifyDataSetChanged();
    }

    public void remove(User user) {
        int pos = getPositionByUsername(user.getEmail());
        contacts.remove(pos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_user)
        CircleImageView imgUser;
        @BindView(R.id.txt_user)
        TextView txtUser;
        @BindView(R.id.txt_status)
        TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setClickListener(final User user, final OnItemClickListener clickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(user);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onItemLong(user);
                    return false;
                }
            });
        }
    }
}
