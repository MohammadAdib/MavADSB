import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

private const val BASE_URL = "https://api.adsb.one/v2/"
private val server: SBSServer = SBSServer()
private var radius = 250
private var polling_delay = 1000L
private var quiet = false

fun main(args: Array<String>) {
    val positional_args = args.dropWhile { it == "-q" }.toTypedArray()
    (positional_args.size < args.size).also { quiet = it }
    val lat = positional_args[0]
    val lon = positional_args[1]
    if (positional_args.size > 2) radius = 250.coerceAtMost((positional_args[2].toIntOrNull() ?: radius))
    if (positional_args.size > 3) polling_delay = 1000L.coerceAtLeast(positional_args[3].toLongOrNull() ?: polling_delay)
    println("Querying ADSB ${radius}nm around ($lat, $lon) every ${polling_delay}ms")
    server.start()
    val client = OkHttpClient()
    val request = Request.Builder().url("${BASE_URL}point/$lat/$lon/$radius").build()
    Timer().schedule(object : TimerTask() {
        override fun run() {
            try {
                if (server.clients.isNotEmpty()) client.newCall(request).execute().body?.let { server.sendData(Gson().fromJson(it.string(), ADSBData::class.java), quiet) }
            } catch (e: Exception) {
                if (!quiet) println("Failed to query ADSB data: ${e.message}")
            }
        }
    }, 0, polling_delay)
}

data class ADSBData(
    val msg: String = "",
    val total: Int = 0,
    val ac: List<Aircraft>
)

data class Aircraft(
    val hex: String = "",
    private val flight: String = "",
    val year: String = "",
    private val alt_baro: String = "",
    val gs: Double = 0.0,
    val track: Double = 0.0,
    val squawk: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
) {
    fun getFlight() = flight.trim().uppercase(Locale.getDefault())
    fun getAltitude() = alt_baro.toIntOrNull() ?: 0
}
