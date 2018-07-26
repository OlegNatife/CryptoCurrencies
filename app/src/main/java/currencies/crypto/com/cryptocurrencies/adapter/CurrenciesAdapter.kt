package currencies.crypto.com.cryptocurrencies.adapter

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.db.chart.renderer.AxisRenderer
import currencies.crypto.com.cryptocurrencies.R
import currencies.crypto.com.cryptocurrencies.model.Currency
import kotlinx.android.synthetic.main.list_item_recycler_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.textColor
import org.jetbrains.anko.uiThread

class CurrenciesAdapter(private val items: MutableList<Currency>,
                        private val listener: (Currency) -> Unit) : RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recycler_main, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], listener)
    }

    inner class ViewHolder(cv: CardView) : RecyclerView.ViewHolder(cv) {
        fun bindView(item: Currency, listener: (Currency) -> Unit){
            with(itemView){
                progressBar.visibility = View.VISIBLE
                item_currency_image.setImageResource(item.icon)
                item_currency_name.text = item.name
                item_trending_value.text = item.trendingValue
                exchange_rate.text = item.exchangeValue
                doAsync {
                    item_chart.setYAxis(false)
                    item_chart.setXAxis(false)
                    item_chart.setXLabels(AxisRenderer.LabelPosition.NONE)
                    item_chart.setYLabels(AxisRenderer.LabelPosition.NONE)
                    item.graphData.thickness = 6f
                    if (!item.isGrow){
                        item_trending_value.textColor = context.resources.getColor(R.color.colorGraphLineFalls)
                        item_trending_icon.setImageDrawable(context.resources.getDrawable(R.drawable.icon_trending_down))
                        item_trending_icon.scaleX = -1f
                        exchange_rate.textColor = context.resources.getColor(R.color.colorGraphLineFalls)
                        item.graphData.color = context.resources.getColor(R.color.colorGraphLineFalls)
                        item.graphData.setGradientFill(intArrayOf(resources.getColor(R.color.colorFallsGradientStart), Color.WHITE), floatArrayOf(0f, 1f))
                    }else{
                        item.graphData.color = context.resources.getColor(R.color.colorGraphLineGrows)
                        item.graphData.setGradientFill(intArrayOf(resources.getColor(R.color.colorGrowsGradientStart), Color.WHITE), floatArrayOf(0f, 1f))
                    }
                    item_chart.addData(item.graphData)
                    uiThread {
                        item_chart.show()
                        progressBar.visibility = View.GONE
                        setOnClickListener {
                            listener(item)
                        }
                    }
                }
            }
        }
    }
}