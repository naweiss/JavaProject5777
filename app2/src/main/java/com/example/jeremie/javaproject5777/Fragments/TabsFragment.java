package com.example.jeremie.javaproject5777.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jeremie.javaproject5777.R;
import com.github.florent37.materialviewpager.MaterialViewPager;


public class TabsFragment extends Fragment {
    MaterialViewPager materialViewPager;
    View headerLogo;
    ImageView headerLogoContent;

    public TabsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //les vues définies dans @layout/header_logo
        final int tabCount = 2;
        headerLogo = getView().findViewById(R.id.headerLogo);
        headerLogoContent = (ImageView) getView().findViewById(R.id.headerLogoContent);

        //le MaterialViewPager
        this.materialViewPager = (MaterialViewPager) getView().findViewById(R.id.materialViewPager);
        this.materialViewPager.getToolbar().setVisibility(View.GONE);
        this.materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {

            int oldItemPosition = -1;

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);

                //seulement si la page est différente
                if (oldItemPosition != position) {
                    oldItemPosition = position;

                    //définir la nouvelle couleur et les nouvelles images
                    String imageUrl = null;
                    int color = Color.BLACK;
                    Drawable newDrawable = null;

                    switch (position) {
                        case 0:
                            imageUrl = "https://static.pexels.com/photos/164661/pexels-photo-164661.jpeg";
                            color = getResources().getColor(R.color.purple);
                            newDrawable = getResources().getDrawable(R.drawable.business_logo);
                            break;
                        case 1:
                            imageUrl = "http://radioteleolehaiti.com/wp-content/uploads/2016/10/bg6.jpg";
                            color = getResources().getColor(R.color.cyan);
                            newDrawable = getResources().getDrawable(R.mipmap.activities_logo);
                            break;

                    }

                    //puis modifier les images/couleurs
                    int fadeDuration = 400;
                    materialViewPager.setColor(color, fadeDuration);
                    materialViewPager.setImageUrl(imageUrl, fadeDuration);
                    toggleLogo(newDrawable, color, fadeDuration);
                }
            }

            @Override
            public Fragment getItem(int position) {
                //je créé pour chaque onglet un RecyclerViewFragment
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = RecyclerViewFragment.newInstance(0);break;
                    //return RecyclerViewFragment.newInstance();
                    case 1:
                        fragment = RecyclerViewFragment.newInstance(1);break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return tabCount;
            }

            //le titre à afficher pour chaque page
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Business";
                    case 1:
                        return "Activities";

                    default:
                        return "Page " + position;
                }
            }
        });

        //permet au viewPager de garder 4 pages en mémoire (à ne pas utiliser sur plus de 4 pages !)
        //this.materialViewPager.getViewPager().setOffscreenPageLimit(tabCount);
        //relie les tabs au viewpager
        this.materialViewPager.getPagerTitleStrip().setViewPager(this.materialViewPager.getViewPager());

    }

    private void toggleLogo(final Drawable newLogo, final int newColor, int duration){

        //animation de disparition
        final AnimatorSet animatorSetDisappear = new AnimatorSet();
        animatorSetDisappear.setDuration(duration);
        animatorSetDisappear.playTogether(
                ObjectAnimator.ofFloat(headerLogo, "scaleX", 0),
                ObjectAnimator.ofFloat(headerLogo, "scaleY", 0)
        );

        //animation d'apparition
        final AnimatorSet animatorSetAppear = new AnimatorSet();
        animatorSetAppear.setDuration(duration);
        animatorSetAppear.playTogether(
                ObjectAnimator.ofFloat(headerLogo, "scaleX", 1),
                ObjectAnimator.ofFloat(headerLogo, "scaleY", 1)
        );

        //après la disparition
        animatorSetDisappear.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                //modifie la couleur du cercle
                ((GradientDrawable) headerLogo.getBackground()).setColor(newColor);

                //modifie l'image contenue dans le cercle
                headerLogoContent.setImageDrawable(newLogo);

                //démarre l'animation d'apparition
                animatorSetAppear.start();
            }
        });

        //démarre l'animation de disparition
        animatorSetDisappear.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }
}
