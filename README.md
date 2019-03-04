# Project Template - Fintech Edition

Just clone and update the repos if it's needed

Description
Layout View for Fintech Application purposes. Never been so easy.

Libraries used:
- Android Support: AppCompat, Design, vector drawable, recyclerview, cardview, constraint layout
- Discrete Scrollview
- Pin Entryview
- Snacky
- Fancy Buttons
- Zxing-Android
- Circle ImageView
- Advanced RecyclerView
- Picasso
- Android Toggle Switch
- ViewPager Layout Manager
- Eventbus
- okhttp3
- okio
- gson
- rxjava2, rxandroid, rxkotlin


Cases

1. Your home app displays Toolbar that can be scrolled, and there is a framelayout contains fragment. This fragment contains viewpagers (yes, multiple viewpager) which are displaying recyclerview. What you are gonna do is you wrapped the framelayoutin the activity inside NestedScrollview. Then in the layout fragment, wrapped entire layout with nestedscrollview again for scrolling those multiple viewpager. Put each viewpager absolute height neither wrap content nor match_parent. 

Like this
> activity_main
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.NestedScrollView 
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    android:fillViewport="true"
    tools:context=".view.page.home.MainActivity"
    tools:showIn="@layout/activity_main">

    <FrameLayout
        android:id="@+id/frameLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />


</android.support.v4.widget.NestedScrollView>
```
> fragment_main

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.NestedScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/scroll_root"
    android:background="@color/white_light"
    app:layout_behavior="@string/appbar_sc_view_behavior"
    >

        <android.support.constraint.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

        <android.support.design.widget.TabLayout
            android:focusable="true"
            android:focusableInTouchMode="true"
            android:id="@+id/tab_a_b"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintTop_toBottomOf="@+id/layout_point"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:tabIndicatorColor="@color/color_tab_indicator"
            app:tabSelectedTextColor="@color/color_tab_text_selected"
            app:tabTextColor="@color/color_tab_text"
            app:tabTextAppearance="@style/AppTheme.TextAppearance.Tab"
            >

            <android.support.design.widget.TabItem
                android:id="@+id/tab_item_a"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/promo" />

            <android.support.design.widget.TabItem
                android:id="@+id/tab_item_b"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/spot" />

        </android.support.design.widget.TabLayout>

        <ScrollableViewPager
            android:id="@+id/pager_a_b"
            android:layout_width="match_parent"
            android:layout_height="400dp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/tab_a_b"
            />

        <android.support.design.widget.TabLayout
            android:focusable="true"
            android:focusableInTouchMode="true"
            android:id="@+id/tab_c_d"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:tabIndicatorColor="@color/color_tab_indicator"
            app:tabSelectedTextColor="@color/color_tab_text_selected"
            app:tabTextColor="@color/color_tab_text"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/pager_a_b"
            >

            <android.support.design.widget.TabItem
                android:id="@+id/tab_item_b"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/news" />

            <android.support.design.widget.TabItem
                android:id="@+id/tab_item_c"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/event" />

        </android.support.design.widget.TabLayout>

        <ScrollableViewPager
            android:id="@+id/pager_c_d"
            android:layout_width="match_parent"
            android:layout_height="400dp"
            android:nestedScrollingEnabled="true"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/tab_home_news_event"
            app:layout_constraintBottom_toBottomOf="parent"
            />


        </android.support.constraint.ConstraintLayout>
</android.support.v4.widget.NestedScrollView>
```

2. Use recyclerview and apply FlexBoxLayoutManager, like when you created items horizontally and wrapped to the width of device.

3. You have to set typeface programmatically to change Input Layout text/hint

4. For applying different colors in text, use foregroundcolorspan
SpanableString(string)
ForegroundColorSpan(
StyleSpan(
