package com.example.vmac.WatBot;

/**
 * Created by VMac on 17/11/16.
 * last modified: 23/03/2019 by J.Cistiakovas - modified getItemViewType
 */

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


  private int SELF = 100;
  private String myId;
  private ArrayList<Message> messageArrayList;


  public ChatAdapter(ArrayList<Message> messageArrayList, String myId) {
    this.messageArrayList = messageArrayList;
    this.myId = myId;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView;

    // view type is to identify where to render the chat message
    // left or right
    if (viewType == SELF) {
      // self message
      itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.chat_item_self, parent, false);
    } else {
      // WatBot message
      itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.chat_item_watson, parent, false);
    }


    return new ViewHolder(itemView);
  }

  //gets view type of a specific item

  /**
   * last modified: 23/03/2019 by J.Cistiakovas - viewType recognises the owner of the message
   * */
  @Override
  public int getItemViewType(int position) {
    Message message = messageArrayList.get(position);
    if (TextUtils.equals(message.getSender(), myId)) {
      return SELF;
    }

    return position;
  }

  @Override
  public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
    Message message = messageArrayList.get(position);
    message.setMessage(message.getMessage());
    ((ViewHolder) holder).message.setText(message.getMessage());
  }

  @Override
  public int getItemCount() {
    return messageArrayList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView message;

    public ViewHolder(View view) {
      super(view);
      message = (TextView) itemView.findViewById(R.id.message);

      //TODO: Uncomment this if you want to use a custom Font
            /*String customFont = "Montserrat-Regular.ttf";
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), customFont);
            message.setTypeface(typeface);*/

    }
  }


}