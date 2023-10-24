import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

private const val BASE_URL = "https://api.adsb.one/v2/"
private val server: SBSServer = SBSServer()

fun main(args: Array<String>) {
    val lat = args[0]
    val lon = args[1]
    println("Querying ADSB 250nm around: $lat, $lon")
    server.start()
    val client = OkHttpClient()
    val request = Request.Builder().url("${BASE_URL}point/$lat/$lon/250").build()
    Timer().schedule(object : TimerTask() {
        override fun run() {
            try {
                client.newCall(request).execute().body?.let { server.sendData(Gson().fromJson(it.string(), ADSBData::class.java)) }
            } catch (e: Exception) {
                println("Failed to query ADSB data: ${e.message}")
            }
        }
    }, 0, 1010)
}

data class ADSBData(
    val msg: String = "",
    val total: Int = 0,
    val ac: List<Aircraft>
)

data class Aircraft(
    val hex: String = "",
    val type: String = "",
    val flight: String = "",
    val r: String = "",
    val t: String = "",
    val desc: String = "Unknown",
    val ownOp: String = "",
    val year: String = "",
    val alt_baro: String = "",
    val alt_geom: Int = 0,
    val gs: Double = 0.0,
    val track: Double = 0.0,
    val baro_rate: Int = 0,
    val squawk: String = "",
    val emergency: String = "",
    val category: String = "",
    val nav_qnh: Double = 0.0,
    val nav_altitude_mcp: Int = 0,
    val nav_heading: Double = 0.0,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val nic: Int = 0,
    val rc: Int = 0,
    val seen_pos: Double = 0.0,
    val version: Int = 0,
    val nic_baro: Int = 0,
    val nac_p: Int = 0,
    val nac_v: Int = 0,
    val sil: Int = 0,
    val sil_type: String = "",
    val gva: Int = 0,
    val sda: Int = 0,
    val alert: Int = 0,
    val spi: Int = 0,
    val messages: Int = 0,
    val seen: Double = 0.0,
    val rssi: Double = 0.0,
    val dst: Double = 0.0,
    val dir: Double = 0.0
) {
    fun getAltitude() = alt_baro.toIntOrNull() ?: 0
}
