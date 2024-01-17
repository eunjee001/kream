package com.kkyoungs.kream

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kkyoungs.kream.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private var mTabMenuAdapter: TabMenuAdapter? = null
    private var mTabMenuDataList: ArrayList<String>? = null
    private var mTabMenuSelectedPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mTabMenuDataList = ArrayList()
        mTabMenuDataList!!.add("추천")
        mTabMenuDataList!!.add("랭킹")
        mTabMenuDataList!!.add("럭셔리")
        mTabMenuDataList!!.add("남성")
        mTabMenuDataList!!.add("여성")

        mTabMenuAdapter = TabMenuAdapter()
        mTabMenuAdapter!!.setTabMenuList(mTabMenuDataList)
        mTabMenuAdapter!!.setScreenWidth(resources.displayMetrics.widthPixels)
        mTabMenuAdapter!!.setSelectedPosition(mTabMenuSelectedPosition)

        mBinding!!.recyclerTabMenu.adapter= mTabMenuAdapter
        mBinding!!.recyclerTabMenu.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        initView()

        tabSelect()
    }

    fun initView() {

    }

    fun initMainTabLayout() {

    }

    private fun tabSelect() {
        mTabMenuAdapter!!.setOnTabSelectedListener { position ->
            if (position === mTabMenuSelectedPosition) return@setOnTabSelectedListener


            if (mBinding!!.llTabWrapper.getHeight() <= mBinding!!.root
                    .height - mBinding!!.commTitleBar.getHeight()
            ) {
                mTabMenuSelectedPosition = position
                mTabMenuAdapter!!.setSelectedPosition(mTabMenuSelectedPosition)
            }
            val childCount: Int = mBinding!!.llTabWrapper.childCount

        }


    }
}