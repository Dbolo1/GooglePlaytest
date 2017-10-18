package com.bolo1.googleplay.ui.fragment;

import android.app.Fragment;

import java.util.HashMap;

/**
 * Created by 菠萝 on 2017/10/16.
 */

public class FragmentFactory {
    public static  HashMap<Integer,BaseFragment> fragmentHashMap=new HashMap<Integer, BaseFragment>();
    public static BaseFragment onCreateFragment(int pos) {
        //先从集合中取如果没有才去new对象，提高性能
        BaseFragment fragment = fragmentHashMap.get(pos);
        if(fragment==null){
            switch (pos) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjuectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;
                default:
                    break;
            }
            fragmentHashMap.put(pos,fragment);
        }
            return fragment;
    }


}
