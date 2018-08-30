# Material Design Notes

[toc]

本文主要记录 `Design` 、 `appcompat` 库中部分控件的使用，使用 **kotlin** 开发，相关库的依赖版本如下：

```
implementation "com.android.support:design:27.1.1"
implementation "com.android.support:appcompat-v7:27.1.1"
```

此外，对于 `CardView`、`palette-v7`、`recyclerview-v7` 等相关的库版本均为 `27.1.1`。相关示例代码：[mdsimple](https://github.com/imtianx/tx-and-learning-repo/tree/master/mdsimple)。

<!-- readmore-->

## 一、顶部导航--ToolBar 

示例代码：[NavigationActivity](https://github.com/imtianx/tx-and-learning-repo/blob/master/mdsimple/src/main/java/cn/imtianx/mdsimple/navigation/NavigationActivity.kt)

### 1.1 添加布局
添加到布局文件中,如下：

```
<android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="?attr/colorPrimary"
    android:theme="@style/AppTheme.ToolBar"
    app:subtitleTextColor="@android:color/white"
    app:contentInsetStartWithNavigation="0dp"
    app:titleTextColor="@android:color/white">
</android.support.v7.widget.Toolbar>
```
> 注意 `contentInsetStartWithNavigation` 可以减小 返回icon 与 title 的间距。

### 1.2、设置主题
设置toolabr 及 app 相应的主题，其中 app 的主题可以指定 parent 为 ``,或者对默认的 主题 添加 如下连个属性：

```
<item name="windowActionBar">false</item>
<item name="windowNoTitle">true</item>
```

这里选择使用前者，代码如下：

```
<style name="AppTheme.NoActionBar" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
 </style>
 
 
<style name="AppTheme.ToolBar" parent="AppTheme.NoActionBar">

    <!--toolbar 图标颜色-->
    <item name="colorControlNormal">@android:color/white</item>
    <!--menu 在 toolbar上的颜色-->
    <item name="actionMenuTextColor">@android:color/white</item>

    <!--toolbar 溢出惨淡默认图标-->
    <item name="android:actionOverflowButtonStyle">@style/OverFlowIcon</item>

    <!--溢出菜单背景颜色-->
    <item name="android:itemBackground">@color/colorAccent</item>
    <!--溢出菜单文字颜色-->
    <item name="android:textColor">@android:color/white</item>
    <!--溢出菜单文字大小-->
    <item name="android:textSize">20sp</item>
    <!--溢出菜单是否显示在toolbar上，默认是true-->
    <item name="overlapAnchor">false</item>
     <!-- 底部导航栏颜色，v21-->
    <item name="android:navigationBarColor">@color/colorPrimary</item>
</style>

<style name="OverFlowIcon" parent="Widget.AppCompat.ActionButton.Overflow">
    <item name="android:src">@drawable/ic_favorite_black_24dp</item>
</style>
```

### 1.3、设置监听
可以直接使用 toolbar 来做标题栏，或者设置给默认的 ActionBar，个人习惯后者，并将其设置在 [BaseActivity](https://github.com/imtianx/tx-and-learning-repo/blob/master/mdsimple/src/main/java/cn/imtianx/mdsimple/base/BaseActivity.kt) 中,主要代码如下：

```
// init 
private fun initToolbar(toolbar: Toolbar?) {
    toolbar?.let {
        setSupportActionBar(it)
        if (isShowBack) {
            supportActionBar?.run {
                // 设置显示返回 按钮
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
        }
        // it.setNavigationOnClickListener { finish() } // 此处无效，需拦截监听
    }
}

// 是否显示返回按钮
protected var isShowBack = true

// 设置监听，之类中课重写该方法拦截返回事件，并返回true
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> {
            false
        }
    }
}
```

> 注意：由于 `setSupportActionBar` 方法中重新 new 了返回的事件，所以如果使用 `setNavigationOnClickListener`方法设置返回监听必须放在其后,但均可以 重写 `onOptionsItemSelected` 拦截监听；

最后记得注册 activity 的时候为其添加 `label`属性设置标题名称。

### 1.4、设置居中标题
主要给toolbar中添加一个 `TexttView` 并设置为居中 `  android:layout_gravity="center"`，可以指定其 样式 为 `Base.TextAppearance.Widget.AppCompat.Toolbar.Title`，$\color{red}{ 代码中设置其标题，并设置 toolbar的标题为 ""，toolbar 的 标题为 ”“，不能指定 label 属性}$。否则，如果改 activity 为启动类，则手机左面不显示 app名称。

### 1.5 标题栏添加菜单

(1)、**创建菜单**
创建一个菜单，添加三个item，如下:

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto">

<item
    android:id="@+id/menu_toolbar_search"
    android:icon="@drawable/ic_search_black_24dp"
    android:title="search"
    app:showAsAction="always"/>

<item
    android:id="@+id/menu_toolbar_add"
    android:icon="@drawable/ic_add_white_24dp"
    android:title="add"
    app:showAsAction="never"/>

<item
    android:id="@+id/menu_toolbar_scan"
    android:icon="@drawable/ic_crop_free_black_24dp"
    android:title="scan"
    app:showAsAction="never"/>
</menu>
```
其中 `showAsAction` 表表示该 menu item 是否显示在标题栏：

- **always** : 总是显示
- **ifRoom** : 如果有空间就显示
- **never** ：不显示

（2）、 **加载菜单及监听**
重写 `onCreateOptionsMenu` 创建菜单：

```
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menu?.let {
        menuInflater.inflate(R.menu.menu_toolbar, it)
    }
    return super.onCreateOptionsMenu(menu)
}
```

然后 重写 `onOptionsItemSelected`方法通过id进行设置相关事件，并 返回 **true**，

> 注：对于菜单的样式设置可参见上面 toolbar 的样式。

（3）、**为菜单添加图标**
按照上面的菜单创建，并不会显示图标，通过查看源码发现课通过如下反射操作设置：

```
override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
    menu?.let {
        if (it::class.java == MenuBuilder::class.java) {
            try {

                // 反射 设置 menu icon 可见
                it::class.java.getDeclaredMethod("setOptionalIconsVisible",
                        java.lang.Boolean.TYPE).apply {
                    isAccessible = true
                    invoke(it, true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    return super.onPrepareOptionsMenu(menu)
}

```

### 1.6 搜索菜单--SearchView

完整代码请查看：[SearchViewActivity](https://github.com/imtianx/tx-and-learning-repo/blob/master/mdsimple/src/main/java/cn/imtianx/mdsimple/navigation/SearchViewActivity.kt)


首先，同上述方式添加菜单，为其设置 `actionViewClass` 属性，值为 `android.support.v7.widget.SearchView`,menu 如下：

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/menu_search"
        android:icon="@drawable/ic_search_black_24dp"
        android:title="search"
        app:actionViewClass="android.support.v7.widget.SearchView"
        app:showAsAction="always"/>
</menu>
```

然后，在 `onCreateOptionsMenu` 方法中加载菜单，并设置相关属性,具体如下：

```
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

```

可以查看相关 SearchView 的 源码，对其进行自定义。若不单独处理返回事件，无论 SearchView 是否有输入内容，页面都会退出。

这里处理为：SearchView 若显示，返回关闭，否则直接关闭页面，如下反射处理方式：

```
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
```
其中 searchAutoComplete 查看上一步代码。


## 二、底部导航--BottomNavigationView

## 三、导航--TabLayout

## 四、底部对话框--BottomSheetDialog

## 五、调色板--Palette

## 六、自定义 Behavior



