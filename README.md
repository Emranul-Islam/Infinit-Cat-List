
<h1 align="center">Infinite Cats List</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
<p align="center">  
Infinte Cats List is a small demo application based on modern Android application tech-stacks and MVVM architecture.<br>This project is for focusing especially on the  library Hilt of implementing dependency injection.<br>
Also fetching data from the network and integrating persisted data in the database via repository pattern.
</p>
</br>

## Preview

![enter image description here](https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png)

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
-  [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for paging network data.
- [Room](https://developer.android.com/jetpack/androidx/releases/room) for save data locally.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Retrofit2](https://github.com/square/retrofit) - construct the REST APIs


## The Cat API

Infinite Cats list using the [The Cat Api](https://thecatapi.com/) for constructing RESTful API.<br>
TheCatApi provides a RESTful API interface to highly detailed objects built from thousands of lines of Cat data .

## Find this repository useful? :heart:
Give this repository. :star: <br>
And __[follow](https://github.com/Emranul-Islam)__ me for my next creations! 🤩

