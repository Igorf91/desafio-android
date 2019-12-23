package br.com.igorf91.desafio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PullRequestActivity : AppCompatActivity() {

    companion object {
        operator fun invoke(context: Context): Intent {
            return Intent(context, PullRequestActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
    }
}
