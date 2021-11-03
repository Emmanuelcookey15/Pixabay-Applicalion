# PixaBay Application

This is a project that builds an app that allows the user to scroll through all the Pixabay search endpoint and view details for each Hits.

**Application**

Polished UI, with thought put into the user experience.

Android app which make use of Marvel Comics API to get comics details and display the information accordingly.

**Separation of Concerns**

The Project Follows MVVM Architecture Pattern plus Clean Architecture in decoupling the logic from the Activity to a ViewModel Class that extends ViewModel.


Design pattern used - MVVM (Model-View-ViewModel), ViewModel, Repository pattern, and Clean Architecture


This App uses following TechStack : 

-- Clean Architecture
-- Kotlin Library
-- Room Database
-- Dagger Hilt
-- Recyclerviews
-- RxJava, RxAndroid, RxKotlin, RxRoom, and Observables
-- Android Architecture Component(ViewModel and LiveData)
-- Retrofit and Coroutines
-- JUnit4 Library For Testing


## Project file


The project File follows the Clean Architechure of One Module per layer
To enforce layer separation I created separate modules for each layer and define dependencies between them.
They are listed below:

###Domain
Contains the base from which the  business logic of the application is built. It is the individual and innermost module. 

### Data Layer
The data moduldes contain everything relating to the data which includes the database, Model, network calls via retrofit class to make request to the server, along with
di folder contains the AppModule for Dependency injection of the various component n


### App or Presentation Layer
view folder contains activity and their adapter- to hold ui

viewmodels folder contain MainViewModel to give any activity fragment that want to observe changes
 in database or network calls from the endpoint





