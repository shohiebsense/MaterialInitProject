package com.shohiebsense.constraintlayoutexample.view

import android.app.DatePickerDialog
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder
import com.shohiebsense.constraintlayoutexample.util.AppUtil
import com.shohiebsense.constraintlayoutexample.R
import kotlinx.android.synthetic.main.item_child.view.*
import kotlinx.android.synthetic.main.item_child_second.view.*
import kotlinx.android.synthetic.main.item_group.view.*
import java.util.*

/**
 * Created by Shohiebsense on 24/03/2018.
 */
class ExpandableAdapter (val expandableItemManager: RecyclerViewExpandableItemManager, val itemClickListener: ItemClickListener) :  AbstractExpandableItemAdapter<ExpandableAdapter.RegisterTitleViewHolder, ExpandableAdapter.RegisterBodyViewHolder>(), RecyclerViewExpandableItemManager.OnGroupCollapseListener, RecyclerViewExpandableItemManager.OnGroupExpandListener{

    val LAYOUT_PERSONAL_INFO = 0
    val LAYOUT_INPUT_PIN = 1
    val layoutItems = arrayListOf<Int>()

    var isFirstTime = true
    var personalInfoPassed = false
    var inputPinPassed = false

    init {
        layoutItems.add(LAYOUT_PERSONAL_INFO)
        layoutItems.add(LAYOUT_INPUT_PIN)
        setHasStableIds(true)
        expandableItemManager.setOnGroupExpandListener(this)
        expandableItemManager.setOnGroupCollapseListener(this)
    }


    override fun getChildCount(groupPosition: Int): Int = 1

    override fun getGroupCount(): Int = 2

    override fun onCheckCanExpandOrCollapseGroup(holder: RegisterTitleViewHolder?, groupPosition: Int, x: Int, y: Int, expand: Boolean): Boolean = false

    fun onRegisterResult(){
        expandableItemManager.notifyChildItemChanged(LAYOUT_INPUT_PIN,0)
    }


    override fun getChildItemViewType(groupPosition: Int, childPosition: Int): Int {
        return layoutItems[groupPosition]
    }

    override fun getGroupItemViewType(groupPosition: Int): Int {
        return layoutItems[groupPosition]
    }


    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): RegisterTitleViewHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        val view = layoutInflater.inflate(R.layout.item_group, parent, false)
        return RegisterTitleViewHolder(view)
    }


    override fun getInitialGroupExpandedState(groupPosition: Int): Boolean {
        return when(groupPosition){
            LAYOUT_PERSONAL_INFO -> true
            else -> false
        }
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): RegisterBodyViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        return when(viewType){
            LAYOUT_PERSONAL_INFO -> {
                PersonalInfoViewHolder(inflater.inflate(R.layout.item_child,parent,false))
            }
            else -> {
                InputPinViewHolder(inflater.inflate(R.layout.item_child_second,parent,false))
            }
        }

    }

    override fun getGroupId(groupPosition: Int): Long = layoutItems[groupPosition].toLong()

    override fun onBindGroupViewHolder(holder: RegisterTitleViewHolder?, groupPosition: Int, viewType: Int) {
        when(groupPosition){
            LAYOUT_PERSONAL_INFO -> {
                holder!!.setTitle(holder.itemView.context.getString(R.string.text_register_subtitle))
                holder.isDisabled(personalInfoPassed)
            }

            LAYOUT_INPUT_PIN -> {
                holder!!.setTitle(holder.itemView.context.getString(R.string.text_title_create_pin))
                holder.isDisabled(inputPinPassed)
                holder.initDisabled()
                isFirstTime = false
            }
        }


        holder!!.itemView.button_edit.setOnClickListener {
            when(groupPosition){
                LAYOUT_PERSONAL_INFO -> {
                    expandableItemManager.collapseGroup(LAYOUT_INPUT_PIN)
                }
            }
            expandableItemManager.expandGroup(groupPosition)
        }
    }


    override fun onBindChildViewHolder(holder: RegisterBodyViewHolder?, groupPosition: Int, childPosition: Int, viewType: Int) {

    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = layoutItems[groupPosition].toLong()



    override fun onGroupCollapse(groupPosition: Int, fromUser: Boolean, payload: Any?) {
    }

    override fun onGroupExpand(groupPosition: Int, fromUser: Boolean, payload: Any?) {
        when(groupPosition){
            LAYOUT_PERSONAL_INFO -> {
                personalInfoPassed = false
                inputPinPassed = false
            }
            LAYOUT_INPUT_PIN -> {
                personalInfoPassed = true
                inputPinPassed = false
            }
        }
    }


    abstract class BaseViewHolder(v : View) : AbstractExpandableItemViewHolder(v) {

    }

    inner class RegisterTitleViewHolder(view : View) : ExpandableAdapter.BaseViewHolder(view) {



        fun setTitle(title : String){
            itemView.text_register_group_title.text = title
        }

        fun initDisabled(){
            itemView.card_register_group.setCardBackgroundColor(if(isFirstTime) ContextCompat.getColor(itemView.context, R.color.gray) else ContextCompat.getColor(itemView.context,R.color.white))
        }

        fun isDisabled(isDisabled : Boolean){
            itemView.button_edit.visibility = if (isDisabled) View.VISIBLE else View.GONE
        }
    }

    open inner class RegisterBodyViewHolder(view : View) : ExpandableAdapter.BaseViewHolder(view) {


    }


    inner class PersonalInfoViewHolder(v: View) : RegisterBodyViewHolder(v) {

        var dateEditTextClickListener = object  : View.OnClickListener{
            val dateFormat = AppUtil.getDateFormFormat()
            val calendar = Calendar.getInstance()
            val datePickerDialog = object: DatePickerDialog.OnDateSetListener{

                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    itemView.edit_date_of_birth.setText(dateFormat.format(calendar.time))
                }
            }
            override fun onClick(v: View?) {
                calendar.add(Calendar.YEAR, -10)
                val datePicker  = DatePickerDialog(itemView.context, datePickerDialog,
                        calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
                datePicker.datePicker.maxDate = calendar.timeInMillis
                datePicker.show()
            }
        }


        init{
            val textWatcher = PersonalInfoTextWatcher()
            itemView.edit_text_full_name.addTextChangedListener(textWatcher)
            itemView.edit_phonenumber.addTextChangedListener(textWatcher)
            itemView.edit_date_of_birth.addTextChangedListener(textWatcher)
            itemView.edit_email_address.addTextChangedListener(textWatcher)
            itemView.edit_date_of_birth.inputType = InputType.TYPE_NULL
            itemView.edit_date_of_birth.setOnClickListener(dateEditTextClickListener)
            itemView.checkbox_policy_agreement.setOnCheckedChangeListener { buttonView, isChecked ->
                itemView.button_next.isEnabled = isChecked &&
                        itemView.edit_text_full_name.text.isNotBlank()
                        && itemView.edit_phonenumber.text.isNotBlank()
                        && itemView.edit_date_of_birth.text.isNotBlank()
                        && itemView.checkbox_policy_agreement.isChecked
                        && itemView.edit_email_address.text.isNotBlank()
            }

            itemView.button_next.setOnClickListener {

                expandableItemManager.collapseGroup(LAYOUT_PERSONAL_INFO)
                expandableItemManager.expandGroup(LAYOUT_INPUT_PIN)
            }
        }

        fun validatingPersonalInfo() : Boolean{

            if(itemView.edit_phonenumber.text.trim().isBlank()) {
                return false
            }
            return true
        }

        inner class PersonalInfoTextWatcher : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                itemView.button_next.isEnabled = validatingPersonalInfo()
            }
        }

    }


    inner class InputPinViewHolder(view : View) : RegisterBodyViewHolder(view){
        init {
            val textWatcher = InputPinTextWatcher()
            itemView.edit_text_create_pin.addTextChangedListener(textWatcher)
            itemView.edit_text_recreate_pin.addTextChangedListener(textWatcher)
            itemView.button_register.isEnabled = validatePin()
            itemView.button_register.setOnClickListener {
                itemClickListener.onRegister()
                itemView.button_register.isEnabled = false
            }
        }


        fun validatePin() : Boolean{

            var pin = itemView.edit_text_create_pin.text.toString()
            var repeatPin = itemView.edit_text_recreate_pin.text.toString()

            if(pin.length != 6){
                return false
            }
            if(!pin.equals(repeatPin)){
                return false
            }

            return true
        }

        inner class InputPinTextWatcher : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isValidate = validatePin()
                itemView.button_register.isEnabled = validatePin()

            }

        }
    }

    interface ItemClickListener {
        fun onRegister()
    }



}