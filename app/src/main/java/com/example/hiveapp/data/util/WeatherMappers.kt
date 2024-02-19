package com.example.hiveapp.data.util

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import com.example.hiveapp.R
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.remote.weather.WeatherDataDto
import com.example.hiveapp.data.remote.weather.WeatherDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherModel
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherModel>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): Map<Int, List<WeatherModel>> {
    return weatherData.toWeatherDataMap()
}

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : WeatherType(
        weatherDesc = "Czyste niebo",
        iconRes = R.drawable.ic_sunny
    )

    object MainlyClear : WeatherType(
        weatherDesc = "Głównie bezchmurnie",
        iconRes = R.drawable.ic_cloudy
    )

    object PartlyCloudy : WeatherType(
        weatherDesc = "Częściowo pochmurno",
        iconRes = R.drawable.ic_cloudy
    )

    object Overcast : WeatherType(
        weatherDesc = "Zachmurzenie całkowite",
        iconRes = R.drawable.ic_cloudy
    )

    object Foggy : WeatherType(
        weatherDesc = "Mgliste",
        iconRes = R.drawable.ic_very_cloudy
    )

    object DepositingRimeFog : WeatherType(
        weatherDesc = "Mgła z szadzią",
        iconRes = R.drawable.ic_very_cloudy
    )

    object LightDrizzle : WeatherType(
        weatherDesc = "Lekka mżawka",
        iconRes = R.drawable.ic_rainshower
    )

    object ModerateDrizzle : WeatherType(
        weatherDesc = "Umiarkowana mżawka",
        iconRes = R.drawable.ic_rainshower
    )

    object DenseDrizzle : WeatherType(
        weatherDesc = "Gęsta mżawka",
        iconRes = R.drawable.ic_rainshower
    )

    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Lekkie przymrozki",
        iconRes = R.drawable.ic_snowyrainy
    )

    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Gęste przymrozki",
        iconRes = R.drawable.ic_snowyrainy
    )

    object SlightRain : WeatherType(
        weatherDesc = "Lekki deszcz",
        iconRes = R.drawable.ic_rainy
    )

    object ModerateRain : WeatherType(
        weatherDesc = "Deszcz",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyRain : WeatherType(
        weatherDesc = "Ulewny deszcz",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Ulewna marznąca deszcz",
        iconRes = R.drawable.ic_snowyrainy
    )

    object SlightSnowFall: WeatherType(
        weatherDesc = "Lekkie opady śniegu",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateSnowFall: WeatherType(
        weatherDesc = "Umiarkowane opady śniegu",
        iconRes = R.drawable.ic_heavysnow
    )

    object HeavySnowFall: WeatherType(
        weatherDesc = "Intensywne opady śniegu",
        iconRes = R.drawable.ic_heavysnow
    )

    object SnowGrains: WeatherType(
        weatherDesc = "Śnieg pyłowy",
        iconRes = R.drawable.ic_heavysnow
    )

    object SlightRainShowers: WeatherType(
        weatherDesc = "Lekkie przelotne opady deszczu",
        iconRes = R.drawable.ic_rainshower
    )

    object ModerateRainShowers: WeatherType(
        weatherDesc = "Umiarkowane przelotne opady deszczu",
        iconRes = R.drawable.ic_rainshower
    )

    object ViolentRainShowers: WeatherType(
        weatherDesc = "Gwałtowne przelotne opady deszczu",
        iconRes = R.drawable.ic_rainshower
    )

    object SlightSnowShowers: WeatherType(
        weatherDesc = "Lekkie przelotne opady śniegu",
        iconRes = R.drawable.ic_snowy
    )

    object HeavySnowShowers: WeatherType(
        weatherDesc = "Intensywne przelotne opady śniegu",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Umiarkowany burza z piorunami",
        iconRes = R.drawable.ic_thunder
    )

    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Burza z piorunami i lekkim gradem",
        iconRes = R.drawable.ic_rainythunder
    )

    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Burza z piorunami i intensywnym gradem",
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}