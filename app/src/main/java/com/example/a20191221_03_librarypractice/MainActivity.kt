package com.example.a20191221_03_librarypractice

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


        callBtn.setOnClickListener {

//            전화 권한 요청(3) =>획득 완료되면(1) => 인텐트를 이용해서 전화걸기(2)
            val permissionListener = object : PermissionListener{
                override fun onPermissionGranted() {
//                    권환 획득에 성공한 상태
                    val uri = Uri.parse("tel:01056091101")
                    val intent = Intent(Intent.ACTION_CALL,uri)
                    startActivity(intent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext,"전화권한을 허용해야 걸 수 있습니다",Toast.LENGTH_SHORT).show()
                }
            }
            TedPermission.with(mContext)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("권한 설정이 필요합니다")
                .setRationaleMessage("바로 전화를 걸기 위해 필요")
                .setPermissions(Manifest.permission.CALL_PHONE)  // Ctrl + Alt + o 쓸데없이 임포트된 클래스 제거 단축키
                .check()
        }
    }

    override fun setValues() {
        Glide.with(mContext).load("https://pbs.twimg.com/profile_images/799445590614495232/ii6eBROd_400x400.jpg").into(profileImgView)

    }

}
