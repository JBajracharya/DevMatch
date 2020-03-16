package com.finalProject.devmatch;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalProject.devmatch.ProjectListFragment.OnListFragmentInteractionListener;
import com.finalProject.devmatch.dummy.DummyContent.DummyItem;
import com.finalProject.devmatch.models.Projects;

import org.w3c.dom.Text;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyProjectListRecyclerViewAdapter extends RecyclerView.Adapter<MyProjectListRecyclerViewAdapter.ViewHolder> {
    static String TAG = "RecyclerView";
    private final List<Projects> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyProjectListRecyclerViewAdapter(List<Projects> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_project_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(position > mValues.size() - 1){
            return;
        }
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mDescriptionView.setText(mValues.get(position).getDescription());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicked on the view");

                Context context = v.getContext();
                Intent goToProjectDetailPage = new Intent(context, ProjectDetail.class);
                goToProjectDetailPage.putExtra("mName",mValues.get(position).getName());
                System.out.println("SHANE " + holder.mItem.getId());
                context.startActivity(goToProjectDetailPage);

                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mDescriptionView;
        public Projects mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mDescriptionView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }

}