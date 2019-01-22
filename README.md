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
