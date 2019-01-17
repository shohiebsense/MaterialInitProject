package com.shohiebsense.fintechtemplate


import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LauncherActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE")

    @Test
    fun launcherActivityTest() {
        val fancyButton = onView(
                allOf(withId(R.id.button_launcher), withText("Collapse Toolbar "),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.v7.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()))
        fancyButton.perform(click())

        val fancyButton2 = onView(
                allOf(withId(R.id.button_launcher), withText("Collapse Toolbar "),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.v7.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()))
        fancyButton2.perform(click())

        val appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.collapsing_toolbar_promo),
                                                1)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.collapsing_toolbar_promo),
                                                1)),
                                1),
                        isDisplayed()))
        appCompatImageButton2.perform(click())

        val fancyButton3 = onView(
                allOf(withId(R.id.button_launcher), withText("Expandable Item"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.v7.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()))
        fancyButton3.perform(click())

        val textInputEditText = onView(
                allOf(withId(R.id.edit_text_full_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_full_name),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText.perform(replaceText("by g"))

        val textInputEditText2 = onView(
                allOf(withId(R.id.edit_text_full_name), withText("by g"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_full_name),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText2.perform(click())

        val textInputEditText3 = onView(
                allOf(withId(R.id.edit_text_full_name), withText("by g"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_full_name),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText3.perform(click())

        val textInputEditText4 = onView(
                allOf(withId(R.id.edit_phonenumber),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_phone_number),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText4.perform(replaceText("085222"))

        val textInputEditText5 = onView(
                allOf(withId(R.id.edit_phonenumber), withText("085222"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_phone_number),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText5.perform(click())

        val textInputEditText6 = onView(
                allOf(withId(R.id.edit_date_of_birth),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_date_of_birth),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText6.perform(click())

        val appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                3)))
        appCompatButton.perform(scrollTo(), click())

        val textInputEditText7 = onView(
                allOf(withId(R.id.edit_email_address),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_email_address),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText7.perform(replaceText("feawfe."))

        val appCompatCheckBox = onView(
                allOf(withId(R.id.checkbox_policy_agreement), withText("I Agree with Policy"),
                        childAtPosition(
                                allOf(withId(R.id.layout_layout_account),
                                        childAtPosition(
                                                withId(R.id.card_account_info),
                                                0)),
                                5),
                        isDisplayed()))
        appCompatCheckBox.perform(click())

        val textInputEditText8 = onView(
                allOf(withId(R.id.edit_email_address), withText("feawfe."),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_email_address),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText8.perform(replaceText("feawfe@"))

        val textInputEditText9 = onView(
                allOf(withId(R.id.edit_email_address), withText("feawfe@"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_input_email_address),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText9.perform()

        val appCompatButton2 = onView(
                allOf(withId(R.id.button_next), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.layout_layout_account),
                                        childAtPosition(
                                                withId(R.id.card_account_info),
                                                0)),
                                6),
                        isDisplayed()))
        appCompatButton2.perform(click())


        val fancyButton4 = onView(
                allOf(withId(R.id.button_launcher), withText("Gallery Example"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.v7.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()))
        fancyButton4.perform(click())

        val fancyButton5 = onView(
                allOf(withId(R.id.button_launcher), withText("Gallery Example"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.v7.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()))
        fancyButton5.perform(click())

        pressBack()


        val fancyButton6 = onView(
                allOf(withId(R.id.button_launcher), withText("Weight Attribute Activity"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.v7.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()))
        fancyButton6.perform(click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
