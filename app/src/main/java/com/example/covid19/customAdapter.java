package com.example.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends ArrayAdapter<countryModel> {

    private  Context context;
    private List<countryModel> countryModelsList;
    private List<countryModel> countryModelsListFiltered;

    public customAdapter(@NonNull Context context, List<countryModel> countryModelsList) {
        super(context, R.layout.custom_list, countryModelsList);
        this.context = context;
        this.countryModelsList = countryModelsList;
        this.countryModelsListFiltered = countryModelsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, null,true);
        TextView countryName = view.findViewById(R.id.countryName);
        ImageView imageView = view.findViewById(R.id.imgFlag);

        countryName.setText(countryModelsListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelsListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }
    public int getCount(){
        return countryModelsListFiltered.size();
    }
    public countryModel getItem(int position){
        return countryModelsListFiltered.get(position);
    }
    public  long getItemId(int position){
        return  position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length()== 0){
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;
                }
                else {
                    List<countryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(countryModel itemsModel :countryModelsList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModelsListFiltered = (List<countryModel>) results.values;
                AffectedCountries.countryModelList = (List<countryModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
