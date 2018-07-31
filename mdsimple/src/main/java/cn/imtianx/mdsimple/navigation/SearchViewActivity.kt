package cn.imtianx.mdsimple.navigation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import cn.imtianx.mdsimple.R
import kotlinx.android.synthetic.main.activity_search_view.*
import org.jetbrains.anko.hintTextColor
import org.jetbrains.anko.textColor

/**
 * <pre>
 *     @desc: SearchView
 * </pre>
 * @author 奚岩
 * @date 2018/7/31 9:04 PM
 */
class SearchViewActivity : AppCompatActivity() {


    private var searchAutoComplete: SearchView.SearchAutoComplete? = null
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        setSupportActionBar(toolbar)

        // 返回监听：有输入内容时关闭，否则直接finish
        toolbar.setNavigationOnClickListener {

            searchAutoComplete?.let {
                if (it.isShown) {
                    it.setText("")
                    val onCloseClicked = searchView.javaClass.getDeclaredMethod("onCloseClicked")
                    onCloseClicked.isAccessible = true
                    onCloseClicked.invoke(searchView)
                    return@setNavigationOnClickListener
                }
            }

            finish()
        }
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


            // 输入提示文本
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {

            if (it.itemId == R.id.menu_toolbar_share) {
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, SearchViewActivity::class.java))
        }
    }
}