package com.vadejurisbr

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)

        val webView = findViewById<WebView>(R.id.webView)
        val fileName = intent.getStringExtra("FILE_NAME")
        val title = intent.getStringExtra("LAW_TITLE")

        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        if (fileName != null) {
            webView.loadUrl("file:///android_asset/legislacao/$fileName")
        } else {
            Toast.makeText(this, "Erro ao carregar arquivo", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
