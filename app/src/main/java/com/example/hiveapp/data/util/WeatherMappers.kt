package com.example.hiveapp.data.util

import androidx.annotation.DrawableRes
import com.example.hiveapp.R
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.remote.weather.WeatherDataDto
import com.example.hiveapp.data.remote.weather.WeatherDto

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherModel
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherModel>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val pressure = pressures[index]
        val humidity = humidities[index]

        IndexedWeatherData(
            index = index,
            data = WeatherModel(
                time = time,
                temperatureCelsius = temperature,
                pressure = pressure,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues { it ->
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo(): Map<Int, List<WeatherModel>> {
    return weatherData.toWeatherDataMap()
}

sealed class WeatherType(
    val weatherDesc: Int,
    @DrawableRes val iconRes: Int
) {
    data object ClearSky : WeatherType(
        weatherDesc = R.string.weather_state_clear_sky,
        iconRes = R.drawable.ic_sunny
    )

    data object MainlyClear : WeatherType(
        weatherDesc = R.string.weather_state_mainly_clear,
        iconRes = R.drawable.ic_cloudy
    )

    data object PartlyCloudy : WeatherType(
        weatherDesc = R.string.weather_state_partly_cloudy,
        iconRes = R.drawable.ic_cloudy
    )

    data object Overcast : WeatherType(
        weatherDesc = R.string.weather_state_overcast,
        iconRes = R.drawable.ic_cloudy
    )

    data object Foggy : WeatherType(
        weatherDesc = R.string.weather_state_foggy,
        iconRes = R.drawable.ic_very_cloudy
    )

    data object DepositingRimeFog : WeatherType(
        weatherDesc = R.string.weather_state_depositing_rime_fog,
        iconRes = R.drawable.ic_very_cloudy
    )

    data object LightDrizzle : WeatherType(
        weatherDesc = R.string.weather_state_light_drizzle,
        iconRes = R.drawable.ic_rainshower
    )

    data object ModerateDrizzle : WeatherType(
        weatherDesc = R.string.weather_state_moderate_drizzle,
        iconRes = R.drawable.ic_rainshower
    )

    data object DenseDrizzle : WeatherType(
        weatherDesc = R.string.weather_state_dense_drizzle,
        iconRes = R.drawable.ic_rainshower
    )

    data object LightFreezingDrizzle : WeatherType(
        weatherDesc = R.string.weather_state_light_freezing_drizzle,
        iconRes = R.drawable.ic_snowyrainy
    )

    data object DenseFreezingDrizzle : WeatherType(
        weatherDesc = R.string.weather_state_dense_freezing_drizzle,
        iconRes = R.drawable.ic_snowyrainy
    )

    data object SlightRain : WeatherType(
        weatherDesc = R.string.weather_state_slight_rain,
        iconRes = R.drawable.ic_rainy
    )

    data object ModerateRain : WeatherType(
        weatherDesc = R.string.weather_state_moderate_rain,
        iconRes = R.drawable.ic_rainy
    )

    data object HeavyRain : WeatherType(
        weatherDesc = R.string.weather_state_heavy_rain,
        iconRes = R.drawable.ic_rainy
    )

    data object HeavyFreezingRain : WeatherType(
        weatherDesc = R.string.weather_state_heavy_freezing_rain,
        iconRes = R.drawable.ic_snowyrainy
    )

    data object SlightSnowFall : WeatherType(
        weatherDesc = R.string.weather_state_slight_snow_fall,
        iconRes = R.drawable.ic_snowy
    )

    data object ModerateSnowFall : WeatherType(
        weatherDesc = R.string.weather_state_moderate_snow_fall,
        iconRes = R.drawable.ic_heavysnow
    )

    data object HeavySnowFall : WeatherType(
        weatherDesc = R.string.weather_state_heavy_snow_fall,
        iconRes = R.drawable.ic_heavysnow
    )

    data object SnowGrains : WeatherType(
        weatherDesc = R.string.weather_state_snow_grains,
        iconRes = R.drawable.ic_heavysnow
    )

    data object SlightRainShowers : WeatherType(
        weatherDesc = R.string.weather_state_slight_rain_showers,
        iconRes = R.drawable.ic_rainshower
    )

    data object ModerateRainShowers : WeatherType(
        weatherDesc = R.string.weather_state_moderate_rain_showers,
        iconRes = R.drawable.ic_rainshower
    )

    data object ViolentRainShowers : WeatherType(
        weatherDesc = R.string.weather_state_violent_rain_showers,
        iconRes = R.drawable.ic_rainshower
    )

    data object SlightSnowShowers : WeatherType(
        weatherDesc = R.string.weather_state_slight_snow_showers,
        iconRes = R.drawable.ic_snowy
    )

    data object HeavySnowShowers : WeatherType(
        weatherDesc = R.string.weather_state_heavy_snow_showers,
        iconRes = R.drawable.ic_snowy
    )

    data object ModerateThunderstorm : WeatherType(
        weatherDesc = R.string.weather_state_moderate_thunderstorm,
        iconRes = R.drawable.ic_thunder
    )

    data object SlightHailThunderstorm : WeatherType(
        weatherDesc = R.string.weather_state_slight_hail_thunderstorm,
        iconRes = R.drawable.ic_rainythunder
    )

    data object HeavyHailThunderstorm : WeatherType(
        weatherDesc = R.string.weather_state_heavy_hail_thunderstorm,
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
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