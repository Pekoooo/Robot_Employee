# Robot Employee App

This is a sample app that showcases the implementation of clean architecture principles, `MVVM` architecture pattern, and properly handled data flow using `Flow` and `StateFlow`. The app saves the data retrieved from the API call using `Retrofit` in a `Room` database and will not fetch the data from the API if the database is not empty. There is a way to force the call to the endpoint by swiping down on the `RecyclerView`.

## Features

The Robot Employee app has the following features:

- Display a list of employees retrieved from an API endpoint
- Save the retrieved employee data in a local database using Room
- Display the employee data from the database if it is not empty
- Refresh the employee data from the API endpoint by swiping down on the `RecyclerView`
- Show an error message if the API call fails to retrieve the data
- Show a specific UI if the list is empty

## Architecture

The Robot Employee app follows the principles of clean architecture, which separates the application into layers that have distinct responsibilities. The layers include:

- **Presentation Layer**: This layer is responsible for presenting the data to the user and handling user interactions. The presentation layer in this app follows the MVVM architecture pattern, which separates the UI logic from the data and business logic. The ViewModel is responsible for holding the data and business logic for the UI to display.

- **Domain Layer**: This layer contains the business logic of the application. It defines the use cases of the application and handles the interactions between the presentation layer and the data layer.

- **Data Layer**: This layer is responsible for handling the data of the application. It retrieves the data from the API or local database and converts it into a format that can be used by the domain and presentation layers.

## Libraries

The Robot Employee app uses the following libraries:

- `Retrofit` for making API calls
- `Room` for local database storage
- `Kotlin Coroutines` for asynchronous programming
- `Flow` and `StateFlow` for reactive programming
- `Picasso` for image loading
- `Material Design` components for UI elements

## Requirements

- Android Studio 4.0 or higher
- Kotlin version 1.5.21 or higher
- Gradle version 4.2.2 or higher
- Android SDK version 21 or higher
