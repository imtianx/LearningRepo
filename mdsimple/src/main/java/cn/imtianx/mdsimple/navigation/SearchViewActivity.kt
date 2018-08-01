package cn.imtianx.mdsimple.navigation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.hintTextColor
import org.jetbrains.anko.textColor

/**
 * <pre>
 *     @desc: SearchView
 * </pre>
 * @author 奚岩
 * @date 2018/7/31 9:04 PM
 */
class SearchViewActivity : BaseActivity() {


    private var searchAutoComplete: SearchView.SearchAutoComplete? = null
    private lateinit var searchView: SearchView


    override fun getContentLayoutId(): Int {
        return R.layout.activity_search_view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // back
                onBackEvent()
                true
            }
            R.id.menu_toolbar_share -> {
                // menu item
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    // 返回，searchView若显示则关闭，否则退出页面
    private fun onBackEvent() {
        searchAutoComplete?.let {
            if (it.isShown) {
                it.setText("")
                val onCloseClicked = searchView::class.java
                        .getDeclaredMethod("onCloseClicked")
                onCloseClicked.isAccessible = true
                onCloseClicked.invoke(searchView)
                return
            }
        }
        finish()
    }

    override fun onBackPressed() {
        onBackEvent()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {

            menuInflater.inflate(R.menu.menu_toolbar_search, it)

            val menuItem = it.findItem(R.id.menu_search)

            searchView = menuItem.actionView as SearchView

            // 设置有输入内容时显示关闭，没有时自动隐藏，必须设置在  setIconified();方法 之前，其内部设置默认：setIconified(false);
            searchView.onActionViewExpanded()

            // 打开就显示
            searchView.isIconified = true
            // 打开显示且不能被隐藏
//            searchView.setIconifiedByDefault(true)


            // 设置提交按钮可见
            searchView.isSubmitButtonEnabled = true

            // SearchView 布局：abc_search_view.xml,可以重定义控件

            // 重设提交按钮 图片
            val submitIcon = searchView.findViewById(R.id.search_go_btn) as ImageView
            submitIcon.setImageResource(R.drawable.ic_done_black_24dp)
            submitIcon.visibility = View.VISIBLE

            // 设置输入提示1，会显示搜索 图标
//            searchView.queryHint = "请输入商品名称"

            // 设置输入提示2，可以设置提示文本的样式
            searchAutoComplete = searchView.findViewById(R.id.search_src_text)
            searchAutoComplete!!.apply {
                hint = "请输入商品名称"
                hintTextColor = Color.GRAY
                textColor = Color.WHITE
            }



            searchView.setOnSearchClickListener {
                toolbar.title = ""
                Toast.makeText(this, "显示搜索", Toast.LENGTH_SHORT).show()
            }

            searchView.setOnCloseListener {
                toolbar.title = "SearchView"
                Toast.makeText(this@SearchViewActivity, "isIconified:${searchView.isIconified}", Toast.LENGTH_SHORT).show()
                return@setOnCloseListener false
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(this@SearchViewActivity, "提交", Toast.LENGTH_SHORT).show()

                    // 收起键盘，关闭 searchView
                    searchView.clearFocus()
                    searchView.onActionViewCollapsed()
                    toolbar.title = "SearchView"
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Toast.makeText(this@SearchViewActivity, "搜索内容变化", Toast.LENGTH_SHORT).show()
                    return false
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, SearchViewActivity::class.java))
        }
    }
}