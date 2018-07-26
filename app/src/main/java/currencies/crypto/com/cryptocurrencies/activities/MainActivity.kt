package currencies.crypto.com.cryptocurrencies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.db.chart.model.LineSet
import currencies.crypto.com.cryptocurrencies.R
import currencies.crypto.com.cryptocurrencies.adapter.CurrenciesAdapter
import currencies.crypto.com.cryptocurrencies.model.Currency
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lineSetBitcoin = LineSet(arrayOf("1", "2", "3", "4", "5", "6","7","8","9","10", "11", "12", "13", "14", "15","16","17","18","19","20"),
                floatArrayOf(1000f, 300f, 1200f, 500f, 650f, 1400f, 200f, 800f, 700f, 500f,550f, 300f, 1000f, 100f, 650f, 900f, 200f, 800f, 400f, 500f))
        val bitcoin = Currency("Bitcoin", R.drawable.icon_bitcoin, lineSetBitcoin, "$6 623,08", "+4.02%", true)
        val lineSetEthereum = LineSet(arrayOf("1", "2", "3", "4", "5", "6","7","8","9","10", "11", "12", "13", "14", "15","16","17","18","19","20"),
                floatArrayOf(1000f, 300f, 1200f, 500f, 650f, 1400f, 200f, 800f, 700f, 500f,550f, 300f, 1000f, 100f, 650f, 900f, 200f, 800f, 400f, 500f))
        val ethereum = Currency("Ethereum", R.drawable.icon_ethereum, lineSetEthereum, "$473,85", "-0.32%", false)
        val items = mutableListOf(bitcoin, ethereum)
        val adapter = CurrenciesAdapter(items){
            Toast.makeText(this, "${it.name} clicked", Toast.LENGTH_SHORT).show()
        }
        currencies_list.adapter = adapter
        currencies_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
