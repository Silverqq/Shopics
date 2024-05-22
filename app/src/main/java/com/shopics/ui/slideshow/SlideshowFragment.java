package com.shopics.ui.slideshow;

import static com.shopics.LogoutHelper.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.shopics.LoginActivity;
import com.shopics.LogoutHelper;
import com.shopics.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        logout();
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

    binding = FragmentSlideshowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    private void logout(){
    FirebaseAuth.getInstance().signOut();
    Intent intent = new Intent(getActivity(), LoginActivity.class);
    startActivity(intent);
    getActivity().finish();
}

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}