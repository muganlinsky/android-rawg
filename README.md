# 🎮 RAWG Games Android App

![Kotlin](https://img.shields.io/badge/Kotlin-Android-purple)
![Platform](https://img.shields.io/badge/Platform-Android-green)
![UI](https://img.shields.io/badge/UI-Jetpack%20Compose-blue)
![Architecture](https://img.shields.io/badge/Architecture-Clean-orange)
![API](https://img.shields.io/badge/API-RAWG-red)

An Android app built with **Kotlin** and **Jetpack Compose** that integrates with the **RAWG Video Games Database API** to fetch and display game data.

## Features

- Browse new and trending games
- Display game cards with cover image, title, and rating
- Modern UI built with Jetpack Compose
- RAWG API integration
- Clean and scalable project structure

## Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Koin**
- **Ktor Client**
- **Coroutines**
- **Flow**
- **Gradle Version Catalog**

## Architecture

This project follows a simple clean architecture approach with separation between:

- **data** – API, DTOs, repository implementations
- **domain** – models and use cases
- **presentation** – UI, ViewModels, state

Example structure:

```text
app
├── data
│   ├── dto
│   ├── network
│   └── repository
├── domain
│   ├── model
│   └── usecase
└── presentation
    ├── components
    ├── screens
    └── viewmodel
```

## API

This project uses the **RAWG Video Games Database API**.

- Docs: [https://api.rawg.io/docs/](https://api.rawg.io/docs/)
- Portal: [https://rawg.io/apidocs](https://rawg.io/apidocs)

## Future Improvements

- Game details screen
- Search support
- Pagination
- Offline caching
- Unit tests
- UI tests

## License

This project is for learning and portfolio purposes.
