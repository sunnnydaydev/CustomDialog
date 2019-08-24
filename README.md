>首先瞅瞅效果图如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190824142138308.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4MzUwNjM1,size_16,color_FFFFFF,t_70)
### 要点

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190824144917391.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4MzUwNjM1,size_16,color_FFFFFF,t_70)

### 一、背景介绍
>做海外上架的小包碰到个需求、关于对话框的，上文已给出效果图。其实也是一个简单的对话框。只不过有点小要求：
>>1、有圆角
>>2、背景图可以动态替换
>>3、文字可以动态替换
>>4、按钮圆角、文字动态替换、背景色动态替换。
>
>要求贼简单、想着每个小包都要使用这个对话框,于是想法来了：前段时间自己划水时刚学过kt、自定义view。又想到可不可以像使用第三方库那样自己也搞一个aar？以后不就搬砖快乐了吗。作为一个应届生要多动手、多思考、干的不错！<font color = red>这里就简单的搞一下。重在总结收获嘛。。。
### 二、自定义对话框步骤详解
###### 1、思路
>了解过自定义view的都知道，自定义全新的view有时是比较复杂的，好多都需要我们处理，比如、wrap_content的处理、view添加padding等等。还好我们自定义对话框相对来说比较简单点。在原有的view上扩展下即可。

（1）创建类继承dialog

（2）加载布局

（3）添加样式

（4）暴露接口



###### 2、简单实现
（1）创建类继承dialog
>这部没啥好说的,三下五除二搞出来。。。

```java
/**
 * Created by sunnyDay on 2019/8/22 19:33
 */
class SubscriptionDialog : Dialog {
}
```

（2）加载布局
>加载布局其实就是 xml布局文件转化为view，然后显示。
>常规有两种方式：
>1、View的inflate方法
>2、 LayoutInflater的inflate方法（自定义dialog时有坑）
>其实dialog类中提供了setContentView方法也是布局转化为view（自定义dialog时有坑）
>

（3）添加样式
>style 就和activity的主题类似。美化dialog。。。

```java

    <!--自定义对话框的样式-->
    <style name="SubscriptionDialog" parent="@style/AlertDialog.AppCompat">
        <!--边框-->
        <item name="android:windowFrame">@null</item>
        <!--是否浮现在activity之上-->
        <item name="android:windowIsFloating">true</item>
        <!--半透明-->
        <item name="android:windowIsTranslucent">false</item>
        <!--无标题-->
        <item name="android:windowNoTitle">true</item>
        <!--提示框背景-->
        <item name="android:windowBackground">@android:color/transparent</item>
    </style>
```

（4）暴露接口
>提供一些方法即可，比如对话框按钮事件、标题内容修改。。。


（5）源码
```java
/**
 * Created by sunnyDay on 2019/8/22 19:33
 */
class SubscriptionDialog : Dialog {

    private var mOnContinueClickListener: OnContinueClickListener? = null

    /**
     *  use default dialog style
     * */
    constructor(context: Context) : super(context, R.style.SubscriptionDialog) {
        setContentView(R.layout.dialog_simple)
    }

    /**
     *  user give a custom dialog style
     * */
    constructor(context: Context, dialogStyle: Int) : super(context, dialogStyle) {
        setContentView(R.layout.dialog_simple)
    }

    /**
     * set image of dialog
     * */
    fun setImage(img: Int): SubscriptionDialog {
//        when (img) {
//            img is Int -> iv_img.setImageResource(img as Int)
//            img is Drawable -> iv_img.setImageDrawable(img as Drawable)
//            img is Bitmap -> iv_img.setImageBitmap(img as Bitmap)
//        }
        iv_img.setImageResource(img)
        return this
    }

    /**
     * set the content of dialog
     * */
    fun setContentText(content: String): SubscriptionDialog {
        tv_content.text = content
        return this
    }

    /**
     * set the content of dialog
     * */
    fun setContentTextColor(contextTextColor: Int): SubscriptionDialog {
        tv_content.setTextColor(contextTextColor)
        return this
    }

    /**
     * set text of button
     * */
    fun setButtonText(btnText: CharSequence): SubscriptionDialog {
        bt_button.text = btnText
        return this
    }

    /**
     * set color of button text
     * */
    fun setButtonTextColor(textColor: Int): SubscriptionDialog {
        bt_button.setTextColor(textColor)
        return this
    }

    /**
     * set color of button bg
     * */
    fun setButtonBackgroundColor(btnBgColor: Int): SubscriptionDialog {
        bt_button.setBackgroundColor(btnBgColor)
        return this
    }

    /**
     * onClickListener of dialog
     * */
    fun setOnContinueClickListener(onContinueClickListener: OnContinueClickListener?): SubscriptionDialog {
        mOnContinueClickListener = onContinueClickListener
        onButtonClick()
        return this
    }

    /**
     * show dialog
     * */
    fun showDialog() {
        this.show()
    }

    /**
     * close dialog
     * */
    fun dismissDialog() {
        this.dismiss()
    }

    private fun onButtonClick() {
        bt_button.setOnClickListener {
            mOnContinueClickListener?.onClick()
                ?: throw Exception("onContinueClickListener is null，please give a non-value.")
        }
    }

    interface OnContinueClickListener {
        fun onClick()
    }
}
```

### 三、打包为aar
###### 1、了解
>1、其实我们的工程是分为：安卓工程、安卓library、java library。
>2、为啥要打包aar？和jar包区别：
>>jar包内步只含有class文件和清单文件
>>aar 文件包含所有资源文件，包括class及res资源文件。方便我们使用res
>文件

###### 2、打包
（1）安卓library 直接如下操作

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019082415174475.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4MzUwNjM1,size_16,color_FFFFFF,t_70)

（2）安卓 project
>需要变为 安卓library 在按照上述打包

变更步骤
>去掉无用代码：
1、manifest 只保留application 节点及其属性。
2、app.build 去掉如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190824152929557.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4MzUwNjM1,size_16,color_FFFFFF,t_70)


接下来打包即可

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019082415334535.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4MzUwNjM1,size_16,color_FFFFFF,t_70)

###### 3、使用
（1）aar 包拷贝你工程的lib下

（2）添加kotlin 支持及aar引入（我们的dialog用kotlin封装的）
```java
//1、工程的build.grdle添加：

buildscript {
    ext.kotlin_version = '1.3.21' // kt 版本
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.0'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"//kt插件
       
    }
}

// 2、项目的build.gradle 添加：
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

// 3、引入aar：

 repositories {
   flatDir {
     dirs 'libs' 
   }

 }

 dependencies {

   implementation(name: 'SubscriptionDialog_1.0.1', ext: 'aar') //必须引入
   implementation 'com.android.support:design:28.0.0'//必须引入,自定义dialog有使用
 }

```

###### 4、简单使用
```java
  val dialog = SubscriptionDialog(this)
        dialog
            .setImage(R.drawable.peiqi)//背景图
            .setContentText("hello custom dialog 、hello custom dialog 、hello custom dialog ")//内容
            .setContentTextColor(Color.RED)//内容的字体颜色
            .setButtonText("sunny")//按钮字体
            .setButtonTextColor(Color.BLACK) // 按钮字体颜色
            .setButtonBackgroundColor(Color.parseColor("#FFD81B60"))
            .setOnContinueClickListener(object : SubscriptionDialog.OnContinueClickListener {
                override fun onClick() {
                    Toast.makeText(applicationContext, "simple dialog", Toast.LENGTH_LONG).show()
                    dialog.dismissDialog()
                }
            })
            .showDialog()
```

### 踩坑总结
###### 1、dialog大小失效问题
>[LayoutInflate 使用时inflate 设置dialog的大小失效参考](https://blog.csdn.net/u012702547/article/details/52628453)
>

###### 2、圆角的设定
>shape 和cardview的选择
>使用shape时，当dialog的背景改变时圆角失效，可以考虑使用cardview作为布局。如果dialog的样式几乎不需要变化时可以使用shape。

###### 3、NoClassDefFoundError
>本文中你的java项目碰见时可能是没引入kotlin环境
>[参考文章](https://blog.csdn.net/jamesjxin/article/details/46606307)


### end
[aar下载](https://github.com/sunnnydaydev/CustomDialog/blob/master/SubscriptionDialog_1.0.1.aar)







