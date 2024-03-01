package com.example.beautybox.firstLaunch

enum class PhysicalActivity (val physicalActivity: String, val value: Double) {
    LOW("Низкая", 0.1),
    AVERAGE("Cредняя", 0.3),
    HIGH("Высокая", 0.5),
    NO_ANSWER("Неизвестно", 0.1)
}