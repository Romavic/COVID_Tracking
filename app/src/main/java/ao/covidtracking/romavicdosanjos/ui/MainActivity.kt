package ao.covidtracking.romavicdosanjos.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ao.covidtracking.romavicdosanjos.R


class MainActivity : AppCompatActivity() {

    private lateinit var home_toolbar:Toolbar

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_toolbar = findViewById(R.id.home_toolbar)

        setSupportActionBar(home_toolbar)
        home_toolbar.setTitleTextAppearance(this, R.style.ToolbarFont)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuMoreInformation -> {
                val link = "https://www.cdc.gov/coronavirus/2019-ncov/index.html"
                val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intentWeb)
            }

            R.id.menuCloseApp -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
