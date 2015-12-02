# BadgeTextView

一个可以在TextView控件上显示数字徽章的Android控件

![Screenshot](https://raw.githubusercontent.com/xiaolifan/BadgeTextView/master/art/device-2015-12-02-114928.png)

## 属性说明

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="BadgeTextView">
        <!--徽章文字颜色-->
        <attr name="badgeTextColor" format="color|reference" />
        <!--徽章文字大小-->
        <attr name="badgeTextSize" format="dimension|reference" />
        <!--徽章背景色-->
        <attr name="badgeBackgroundColor" format="color|reference" />
        <!--徽章相对于控件的外边距-->
        <attr name="badgeMargin" format="dimension|reference" />
        <!--徽章内边距-->
        <attr name="badgePadding" format="dimension|reference" />
    </declare-styleable>
</resources>
```

## 使用

直接在XML中声明，在代码中调用setBadgeCount设置数字即可。

```xml
<com.ns.badgetextview.BadgeTextView
        android:id="@+id/tv_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#a4a486"
        android:gravity="center"
        android:padding="10dp"
        android:text="纯文字演示"
        android:textColor="#000"
        app:badgeBackgroundColor="#f00"
        app:badgeMargin="2dp"
        app:badgePadding="6dp"
        app:badgeTextColor="#fff"
        app:badgeTextSize="12dp" />
```

```java
tvText = (BadgeTextView) findViewById(R.id.tv_text);
tvText.setBadgeCount(8);
```

## License

    Mozilla Public License, version 2.0

