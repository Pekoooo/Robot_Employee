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

## Data Flow

1. The user interacts with the UI elements in the presentation layer 
2. The ViewModel receives this user input and calls the appropriate Use Case in the Domain layer
3. Use Case interacts with the repository, who applies some logic and interact with the data layer, local or remote based on the logic.
4. Data Layers are the single source of truth to fetch the required data, through API calls or SQL Queries.
5. The data is returned to the repository in a form of a Flow, that the repository wraps in a Result based on a Try Catch block, using a sealed class. This sealed class can handle a Success state, an Error state. A specific logic is applied regarding the state down the stream in the ViewModel layer.
6. The data, wrapped in a Result Type is being processed through the Use Case and some logic is applied regarding which use case is being called (data transformation etc..) and returns the transformed data to the ViewModel
7. The ViewModel unwraps the data from its Result and updates the State Flow called Ui State based on the Result with another Sealed Class, created to handle Ui State.

This other sealed class is here to define 3 states, Loading, Success or Error. The Error State of that Sealed Class takes another Sealed Class as a parameter called “ ErrorType “ who contains multiple types of errors (Network Errors, Malformed Data, Empty List and Other) this Sealed Class Error Type will help us communicate with the user what kind of malfunction the app went through when trying to fetch data. 

8. Once the data unwrapped and properly handled, the ViewModel updates the UI with the new data. 

This approach emphasizes 2 very important OOP concepts which are the separation of concerns and the independence of components. By doing so, I am making sure the application is can be easily tested and maintained and changes to one layer will not affect the others. 

## Libraries

The Robot Employee app uses the following libraries:

- `Retrofit` for making API calls
- `Room` for local database storage
- `Kotlin Coroutines` for asynchronous programming
- `Hilt` for Dependency Injection
- `Flow` and `StateFlow` for reactive programming
- `Picasso` for image loading
- `Material Design` components for UI elements

## Requirements

- Android Studio 4.0 or higher
- Kotlin version 1.5.21 or higher
- Gradle version 4.2.2 or higher
- Android SDK version 21 or higher
