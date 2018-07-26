package currencies.crypto.com.cryptocurrencies.model

import com.db.chart.model.LineSet

data class Currency(val name: String, val icon: Int, val graphData: LineSet, val exchangeValue: String, val trendingValue: String, val isGrow: Boolean)