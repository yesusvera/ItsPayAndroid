package itspay.br.com.util.CustomViewPage.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import itspay.br.com.activity.CadastroLoginActivity;

/**
 * Created by juniorbraga on 24/02/17.
 */
public class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

    private Context mContext;
    private List<Fragment> mFragmentsList;

    public CustomFragmentPagerAdapter(FragmentManager fm, Context activity, List<Fragment> fragmentsList)
    {
        super(fm);
        mContext = activity;
        mFragmentsList = fragmentsList;
    }


    @Override
    public int getCount()
    {
        return mFragmentsList.size();
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = mFragmentsList.get(position);
        return Fragment.instantiate(mContext, fragment.getClass().getName(), fragment.getArguments());
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
        int i = position;
    }

    public void onPageSelected(int position)
    {
        int i = position;
    }

    public void onPageScrollStateChanged(int state)
    {
        int i = state;
    }

}
