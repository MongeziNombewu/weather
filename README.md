# Weather App

A simple weather app intended to provide the user with weather forecast information for the next 5 days. The app is
built using the Open Weather API to get forecast information.

## Architecture

I opted to use the MVVM architecture pattern for this project. The main reason for this is that it allows for a clear
separation of concerns between the UI and the data. This makes it easier to test and maintain the codebase, while also
making the most of what the Android ecosystem provides such as Jetpack components.

The app is layered into four main layers:

- **Data**: This layer is responsible for fetching data from the network or any other source, such as location data. It
  uses the Repository pattern to abstract the data sources from the rest of the app.
- **Domain**: This layer contains the business logic of the app. It is responsible for processing the data and preparing
  it for the presentation layer. In this layer you will find the use cases that the app uses to interact with the data,
  as
  well as the models that represent the data.
- **Presentation**: This layer is responsible for displaying the data to the user. It uses the ViewModel to manage the
  UI
  related data and the Kotlin flows to observe changes in the data. The View layer is responsible for displaying the
  data
  to the user and handling user interactions.

## Languages and Tools

I chose Kotlin as the language for this project because it is the recommended language for Android development, and gets
first class support from Android in the form of great tools such as Compose & Jetpack. This also made it possible to
use Compose for the UI, which is a declarative UI toolkit that makes it easier to build UIs.

## Dependencies

_Networking & Parcelling_

- **Retrofit**: Used to make network requests to the OpenWeatherMap API.
- **Moshi**: Used to parse JSON responses from the API.

_Dependency Injection_

- **Koin**: Used for dependency injection.

_Utility_

- **Timber**: Used for logging.
- **Kotlin Coroutines**: Used to handle asynchronous operations.
- **LeakCanary**: Used to detect memory leaks.
- **Firebase Crashlytics**: Used to track app crashes.
- **Firebase App Distribution**: Used to distribute the app to testers.

_User Interface_

- **Jetpack Compose**: Used to build the UI.
- **Accompanist**: Used to request permissions in Compose.
- **Material3**: Used to style the app.
- **Play Services Location**: Used to get the user's location.

_Unit Testing_

- **JUnit**: Used for unit testing.
- **Mockito**: Used for mocking objects in tests.

## CI/CD

The project uses GitHub Actions for CI/CD. The CI workflow runs the unit tests and code quality checks on every push
and pull request. The CD workflow builds the app and distributes it to testers using Firebase App Distribution.

### Considerations

**Dependabot** has been integrated into the project to keep the dependencies up to date.

The sensitive information such as the API key is stored in a `secrets.properties` file that is not checked into GitHub.
In addition, the sonarcloud, Google Cloud service account and Firebase project are stored discreetly as **GitHub secret
variables**.

Code analysis and quality checks are done using **SonarCloud**. The project is also developed in an IDE with
**SonarLint** to pickup issues As You Code.

A **pull request template** has been added to the project to ensure that all pull requests have a description, testing
steps.
This is to ensure that the reviewer has all the information needed to review the code.

## Installation

The app can be installed by downloading the APK from Firebase App Distribution. The app is distributed to testers

Additionally you can build the app from the source code by following the steps below:

1. Clone the repository
2. Create a `secrets.properties` file in the root of the project and provide the applicable keys
3. Add the `google-services.json` file to the `app` directory
4. Build the project using Android Studio or the command line

NOTE: The signing configs used locally will not be the same as those used in the firebase tester app. The signing
configurations are not complete :/