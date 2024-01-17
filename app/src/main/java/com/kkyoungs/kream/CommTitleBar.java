package com.kkyoungs.kream;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.FontRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;



/**
* 앱 공통 타이틀바
* @author Webcash Smart
* @since 2019-08-15 13:03
**/
public class CommTitleBar extends RelativeLayout {

	/** Context */
	private Context mContext;

	/** 타이틀 텍스트 */
	private TextView mTvTitle;
	private ImageView mBtnBack;
	private RelativeLayout rlContainer;

	/**
	 * 생성자
	 * @param context Context
	 * @param attrs AttributeSet
	 */
	public CommTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		try {

			mContext = context;
			View titleBarLayout = View.inflate(mContext, R.layout.layout_comm_title_bar, null);
			addView(titleBarLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			mTvTitle = findViewById(R.id.tv_center_title);
			mBtnBack = findViewById(R.id.btn_title_back);
			rlContainer = findViewById(R.id.rl_title_bar);

			//+ type
			String strType = attrs.getAttributeValue(null, "Type");
            setTitleBarType(strType);

			//+ title
			String strTitle = attrs.getAttributeValue(null, "Title");
			setTitle(strTitle);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*

	[사용예]
		<com.bizplay.bizzeropay.ui.comm.CommTitleBar
			android:id="@+id/title_bar"
			android:layout_width="match_parent"
			android:layout_height="wrap_content"
			Type = "1"
			Title = "로그인"
    [Type]
        common title Type
        1. 				    title
        2.  back icon       title
        3.                  title       zeropay icon
        4.                  title       push icon
        5.                  title       setting icon
        9.  No TitleBar
        10. back icon       title		push icon
        11. back icon           		title
        12.                 title       close icon (change icon)
        13.
        14.
        15.
        16. back icon       title       right icon


*/
    /**
     * 타이틀바 타입 설정
     * @param strType 타이틀바 타입 코드
     */
	public void setTitleBarType(String strType) {

        if (strType == null) {
            return;
        }


		try {
			if ("1".equals(strType)) {
			    // 타이틀바 사용
			    findViewById(R.id.rl_title_bar).setVisibility(VISIBLE);
                // 타이틀
				mTvTitle.setVisibility(View.VISIBLE);
				// 좌측 버튼 없음
				findViewById(R.id.rl_title_left).setVisibility(View.GONE);
				// 우측 버튼 없음
				findViewById(R.id.rl_title_right).setVisibility(GONE);

			} else if ("2".equals(strType)) {
                // 타이틀바 사용
                findViewById(R.id.rl_title_bar).setVisibility(VISIBLE);
                // 타이틀
                mTvTitle.setVisibility(View.VISIBLE);
                // 좌측 버튼 있음 >>> 이전 버튼
                findViewById(R.id.rl_title_left).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_title_back).setVisibility(View.VISIBLE);
                // 우측 버튼 없음
                findViewById(R.id.rl_title_right).setVisibility(GONE);

            } else if ("3".equals(strType)) {
                // 타이틀바 사용
                findViewById(R.id.rl_title_bar).setVisibility(VISIBLE);
                // 타이틀
                mTvTitle.setVisibility(View.VISIBLE);
                // 좌측 버튼 없음
                findViewById(R.id.rl_title_left).setVisibility(View.GONE);
                // 우측 버튼 있음 >>> 닫기 버튼
                findViewById(R.id.rl_title_right).setVisibility(VISIBLE);
                findViewById(R.id.btn_alarm).setVisibility(VISIBLE);

            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 상단 제목 세팅
     * @param title 타이틀 String
     */
    public void setTitle(String title) {
        if (mTvTitle == null) {
            return;
        }
        if (null == title || "".equals(title)) {
            mTvTitle.setText("");
        }

//        if (title.charAt(0) != '@') {
//            mTvTitle.setText(title);
//        } else {
//            int resId = FileUtil.getResIdFromName(mContext, null, title.substring(1));
////			int resId = getResources().getIdentifier(title.substring(1), null, getContext().getPackageName());
//            if (resId > 0)
//                mTvTitle.setText(getResources().getString(resId));
//            else mTvTitle.setText("");
//        }
    }

    public void setTitleBarTheme(
            int bgColor,
            int txtColor,
            int icon
    ) {
        rlContainer.setBackgroundColor(ContextCompat.getColor(mContext, bgColor));
        mTvTitle.setTextColor(ContextCompat.getColor(mContext, txtColor));
        mBtnBack.setImageDrawable(ContextCompat.getDrawable(mContext, icon));
    }

    /**
     * 타이틀 폰트 설정
     * @param fontFamily Font Resource
     */
    public void setTitleFontFamily(@FontRes int fontFamily, int fontSize){
        mTvTitle.setTypeface(ResourcesCompat.getFont(mContext, fontFamily));
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize);
    }

    /**
     * 타이틀바 View 클릭리스너 등록
     * @param clickListener OnClickListener
     */
	public void setOnClickListener(OnClickListener clickListener) {
		try {

			// 좌측 이전버튼
			findViewById(R.id.btn_title_back).setOnClickListener(clickListener);
			// 우측 닫기버튼
			findViewById(R.id.btn_alarm).setOnClickListener(clickListener);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
