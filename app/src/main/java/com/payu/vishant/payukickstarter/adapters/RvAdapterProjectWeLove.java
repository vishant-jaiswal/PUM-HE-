package com.payu.vishant.payukickstarter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.payu.vishant.payukickstarter.R;
import com.payu.vishant.payukickstarter.models.KickStarter;

import java.util.ArrayList;


public class RvAdapterProjectWeLove extends RecyclerView.Adapter<RvAdapterProjectWeLove.RvProjectWeLoveViewHolder> {

    private ArrayList<KickStarter> kickStarterArrayList;
    private final LayoutInflater mInflator;
    private final Context mContext;

    public RvAdapterProjectWeLove(Context context, ArrayList<KickStarter> kickStarters) {
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        kickStarterArrayList = kickStarters;
        mContext = context;
    }


    @Override
    public RvProjectWeLoveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.row_project_we_love, parent, false);
        RvProjectWeLoveViewHolder holder = new RvProjectWeLoveViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RvProjectWeLoveViewHolder holder, int position) {
        holder.bindToView(kickStarterArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return kickStarterArrayList.size();
    }

    public class RvProjectWeLoveViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_pledged_amount;
        private TextView tv_backers;
        private TextView tv_percentage_funding;
        /*private View cl_row_kick_starter;*/

        public RvProjectWeLoveViewHolder(View itemView) {
            super(itemView);
            //cl_row_kick_starter = itemView.findViewById(R.id.cl_row_kick_starter);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_pledged_amount = (TextView) itemView.findViewById(R.id.tv_pledged_amount);
            tv_backers = (TextView) itemView.findViewById(R.id.tv_backers);
            tv_percentage_funding = (TextView) itemView.findViewById(R.id.tv_percentage_funding);

        }

        public void bindToView(final KickStarter kickStarter) {
            tv_title.setText(kickStarter.getTitle());
            tv_title.setSelected(true);
            tv_pledged_amount.setText(String.valueOf(kickStarter.getAmt_pledged()));
            tv_backers.setText(String.valueOf(kickStarter.getNum_backers()));
            tv_percentage_funding.setText(String.valueOf(kickStarter.getPercentage_funded()));
            /*cl_row_kick_starter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startDetailActivity(kickStarter);
                }
            });*/

        }
    }
}
