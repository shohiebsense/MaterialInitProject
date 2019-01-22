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

Your home app displays Toolbar that can be scrolled, and there is a framelayout contains fragment. This fragment contains viewpagers (yes, multiple viewpager) which are displaying recyclerview. What you are gonna do is you wrapped the framelayoutin the activity inside NestedScrollview. Then in the layout fragment, wrapped entire layout with nestedscrollview again for scrolling those multiple viewpager. Put each viewpager absolute height neither wrap content nor match_parent. 

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
        android:foreground="@drawable/bg_window_dim"
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

        <!--<RelativeLayout
            android:id="@+id/layout_point"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:background="@drawable/main_header_selector_bottom"
            android:paddingLeft="@dimen/spacing_normal"
            android:paddingRight="@dimen/spacing_normal"
            android:paddingBottom="@dimen/spacing_normal"
            android:layout_below="@id/containerMenu">

            <ImageView
                android:id="@+id/ivMenuOrnament"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:src="@drawable/bg_toolbar"/>

            <View
                android:id="@+id/separator"
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:layout_marginBottom="@dimen/spacing_tiny"
                android:alpha="0.2"
                android:background="@color/white_dark" />

            <TextView
                android:id="@+id/tvPointLabel"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_below="@id/separator"
                android:layout_marginLeft="@dimen/spacing_large"
                android:layout_marginStart="@dimen/spacing_large"
                android:layout_marginTop="@dimen/spacing_small"
                android:text="@string/your_point"
                android:textColor="@color/white_light"
                style="@style/AppTheme.TextAppearance.Medium"/>

            <ImageView
                android:id="@+id/icPoint"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_below="@id/tvPointLabel"
                android:layout_alignLeft="@id/tvPointLabel"
                android:layout_alignStart="@id/tvPointLabel"
                android:layout_marginTop="@dimen/spacing_tiny"
                android:src="@drawable/ic_point" />

            <TextView
                android:id="@+id/tvPoint"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_below="@id/tvPointLabel"
                android:layout_toRightOf="@id/icPoint"
                android:layout_toEndOf="@id/icPoint"
                android:layout_marginTop="-3dp"
                android:layout_marginLeft="@dimen/spacing_small"
                android:layout_marginStart="@dimen/spacing_small"
                android:layout_marginBottom="@dimen/spacing_tiny"
                android:text="1500"
                android:textColor="@color/white_light"
                android:textSize="@dimen/font_title"
                style="@style/AppTheme.TextAppearance.Large"/>

            <Button
                android:id="@+id/btnTopup"
                android:layout_alignParentRight="true"
                android:layout_alignParentEnd="true"
                android:layout_below="@id/separator"
                android:layout_marginTop="@dimen/spacing_normal"
                android:layout_marginBottom="@dimen/spacing_tiny"
                android:foreground="?selectableItemBackground"
                android:text="@string/topup"
                android:textColor="@color/green_light"
                style="@style/AppTheme.Widget.Button.Secondary" />

        </RelativeLayout>-->



        <android.support.design.widget.TabLayout
            android:focusable="true"
            android:focusableInTouchMode="true"
            android:id="@+id/tab_home_promo_spot"
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
                android:id="@+id/tab_item_promo"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/promo" />

            <android.support.design.widget.TabItem
                android:id="@+id/tab_item_spot"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/spot" />

        </android.support.design.widget.TabLayout>

        <ScrollableViewPager
            android:id="@+id/pager_promo_spot"
            android:layout_width="match_parent"
            android:layout_height="400dp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/tab_home_promo_spot"
            />

        <android.support.design.widget.TabLayout
            android:focusable="true"
            android:focusableInTouchMode="true"
            android:id="@+id/tab_home_news_event"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:tabIndicatorColor="@color/color_tab_indicator"
            app:tabSelectedTextColor="@color/color_tab_text_selected"
            app:tabTextColor="@color/color_tab_text"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/pager_promo_spot"
            >

            <android.support.design.widget.TabItem
                android:id="@+id/tab_item_news"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/news" />

            <android.support.design.widget.TabItem
                android:id="@+id/tab_item_event"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/event" />

        </android.support.design.widget.TabLayout>

        <ScrollableViewPager
            android:id="@+id/pager_news_event"
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
