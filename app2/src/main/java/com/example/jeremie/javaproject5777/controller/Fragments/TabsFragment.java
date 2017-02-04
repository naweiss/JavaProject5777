package com.example.jeremie.javaproject5777.controller.Fragments;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.jeremie.javaproject5777.controller.Adapters.FilterAdapter;
import com.example.jeremie.javaproject5777.model.datasource.ListDB_manager;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.controller.Adapters.RecyclerViewAdapterActivities;
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
        final int tabCount = 2;
        headerLogo = getView().findViewById(R.id.headerLogo);
        headerLogoContent = (ImageView) getView().findViewById(R.id.headerLogoContent);

        //the MaterialViewPager
        this.materialViewPager = (MaterialViewPager) getView().findViewById(R.id.materialViewPager);
        this.materialViewPager.getToolbar().setVisibility(View.GONE);
        this.materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {

            int oldItemPosition = -1;

            @Override
            public void setPrimaryItem(final ViewGroup container,final int position, Object object) {
                super.setPrimaryItem(container, position, object);
                //only if the page is different
                if(container.getChildAt(position) != null)
                    ListDB_manager.getInstance().setDataSetChangedListener(new ListDB_manager.NotifyDataSetChangedListener() {
                        @Override
                        public void onDataSetChanged() {
                            FilterAdapter adapter = ((FilterAdapter)((RecyclerView)container.getChildAt(position)).getAdapter());
                            adapter.clear();
                            if(adapter instanceof RecyclerViewAdapterActivities)
                                adapter.addAll(ListDB_manager.getInstance().getAllActivity());
                            else
                                adapter.addAll(ListDB_manager.getInstance().getAllBusinesses());
                        }
                    });
                if (oldItemPosition != position) {
                    oldItemPosition = position;

                    //Define  color and  images for the tabs
                    String imageUrl = null;
                    int color = Color.BLACK;
                    Drawable newDrawable = null;

                    switch (position) {
                        case 0:
                            imageUrl = "https://static.pexels.com/photos/164661/pexels-photo-164661.jpeg";
                            color = getResources().getColor(R.color.cyan);
                            newDrawable = getResources().getDrawable(R.drawable.business_logo);
                            break;
                        case 1:
                            imageUrl = "http://radioteleolehaiti.com/wp-content/uploads/2016/10/bg6.jpg";
                            color = getResources().getColor(R.color.cyan);
                            newDrawable = getResources().getDrawable(R.mipmap.activities_logo);
                            break;

                    }

                    //Change images / colors
                    int fadeDuration = 400;
                    materialViewPager.setColor(color, fadeDuration);
                    materialViewPager.setImageUrl(imageUrl, fadeDuration);
                    toggleLogo(newDrawable, color, fadeDuration);
                }
            }

            @Override
            public Fragment getItem(int position) {
                //I created for each tab a RecyclerViewFragment
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = RecyclerViewFragment.newInstance(0);break;

                    case 1:
                        fragment = RecyclerViewFragment.newInstance(1);break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return tabCount;
            }

            //The title to display for each page
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


        //Connects tabs to viewpager
        this.materialViewPager.getPagerTitleStrip().setViewPager(this.materialViewPager.getViewPager());
    }

    private void toggleLogo(final Drawable newLogo, final int newColor, int duration){

        //Animation of disappearance
        final AnimatorSet animatorSetDisappear = new AnimatorSet();
        animatorSetDisappear.setDuration(duration);
        animatorSetDisappear.playTogether(
                ObjectAnimator.ofFloat(headerLogo, "scaleX", 0),
                ObjectAnimator.ofFloat(headerLogo, "scaleY", 0)
        );

        //Animation of appearance
        final AnimatorSet animatorSetAppear = new AnimatorSet();
        animatorSetAppear.setDuration(duration);
        animatorSetAppear.playTogether(
                ObjectAnimator.ofFloat(headerLogo, "scaleX", 1),
                ObjectAnimator.ofFloat(headerLogo, "scaleY", 1)
        );

        //After the disappearance
        animatorSetDisappear.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                //Changes the color of the circle
                ((GradientDrawable) headerLogo.getBackground()).setColor(newColor);

                //Modifies the image contained in the circle
                headerLogoContent.setImageDrawable(newLogo);

                //Starts the appearance animation
                animatorSetAppear.start();
            }
        });

        //Starts the animation of disappearance
        animatorSetDisappear.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }
}
