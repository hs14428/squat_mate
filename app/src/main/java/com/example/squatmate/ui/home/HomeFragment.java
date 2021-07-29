package com.example.squatmate.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.squatmate.R;
import com.example.squatmate.databinding.FragmentHomeBinding;

import java.io.File;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textHome;
        textView.setText("Press the camera to record your squats");
        textView = binding.textHome2;
        textView.setText("Then go to \"Load your squats\" and select the video to process");
        textView = binding.textHome3;
        textView.setText("Once the video has processed, read through the feedback");
        textView = binding.textHome4;
        textView.setText("Finally, visit the \"Learn about squats\" section for further guidance");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}