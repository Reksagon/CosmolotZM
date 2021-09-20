package com.ho.kosmolot.lot.com;

import android.annotation.SuppressLint;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ho.kosmolot.lot.com.databinding.FragmentCosmoZMMainBinding;
import com.unity3d.player.UnityPlayerActivity;


public class CosmoZMMain extends Fragment {

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            int flags = View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

            Activity activity = getActivity();
            if (activity != null
                    && activity.getWindow() != null) {
                activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            }

        }
    };

    private FragmentCosmoZMMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mHidePart2Runnable.run();
        binding = FragmentCosmoZMMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getURL())
                {
                    NavHostFragment.findNavController(CosmoZMMain.this)
                            .navigate(R.id.action_cosmoZMMain_to_cosmoZMView);
                }
                else {
                    Intent i = new Intent(getActivity(), UnityPlayerActivity.class);
                    getActivity().startActivity(i);
                    getActivity().finish();
                }
            }
        });

        binding.bttnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_cosmoZMMain_to_cosmoZMProfile);
            }
        });

        binding.sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Sound")
                        .setMessage("Make your chosie:")
                        .setPositiveButton(Html.fromHtml("<font color='#a82da0'>ON</font>"), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //editor.putString("Sound", "on");
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(Html.fromHtml("<font color='#a82da0'>OFF</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //editor.putString("Sound", "off");
                            }
                        })
                        .setIcon(R.drawable.icon_music)
                        .show();
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true ) {
            @Override
            @MainThread
            public void handleOnBackPressed() {

                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);

            }
        });
    }

    private boolean getURL() {
        if(!CosmoZMConst.getFirebase().getString(new String(Base64.decode(getActivity().getResources().getString(R.string.cosmo_zm), Base64.DEFAULT))).
                equals(new String(Base64.decode(getActivity().getResources().getString(R.string.cosmo_zm), Base64.DEFAULT))))
        {
            CosmoZMView.url = CosmoZMConst.getFirebase().getString(new String(Base64.decode(getActivity().getResources().getString(R.string.cosmo_zm), Base64.DEFAULT)));
            return true;
        }else {
            return false;
        }
    }
}