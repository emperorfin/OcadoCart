# Ocado Cart

Ocado Cart is a native Android app project that uses [Ocado's mocked webservice REST API](https://my-json-server.typicode.com/ocadotechnology/mobile-challenge)
to display list of products on one screen and a product details on another screen. The list displays
a header with the cluster name as well as the products it contains. The list displays title, image,
size, and price of each product.

When a user clicks on a given product of the list, the user will be redirected to another screen with
more specific details about the clicked product.

The second screen displays more detailed information about a given product. It shows the title,
image, description, price, size and allergy information of the product.

There's proper handling of screen rotation configuration changes.

Also, when the app is newly installed and if the user opens it (and navigates) to the products
overview screen without an internet connectivity, the screen shows no products overview list and
allows the user to refresh the screen assuming they have an active internet connectivity.

The app fetches a list of products overview (from the Ocado's mocked webservice REST API) when the
user refreshes with an active internet connectivity. The products overview list is then displayed
on the screen.

Assuming the user visits the products overview screen for the first time with an active internet
connectivity, the app fetches a list of products overview (from the Ocado's mocked webservice REST API).
The products overview list is then displayed on the screen.

Whenever the user subsequently visits the products overview list screen, if without internet
connectivity, the cached products overview list is displayed. But with active internet connectivity,
a fresh products overview list is fetched from the aforementioned web service and displayed on the
screen.

The products overview screen's configuration changes (screen rotation in this case) has been
automatically handled since the ViewModel is used. So whenever there is products overview data on
the screen and the screen rotates, the products overview data is immediately available for the new
instance of the screen. So no need to request products overview data from the cache (Room database)
or web service (Ocado's).

Also implemented internet connectivity checks to the product details UI screen. On navigation to the
screen without internet connectivity, no product details is displayed and a SnackBar is displayed
with an action for the user to retry fetching a product's details when there's active internet
connectivity. Product details is automatically fetched on navigation to the screen with active
internet connectivity.

## Project Tech-stack and Characteristics

* Android Framework
* Kotlin
* MVVM Architectural Design Pattern
* Repository Pattern
* Single Activity App Architecture (just one Activity in the whole app with multiple Fragment classes.)
* Jetpack
* Navigation Components
* Proper Navigation Handling
* Automatic Configuration Changes Handling (screen rotation)
* SafeArgs
* LiveData
* Offline Storage (via Room)
* Retrofit
* Moshi
* Coroutines
* ViewModel
* [Ocado's mocked webservice REST API](https://my-json-server.typicode.com/ocadotechnology/mobile-challenge)
* Glide
* Data Binding (very strict)
* Binding Adapters
* RecyclerView DiffUtil
* Unit Testing
* AndroidX Test
* Hamcrest
* Robolectric
* Architecture Components Core Testing
* Coroutines Test
* UI Testing
* Espresso

## Todo

 - [ ] Dependency Injection (Dagger2, Hilt)
 - [ ] Modularization
 - [ ] CI/CD
 - [ ] More...

## Architecture

This is a sample project that presents a good starting point for a modern approach to Android app
development. For a single module app, this project lets you kickstart a Single Activity App
architecture based Android app development with the support of MVVM architectural design and
Repository patterns.

### Project Structure

The project contains 3 layers with distinct set of responsibilities.

#### UI Layer

This layer is closest to what the user sees on the screen. The UI layer contains Jetpack ViewModel +
LiveData used to preserve data across Activity restart.

Components:
- **View (Fragment)** - presents data on the screen and pass user interactions to the ViewModel.
Views are hard to test, so they should be as simple as possible.
- **ViewModel** - dispatches (through LiveData) the state changes to the view and deals with user
interactions (these ViewModels are not simply Plain Old Java Object [POJO] classes.)
- **UiModel** - defines the structure of the data that will be used within the UI layer.
- **UiModelMapper** - is used to convert from domain model to UI model (to keep UI layer
independent from the data layer).

#### Domain Layer

This is the core layer of the application.

Components:
- **Model** - defines the core structure of the data that will be used within the application. This
is the source of truth for application data.
More importantly, the domain model mediates between the data models from other layers. For example,
if the UI layer needs to use data from the local data source (e.g. Room database) in the Data layer,
the data is first converted from entity (data model for local data source) to domain model and then
to UI model. The conversion process is the reverse if the local data source in the Data layer needs
data from the UI layer. Also if the UI layer needs to use data directly from the remote data source
(e.g. web service via Retrofit) in the Data layer, the data is first converted from data transfer
object (data model for remote data source) to domain model and then to UI model. The conversion
process is the reverse if the remote data source in the Data layer needs data from the UI layer.
Even within the same layer, the domain model mediates between the models from different data
sources. For example, in the Data layer, if the local data source needs data from the remote data
source, the data is first converted from the data transfer object to the domain model and then to
entity. The conversion process is the reverse if the remote data source needs data from the local
data source.
This further strengthens decoupling between UI and Data layers and data sources within the Data layer.
- **ModelMapper** - is used to convert from data models from every other layers to domain data model.
- **Repository interface** - required to keep the UI layer independent from the Data layer
(Dependency inversion). This is also very useful for testing.
- **DataSource interface** - required for testing.

#### Data Layer

Manages application data and exposes data sources as repositories to the UI layer. Typical
responsibilities of this layer would be to retrieve data from the internet and optionally cache this
data locally.

Components:
- **DataTransferObjectModel** - defines the structure of the data retrieved from the internet and
may contain annotations, so Retrofit/Moshi knows how to parse this internet
data (XML, JSON, Binary...) into objects.
- **DataTransferObjectMapper** - is used to convert from domain model to data transfer object (to
keep the Data layer's remote data source independent from other layers and data sources).
- **Entity** - defines the structure of the data retrieved from or saved to the local database.
- **EntityMapper** - is used to convert from domain model to entity (to keep the Data layer's local
data source independent from other layers and data sources).
- **Repository class** - exposes data to the UI layer.
- **Retrofit Service** - defines a set of web service API endpoints.

## Upcoming Improvements

Checklist of all upcoming [enhancements](https://github.com/emperorfin/OcadoCart/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc+label%3Aenhancement).

## Getting Started

### Command-line
Run git clone https://github.com/emperorfin/OcadoCart.git command to clone the project.

## Note
You may notice some empty packages, unused classes (e.g. sample data generator) and functions. The
empty packages indicate ongoing project improvements. Working with sample data first is faster
during development. When everything is fine, then the sample data is dropped and then the real data
is directly worked with. I don't often recommend removing such sample data generator classes after
use as, if something goes wrong while using real data for example, they may be very helpful. It's
really fine to remove them during production. But they are there because of this project.

