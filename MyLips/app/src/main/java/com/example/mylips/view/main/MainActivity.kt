package com.example.mylips.view.main

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylips.databinding.ActivityMainBinding
import com.example.mylips.retrofit.response.ListColorItem
import com.example.mylips.view.ListColor
import com.example.mylips.view.recommendation.RecommendAdapter
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val colorList = ArrayList<ListColor>()
    private lateinit var rvRecommned: RecyclerView
    private var getFile: File? = null


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.btCamera.setOnClickListener { startCameraX() }
        binding.btGallery.setOnClickListener { startGallery() }
        binding.btUpload.setOnClickListener { uploadImage() }
    }

    private fun startCameraX() {
        binding.previewImageView.visibility = View.VISIBLE
        binding.rvRecommend.visibility = View.GONE
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)


    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
        binding.previewImageView.visibility = View.VISIBLE
        binding.rvRecommend.visibility = View.GONE
    }


    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)


            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestImageFile
            )
            mainViewModel.uploadImage(imageMultipart, file.name)

            mainViewModel.listColor.observe(this) { dataColors ->
                setColors(dataColors)
            }


            showToast()


            rvRecommned = binding.rvRecommend
            rvRecommned.layoutManager = LinearLayoutManager(this)
            rvRecommned.adapter = RecommendAdapter(colorList)


            binding.previewImageView.visibility = View.GONE
            binding.rvRecommend.visibility = View.VISIBLE



        } else {
            Toast.makeText(this@MainActivity, "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setColors(colorData: List<ListColorItem>): ArrayList<ListColor> {
        val link = colorData.map { it.link }
        val name = colorData.map { it.name }


        for (i in link.indices) {

            val color = ListColor(link[i], name[i])
            colorList.add(color)
        }
        return colorList



    }





    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            myFile?.let { file ->
                rotateFile(file, isBackCamera)
                getFile = file
                binding.previewImageView.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@MainActivity)
                getFile = myFile
                binding.previewImageView.setImageURI(uri)
            }
        }
    }

    private fun showToast() {
        mainViewModel.toast.observe(this@MainActivity) { toastText ->
            Toast.makeText(
                this@MainActivity, toastText, Toast.LENGTH_SHORT
            ).show()
        }
    }



    companion object {
        const val CAMERA_X_RESULT = 200
        const val COLOR_LIST = "listColor"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

}

