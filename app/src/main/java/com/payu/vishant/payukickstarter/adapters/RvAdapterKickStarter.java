package com.payu.vishant.payukickstarter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.payu.vishant.payukickstarter.R;
import com.payu.vishant.payukickstarter.activities.ActivityKickStartDetail;
import com.payu.vishant.payukickstarter.models.KickStarter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static com.payu.vishant.payukickstarter.utils.Utils.getDayDiff;



public class RvAdapterKickStarter extends RecyclerView.Adapter<RvAdapterKickStarter.RvKickStarterViewHolder> {

    private ArrayList<KickStarter> kickStarterArrayList;
    private final LayoutInflater mInflator;
    private final Context mContext;

    public RvAdapterKickStarter(Context context, ArrayList<KickStarter> kickStarters) {
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        kickStarterArrayList = kickStarters;
        mContext = context;
    }


    @Override
    public RvKickStarterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.row_kick_starter, parent, false);
        RvKickStarterViewHolder holder = new RvKickStarterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RvKickStarterViewHolder holder, int position) {
            holder.bindToView(kickStarterArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return kickStarterArrayList.size();
    }

    public class RvKickStarterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_pledged_amount;
        private TextView tv_backers;
        private TextView tv_number_of_days;
        private View cl_row_kick_starter;

        public RvKickStarterViewHolder(View itemView) {
            super(itemView);
            cl_row_kick_starter = itemView.findViewById(R.id.cl_row_kick_starter);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_pledged_amount = (TextView) itemView.findViewById(R.id.tv_pledged_amount);
            tv_backers = (TextView) itemView.findViewById(R.id.tv_backers);
            tv_number_of_days = (TextView) itemView.findViewById(R.id.tv_number_of_days);

        }

        public void bindToView(final KickStarter kickStarter) {
            tv_title.setText(kickStarter.getTitle());
            tv_title.setSelected(true);
            tv_pledged_amount.setText(String.valueOf(kickStarter.getAmt_pledged()));
            tv_backers.setText(String.valueOf(kickStarter.getNum_backers()));
            tv_number_of_days.setText(String.valueOf(getDayDiff(new Date(System.currentTimeMillis()),kickStarter.getEnd_time())));
            cl_row_kick_starter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startDetailActivity(kickStarter);
                }
            });

        }
    }

    private void startDetailActivity(KickStarter kickStarter) {
        Intent intent = new Intent(mContext, ActivityKickStartDetail.class);
        intent.putExtra("kickStarter",kickStarter);
        mContext.startActivity(intent);
    }

    public void setSearchFilter(ArrayList<KickStarter> newKickStarterList){
        kickStarterArrayList = new ArrayList<>();
        kickStarterArrayList.addAll(newKickStarterList);
        notifyDataSetChanged();

    }


    public void SortAlphabatically(boolean ASC) {
        if (ASC) {
            Collections.sort(kickStarterArrayList, new KickStarter.TitleComaparatorASC());
            notifyDataSetChanged();
        } else {
            Collections.sort(kickStarterArrayList, new KickStarter.TitleComaparatorDSC());
            notifyDataSetChanged();
        }
    }


    public void SortByEndTime(boolean ASC) {

        if (ASC) {
            Collections.sort(kickStarterArrayList, new KickStarter.TimeComparatorASC());
            notifyDataSetChanged();
        } else {
            Collections.sort(kickStarterArrayList, new KickStarter.TimeComparatorDSC());
            notifyDataSetChanged();
        }

    }
}
