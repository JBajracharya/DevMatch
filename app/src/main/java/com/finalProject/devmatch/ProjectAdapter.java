//package com.finalProject.devmatch;
//
//import com.finalProject.devmatch.models.Projects;
//
/////https://www.codeproject.com/Tips/894233/Using-Spinner-Control-for-Filtering-ListView-Andro
//
//public class ProjectAdapter extends ArrayAdapter<Projects> {
//    {
//
//        private Context context;
//        private List<City> cityList;
//
//        public CityAdapter(Context context, int textViewResourceId,
//                           List<City> values) {
//            super(context, textViewResourceId, values);
//            this.context = context;
//            this.cityList = values;
//        }
//
//        public int getCount(){
//            return cityList.size();
//        }
//
//        public City getItem(int position){
//            return cityList.get(position);
//        }
//
//        public long getItemId(int position){
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            TextView view = new TextView(context);
//            view.setTextColor(Color.BLACK);
//            view.setGravity(Gravity.CENTER);
//            view.setText(cityList.get(position).getCity());
//
//            return view;
//        }
//
//        //View of Spinner on dropdown Popping
//
//        @Override
//        public View getDropDownView(int position, View convertView,
//                                    ViewGroup parent) {
//            TextView view = new TextView(context);
//            view.setTextColor(Color.BLACK);
//            view.setText(cityList.get(position).getCity());
//            view.setHeight(60);
//
//            return view;
//        }
//    }
//}
