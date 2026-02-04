# MyNewsAggregator

Кроссплатформенное приложение для чтения новостей на Android, iOS и Web.

## О проекте

Агрегатор новостей с единым кодом для всех платформ. Использует TheNewsAPI для получения новостей.

## Возможности

- 🌍 Поддержка нескольких языков
- 📂 Фильтрация по категориям
- 🔍 Детальный просмотр новостей
- 🖼️ Отображение изображений
- 📱 Адаптивный интерфейс

## Технологии

**Основа:**

- Kotlin 2.3.0
- Kotlin Multiplatform
- Compose Multiplatform

**Архитектура:**

- MVVM
- Clean Architecture
- Repository Pattern

**Библиотеки:**

- Ktor Client — сетевые запросы
- Kotlinx Serialization — работа с JSON
- Koin — Dependency Injection
- Navigation Compose — навигация
- Coil — загрузка изображений
- Material 3 — UI компоненты
- Kotlinx DateTime — работа с датой и временем

## Платформы

- **Android** — minSdk 24, targetSdk 35
- **iOS** — ARM64, Simulator ARM64
- **Web** — WASM и JavaScript

## Запуск

### Требования

- JDK 11+
- Android Studio (для Android)
- Xcode (для iOS, macOS)
- Gradle 8.x

### Android

```bash
./gradlew :composeApp:assembleDebug
```

### iOS

Откройте `iosApp` в Xcode и запустите проект.

### Web

WASM:

```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

JavaScript:

```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

Приложение доступно по адресу `http://localhost:8080`

## Структура проекта

```
MyNewsAggregator/
├── composeApp/
│   └── src/
│       ├── commonMain/          # Общий код
│       │   └── kotlin/
│       │       └── com/example/mynewsaggregator/
│       │           ├── data/            # Данные, API, репозитории
│       │           ├── presentation/    # UI, экраны, навигация
│       │           └── utils/           # Утилиты
│       ├── androidMain/         # Android код
│       ├── iosMain/             # iOS код
│       ├── jsMain/              # JS код
│       ├── wasmJsMain/          # WASM код
│       └── webMain/             # Общий Web код
└── iosApp/                      # iOS приложение
```

## Конфигурация

API ключ находится в `NewsApiService.kt`. Получить ключ можно на [thenewsapi.com](https://www.thenewsapi.com/).

## Ссылки

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/)
- [Ktor](https://ktor.io/docs/welcome.html)
- [Koin](https://insert-koin.io/)
