package com.example.squatmate.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.squatmate.R;
import com.example.squatmate.SafetyActivity;
import com.example.squatmate.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment implements AdapterView.OnItemClickListener {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private static final String TAG = "ChooserActivity";

    private static final Class<?>[] CLASSES =
            new Class<?>[] {
                SafetyActivity.class
            };

    private static final int[] DESCRIPTION_IDS =
            new int[] {
                R.string.desc_squat_safety
            };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set up ListView and Adapter
        ListView listView = root.findViewById(R.id.learn_options_list_view);

//        MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);
        MyArrayAdapter adapter = new MyArrayAdapter(getContext(), android.R.layout.simple_list_item_2, CLASSES);
        adapter.setDescriptionIds(DESCRIPTION_IDS);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class<?> clicked = CLASSES[position];
        Intent intent = new Intent(getActivity(), clicked);
        startActivity(intent);
    }

    private static class MyArrayAdapter extends ArrayAdapter<Class<?>> {

        private final Context context;
        private final Class<?>[] classes;
        private int[] descriptionIds;

        MyArrayAdapter(Context context, int resource, Class<?>[] objects) {
            super(context, resource, objects);

            this.context = context;
            classes = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (convertView == null) {
                LayoutInflater inflater =
                        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2, null);
            }

            ((TextView) view.findViewById(android.R.id.text1)).setText(classes[position].getSimpleName());
            ((TextView) view.findViewById(android.R.id.text2)).setText(descriptionIds[position]);

            return view;
        }

        void setDescriptionIds(int[] descriptionIds) {
            this.descriptionIds = descriptionIds;
        }
    }
}