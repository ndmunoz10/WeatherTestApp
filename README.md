# WeatherApp

## Introduction

### Attention!

This repository contains the source code of the best WeatherApp you're ever seen. This application lets you find any city by its name along with the weather statistics of the current and next days!

## Architecture

The current project followed a clean architecture approach with MVI pattern on the presentation layer, use cases for the domain layer and a repository pattern for the data layer. This decision allows that each feature is independent from each other, so if you need to reuse some logic from one feature into another you just have to inject the use case with the logic into the view model from the other feature. Also, following the repository pattern on the data layer allows us to always relay on the same data and maintain only one source of truth. Additionally, the separation of concerns makes it really easy to introduce changes on each of the layers as long as they maintain the contracts, so you can change the way the locations are fetched without changing the view layer.

![Clean architecture diagram](https://devexperto.com/wp-content/uploads/2018/10/clean-architecture-graph.png)

## Features implemented
* Required features:
    * List locations with their basic information.
    * See the detailed weather information for the selected location 
* Additional features:
    * Support for different device resolutions thanks to constraint layout.
    * Showing images with cache loading and thumbnail placeholder so the experience is smoother.
    * Splash, icon animations and list refresh micro animations.
* Technical features:
    * Dependency injection
    * Multi-module approach
    * Multilayered architecture
    * Unit testing
    * Theme centralization

## Libraries

The project used several libraries, this section will name them and explain the reason why they were chosen and where it was implemented:
* **core-ktx and other ktx related libraries**
    * It adds a nice, big toolset that makes it more clear and less error prone different implementations on the Android level.
* **Coroutines**
    * It's a way to make asynchronous code synchronous and avoiding memory leaks by listening to the Android lifecycle and loading data only when the lifecycle of a certain component is active.
* **Hilt**
    * It allows easier implementation of Dagger 2 and makes it really easy to inject dependencies and make dependency inversion while depending only on contracts (interfaces) and making it easy to change the implementations below without changing the classes that depend on it.
* **JUnit**
    * Library that allows unit testing and makes it easier to setup and discard resources before and after a test is run.
* **LiveData**
    * A way to make reactive programming easy by setting observers to listen for data changes and update the UI accordingly. It was used to change the view state when loading the locations detailed data.
* **Lottie**
    * Used to play some animations for the different weather types a location can have, it's the way they handle cache and play animations is really light by using JSON files.
* **Material related libraries**
    * It allows easier implementation of the dark mode theming by exposing properties such as colorOnSurface, etc...
* **Mockk**
    * Gives straight forward implementations to create mocks based on interfaces or static and final classes and perform unit test on classes that depend on others by using the potential of the Kotlin language.
* **MotionLayout**
    * Animations made easier, checkout the splash screen!
* **Retrofit 2**
    * It allows an easy implementation to consume third party APIs because you only need to build the client and declare an interface to consume an API. It was used to fetch the locations.
* **Test coroutines**
    * Library that allows the test task to run on a single thread even if its specified otherwise on the code. This creates a much more easier way to test suspend functions without having to worry about asynchronous operations.

## How to run

The mail sent has the link to the repository which can be cloned or downloaded. Please open the project on Android Studio, make sure to fetch all dependencies by running the gradle tasks and run the app normally by pressing the Play button.    