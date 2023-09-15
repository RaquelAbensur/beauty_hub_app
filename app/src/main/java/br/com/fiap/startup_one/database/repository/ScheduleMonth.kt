package br.com.fiap.startup_one.database.repository

import br.com.fiap.startup_one.model.Schedule

fun getAllMonth(): List<Schedule>  {
    val daysInMonth = 30
    val initialDayOfWeek = "Seg"
    val scheduleList = mutableListOf<Schedule>()

    var currentDayOfWeek = initialDayOfWeek

    for (day in 1..daysInMonth) {
        scheduleList.add(Schedule(day, currentDayOfWeek))

        currentDayOfWeek = when (currentDayOfWeek) {
            "Seg" -> "Ter"
            "Ter" -> "Qua"
            "Qua" -> "Qui"
            "Qui" -> "Sex"
            "Sex" -> "Sáb"
            "Sáb" -> "Dom"
            "Dom" -> "Seg"
            else -> throw IllegalArgumentException("Dia da semana inválido: $currentDayOfWeek")
        }
    }

    return scheduleList
}