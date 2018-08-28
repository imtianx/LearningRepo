package cn.imtianx.mdsimple.status

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/27 5:35 PM
 */
class ViewPagerFragmentAdapter @JvmOverloads constructor(fm: FragmentManager,
                                                         var fragments: List<Fragment>,
                                                         var titles: List<String>? = null)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        titles?.let {
            return it.get(position)
        }
        return super.getPageTitle(position)
    }

}